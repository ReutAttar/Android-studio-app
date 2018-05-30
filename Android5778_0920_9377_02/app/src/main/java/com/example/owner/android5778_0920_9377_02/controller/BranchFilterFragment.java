package com.example.owner.android5778_0920_9377_02.controller;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.owner.android5778_0920_9377_02.R;
import com.example.owner.android5778_0920_9377_02.model.backend.DB_ManagerFactory;
import com.example.owner.android5778_0920_9377_02.model.backend.RentConst;
import com.example.owner.android5778_0920_9377_02.model.entities.Address;
import com.example.owner.android5778_0920_9377_02.model.entities.Branch;
import com.example.owner.android5778_0920_9377_02.model.entities.Car;
import com.example.owner.android5778_0920_9377_02.model.entities.Order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class BranchFilterFragment extends Fragment{

    private SearchView filterSearchView;
    private ExpandableListView branchesExpandableList;
    private ExpandableListView carByBranchExpandableList;
    private Button navigationButton;
    private View view;
    private String userID;
    String date;
    Car childCar;
    private static List<Car> cars = new ArrayList<>();
    private static List<Branch> branches = new ArrayList<>();
    private static List<Branch> copybranches = new ArrayList<>();
    final MyBaseExpandableListAdapter myBaseExpandableListAdapter = new MyBaseExpandableListAdapter();

    public BranchFilterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_branch_filter, container, false);
        filterSearchView = (SearchView) view.findViewById(R.id.filterSearchView);
        carByBranchExpandableList = (ExpandableListView) view.findViewById(R.id.carByBranchExpandableList);
        branchesExpandableList =  ((ExpandableListView) view.findViewById(R.id.branchesExpandableList));
        getallbranches();
    /**
     * what happen when the user click on the details of branch
     */
        branchesExpandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
            {
                final Branch branch = branches.get(groupPosition); //creates object of type branch
                final ContentValues contentValues = new ContentValues(); //creates contentValues
                contentValues.put(RentConst.BranchConst.BRANCH_NUMBER,branch.getBranchNumber()); //inserts to contentValues the number's branch
                getallcarsbybranch(contentValues); //call to function that returns the cars that belong to the specific branch
                return true;
            }
        });

        /**
         * what happen when the user click on the details of car.
         */
        carByBranchExpandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, final int groupPosition, final int childPosition, long id) {
                AlertDialog.OnClickListener onClickListener = new DialogInterface.OnClickListener() { //event click of the dialog

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which)
                        {
                            case Dialog.BUTTON_POSITIVE: //if the user click yes
                                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                                userID = sharedPreferences.getString("PASSWORD", null); //save the user id from the sharedPreferences
                                childCar=cars.get(groupPosition); //save the specific car that the user choose
                                date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()); //save the current date
                                addOrder(userID,childCar.getCarNumber(),childCar.getKilometers(),date); //call to function that make order for the user
                                cars.remove(childCar); //remove the car from the list available cars
                                carByBranchExpandableList.setAdapter(new MyBaseExpandableListCarsAdapter());
                                break;
                            case Dialog.BUTTON_NEGATIVE: //if the user click no
                                break;
                        }
                    } };

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setTitle("add order");
                alertDialogBuilder.setMessage("Are you sure you want to rent this car?");
                alertDialogBuilder.setPositiveButton("Yes",onClickListener);
                alertDialogBuilder.setNegativeButton("No",onClickListener);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                return true;
            }
        });

        return view;
    }

    /**
     * class for ExpandableListView that represents the branches.
     * the header of the group contain the branch number and have button that open google map at the address of the branch.
     * the child of the group contain the details about the branch and if the user click it another ExpandableListView shows the cars that belong to the branch.
     */

    class MyBaseExpandableListAdapter extends BaseExpandableListAdapter implements View.OnClickListener {

        @Override
        public int getGroupCount() {
            return copybranches.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 1;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return null;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return null;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override //define the group view
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View Header = getActivity().getLayoutInflater().inflate(R.layout.header_with_map, parent, false);
            navigationButton=(Button)Header.findViewById(R.id.navigationButton);
            navigationButton.setTag(copybranches.get(groupPosition).getAddress()); //save the address as tag in the button
            TextView text = (TextView) Header.findViewById(R.id.ListHeader);
            text.setTypeface(null, Typeface.BOLD);
            text.setText("Branch Number: " + copybranches.get(groupPosition).getBranchNumber()); //sets the header's text to be the branch number
            navigationButton.setOnClickListener(this); //sets event when the user click on the navigation Button
            return Header;
        }

        @Override //define the child's group view
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View BranchItem = getActivity().getLayoutInflater().inflate(R.layout.branch_item, parent, false);
            final Branch branch = copybranches.get(groupPosition);
            TextView branchNumber = (TextView) BranchItem.findViewById(R.id.branchNumberTextView);
            TextView parkingSpaces = (TextView) BranchItem.findViewById(R.id.parkingSpacesTextView);
            TextView address = (TextView) BranchItem.findViewById(R.id.addressTextView);
            branchNumber.setText("Branch Number: " + branch.getBranchNumber());
            parkingSpaces.setText("Number of parking spaces: " + String.valueOf(branch.getParkingSpaces()));
            address.setText("Branch Address: " + branch.getAddress().toString());
            return BranchItem;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        /**
         * the function allow the user to filtering the branches according to the city name.
         * also works with upper and lower letters.
         * @param query
         */
        public void filterData(String query){

            copybranches.clear();

            if(query.isEmpty()){ //if there is no filter query show all branches
                copybranches.addAll(branches);
            }
            else {
                for (Branch branch : branches) {
                    if (branch.getAddress().getCity().toUpperCase().startsWith(query.toString().toUpperCase())) { //checks if the city start with same letters that the user insertt
                        copybranches.add(branch);
                    }
                }
            }
            notifyDataSetChanged();
        }

        /**
         * when the user click on the navigation button.
         * @param v
         */
        @Override
        public void onClick(View v) {
            if ( v.getTag().getClass().equals(Address.class)) {

                Address address = (Address) v.getTag(); //save the address from the tag button
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri location=Uri.parse("geo:0,0?q="+address.toString());
                intent.setData(location);
                startActivity(intent);
            }
        }
    }

    /**
     * class for ExpandableListView that represents the cars that belong to specific branch.
     * the header of the group contain the car number.
     * the child of the group contain the details about the car and if the user click it, dialog will show up and ask the user if he want to rent the specific car.
     */

    class MyBaseExpandableListCarsAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return cars.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 1;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return null;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return null;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override //define the group view
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View Header = getActivity().getLayoutInflater().inflate(R.layout.header, parent, false);
            TextView text = (TextView) Header.findViewById(R.id.ListHeader);
            text.setTypeface(null, Typeface.BOLD);
            text.setText("Car Number: " + cars.get(groupPosition).getCarNumber()); //sets the header's text to be the car number
            return Header;
        }

        @Override //define the child's group view
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View CarItem = getActivity().getLayoutInflater().inflate(R.layout.car_item, parent, false);
            final Car car = cars.get(groupPosition);
            TextView CarNumberTextView = (TextView) CarItem.findViewById(R.id.CarNumberTextView);
            TextView BranchParkingNumberTextView = (TextView) CarItem.findViewById(R.id.BranchParkingNumberTextView);
            TextView CarModelTextView = (TextView) CarItem.findViewById(R.id.CarModelTextView);
            TextView KilometersTextView = (TextView) CarItem.findViewById(R.id.KilometersTextView);
            TextView StatusTextView = (TextView) CarItem.findViewById(R.id.StatusTextView);
            TextView CarCostTextView = (TextView) CarItem.findViewById(R.id.CarCostTextView);
            CarNumberTextView.setText("Car Number: " +car.getCarNumber());
            BranchParkingNumberTextView.setText("Branch Parking Number: " +car.getBranchParkingNumber());
            CarModelTextView.setText("Car Model: " +car.getCarModel());
            KilometersTextView.setText("Kilometers: " +car.getKilometers());
            StatusTextView.setText("Status: " +car.getStatus());
            CarCostTextView.setText("Car Cost: " +car.getCost());
            return CarItem;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    }

    //method to expand all groups, happen when the user use filter option.
    private void expandAll() {
        int count = myBaseExpandableListAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            branchesExpandableList.expandGroup(i);
        }
    }

    /**
     * the function works after the user choose to create order.
     * @param userID
     * @param CarNumber
     * @param Kilometers
     * @param date
     */

    private void addOrder( String userID, final String CarNumber, Float Kilometers, String date) {
        final ContentValues contentValues = new ContentValues();
        try {
            contentValues.put(RentConst.OrderConst.CLIENT_NUMBER, userID);
            contentValues.put(RentConst.OrderConst.CAR_NUMBER, CarNumber);
            contentValues.put(RentConst.OrderConst.START_KM, Kilometers);
            contentValues.put(RentConst.OrderConst.START_RENT, date);

            new AsyncTask<Void, Void, String>() {
                @Override
                protected String doInBackground(Void... params) {
                    String numOrder=DB_ManagerFactory.getManager().addOrder(contentValues); //call to function that add order to the user
                    DB_ManagerFactory.getManager().OpenUpdateCar(CarNumber); //call to function that update the details of the specific car
                    return numOrder;
                }

                @Override
                protected void onPostExecute(String numOrder) {
                    Toast.makeText(getContext(), "An order has been added , your order number is:" + numOrder, Toast.LENGTH_LONG).show();
                }
            }.execute();
        }
        catch (Exception e) {

        }
    }

    /**
     * the function return all branches and save them at variable "branches".
     */

    private void getallbranches() {
        try {
            new AsyncTask<Void, Void, List<Branch>>() {

                @Override
                protected List<Branch> doInBackground(Void... params) {
                    return DB_ManagerFactory.getManager().getAllBranchs();//call to function that return all branches
                }

                @Override
                protected void onPostExecute(List<Branch> ListBranches) {
                    super.onPostExecute(ListBranches);
                    branches = ListBranches;
                    copybranches.clear(); //"copybranches" list for filter option
                    copybranches.addAll(ListBranches);
                    branchesExpandableList.setAdapter(myBaseExpandableListAdapter);

                    filterSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() { //filter option

                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            myBaseExpandableListAdapter.filterData(query);
                            expandAll();
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            myBaseExpandableListAdapter.filterData(newText);
                            expandAll();
                            return false;
                        }
                    });
                }
            }.execute();
        }
        catch (Exception e)
        {

        }
    }

    /**
     * the function returns all cars that belong to specific branch.
     * @param branchNumber
     */

    private void getallcarsbybranch(final ContentValues branchNumber) {
        try {
            new AsyncTask<Void, Void, List<Car>>() {
                @Override
                protected List<Car> doInBackground(Void... params) {
                    return DB_ManagerFactory.getManager().getAvailableCarsByBranch(branchNumber);//call to the function that returns the cars that belong to specific branch
                }

                @Override
                protected void onPostExecute(List<Car> listcars) {
                    if(listcars.size()==0) //if there is no any cars at the branch
                    {
                        Toast.makeText(getContext(), "there is no available cars in this branch" , Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        cars = listcars; //save the cars at the variable "cars"
                        carByBranchExpandableList.setAdapter(new MyBaseExpandableListCarsAdapter());
                    }
                }
            }.execute();
        }
        catch (Exception e) {

        }
    }
}
