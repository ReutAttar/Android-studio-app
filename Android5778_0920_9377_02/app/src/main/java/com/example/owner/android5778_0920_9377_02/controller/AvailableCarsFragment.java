package com.example.owner.android5778_0920_9377_02.controller;


import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.owner.android5778_0920_9377_02.R;
import com.example.owner.android5778_0920_9377_02.model.backend.DB_ManagerFactory;
import com.example.owner.android5778_0920_9377_02.model.entities.Branch;
import com.example.owner.android5778_0920_9377_02.model.entities.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AvailableCarsFragment extends Fragment {
    private SearchView filterSearchView;
    private ExpandableListView availableCarsExpandableList;
    private View view;
    private List<Car> allavailablecars=new ArrayList<>();
    private List<Car> copyallavailablecars=new ArrayList<>();
    final MyBaseExpandableListAdapter myBaseExpandableListAdapter = new MyBaseExpandableListAdapter();

    public AvailableCarsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_available_cars, container, false);
        filterSearchView = (SearchView) view.findViewById(R.id.filterSearchView);
        availableCarsExpandableList=((ExpandableListView) view.findViewById(R.id.availableCarsExpandableList));
        getallavailablecars(); //call function that return all available cars
        return view;
    }

    /**
     * the function return all available cars and saved them at "allavailablecars"
     */
    private void getallavailablecars() {
        try {
            new AsyncTask<Void, Void, List<Car>>() {

                @Override
                protected List<Car> doInBackground(Void... params) {
                    return DB_ManagerFactory.getManager().getAllAvailableCars();
                }

                @Override
                protected void onPostExecute(List<Car> ListCars) {
                    super.onPostExecute(ListCars);
                    allavailablecars = ListCars;
                    copyallavailablecars.clear();
                    copyallavailablecars.addAll(ListCars); //another list for the filter option
                    availableCarsExpandableList.setAdapter(myBaseExpandableListAdapter);

                    filterSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

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

    //method to expand all groups
    private void expandAll() {
        int count = myBaseExpandableListAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            availableCarsExpandableList.expandGroup(i);
        }
    }

    /**
     * class for ExpandableListView that represents the available cars.
     * the header of the group contain the car number.
     * the child of the group contain the details about the car.
     */
    class MyBaseExpandableListAdapter extends BaseExpandableListAdapter
    {

        @Override
        public int getGroupCount() {
            return copyallavailablecars.size();
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

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View Header = getActivity().getLayoutInflater().inflate(R.layout.header, parent, false);
            TextView text = (TextView) Header.findViewById(R.id.ListHeader);
            text.setTypeface(null, Typeface.BOLD);
            text.setText("Car Number: " + copyallavailablecars.get(groupPosition).getCarNumber());
            return Header;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View CarItem = getActivity().getLayoutInflater().inflate(R.layout.car_item, parent, false);
            final Car car = copyallavailablecars.get(groupPosition);
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
        public boolean isChildSelectable(int groupPosition, int childPosition) {return true; }

        /**
         * the function allow the user to filtering the cars according to the car model name.
         * also works with upper and lower letters.
         * @param query
         */
        public void filterData(String query){

            copyallavailablecars.clear();

            if(query.isEmpty()){
                copyallavailablecars.addAll(allavailablecars);
            }
            else {
                for (Car car : allavailablecars) {
                    if (car.getCarModel().startsWith(query.toString())) {
                        copyallavailablecars.add(car);
                    }
                }
            }
            notifyDataSetChanged();
        }
    }


}
