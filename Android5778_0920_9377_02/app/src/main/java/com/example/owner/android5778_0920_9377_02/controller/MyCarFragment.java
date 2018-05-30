package com.example.owner.android5778_0920_9377_02.controller;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.owner.android5778_0920_9377_02.R;
import com.example.owner.android5778_0920_9377_02.model.backend.DB_ManagerFactory;
import com.example.owner.android5778_0920_9377_02.model.backend.RentConst;
import com.example.owner.android5778_0920_9377_02.model.entities.Branch;
import com.example.owner.android5778_0920_9377_02.model.entities.Car;
import com.example.owner.android5778_0920_9377_02.model.entities.Enums.Status;
import com.example.owner.android5778_0920_9377_02.model.entities.Order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCarFragment extends ListFragment {

    private TextView loadingTextView;
    private TextView NoOrderTextView;
    private  View view;
    String userID;
    String carnum;
    String orderNum;
    Date startRent;
    int cost;
    private TextView kilometersEditText;
    private TextView fuelEditText ;
    private CheckBox fuelCheckBox;
    private static List<Order> orders=new ArrayList<>();
    private static List<Car> cars=new ArrayList<>();
    private static List<Car> mycars=new ArrayList<>();
    private static List<String> carNumbers=new ArrayList<>();
    private boolean flag=false;
    ArrayAdapter<Car> adapter;

    public MyCarFragment() {
        // Required empty public constructor

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_my_car, container, false);
        loadingTextView = (TextView) view.findViewById(R.id.loadingTextView);
        NoOrderTextView = (TextView) view.findViewById(R.id.NoOrderTextView);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        userID = sharedPreferences.getString("PASSWORD", null);
        getAllOrders(); //call function that return all open orders
        return view;
    }

    /**
     * the function returns all open orders and save them in "orders"
     * after, we check for each order if it belong to the current client, to show all his orders. and save the number car that the user rent in "carNumbers"
     */
    private void getAllOrders() {
        try {
            new AsyncTask<Void, Void, List<Order>>() {

                @Override
                protected List<Order> doInBackground(Void... params) {
                    return DB_ManagerFactory.getManager().getAllOpenOrders();
                }

                @Override
                protected void onPostExecute(List<Order> ListOrders) {
                    super.onPostExecute(ListOrders);
                    orders.clear();
                    orders = ListOrders;
                    carNumbers.clear();
                    for(Order order : orders)
                    {
                        if (order.getClientNumber().equals(userID)) {
                            flag = true;
                            carNumbers.add(order.getCarNumber());
                        }
                    }
                    if(flag)  //that mean the user have order
                    {
                        getAllCars(); //function that returns all cars that the user rent
                    }
                    else
                    {
                        NoOrderTextView.setVisibility(View.VISIBLE);
                        loadingTextView.setVisibility(View.INVISIBLE);
                    }
                }
            }.execute();
        }
        catch (Exception e)
        {

        }
    }

    /**
     * the function returns all cars that the user rent and save them at "mycars"
     * we check all cars and choose car only if the number equals to the car number that we saved from the orders that belong to user
     */
    private void getAllCars() {
        try {
            new AsyncTask<Void, Void, List<Car>>() {

                @Override
                protected List<Car> doInBackground(Void... params) {
                    return DB_ManagerFactory.getManager().getAllCars();
                }

                @Override
                protected void onPostExecute(List<Car> ListCars) {
                    super.onPostExecute(ListCars);
                    cars = ListCars;
                    mycars.clear();
                    for(Car car : cars) //check all cars
                    {
                        for(String number:carNumbers) {
                            if (car.getCarNumber().equals(number)) { //only the car with the number that we saved from the orders
                                mycars.add(car);
                            }
                        }
                    }
                    adapter =new ArrayAdapter<Car>(getContext(), R.layout.item_view, mycars);
                    setListAdapter(adapter);

                    loadingTextView.setVisibility(View.INVISIBLE);
                }
            }.execute();
        }
        catch (Exception e)
        {

        }
    }

    /**
     by clicking on a car , you can close your order
     before closing, ask if you want to close the order ,if you do, show dialog that ask for the kilometers and the fuel that the user fill
     */
    @Override
    public void onListItemClick(ListView l, View v, final int position, long id) {
        super.onListItemClick(l, v, position, id);

        final AlertDialog.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            final AlertDialog.OnClickListener onClickListener = new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Dialog d = (Dialog) dialog;
                    fuelCheckBox=(CheckBox)d.findViewById(R.id.fuelCheckBox);
                    fuelEditText=(EditText)d.findViewById(R.id.fuelEditText);
                    kilometersEditText=(EditText)d.findViewById(R.id.kilometersEditText);
                    switch (which)
                    {
                        case Dialog.BUTTON_POSITIVE:

                            for(Order order:orders) //find the specific order, save the number order and car
                                if(order.getCarNumber().equals(mycars.get(position).getCarNumber()))
                                {
                                    orderNum = order.getOrderNumber();
                                    carnum = mycars.get(position).getCarNumber();
                                    break;
                                }

                            if(fuelCheckBox.isChecked()) //if the user filled fuel
                                closeorder(orderNum,kilometersEditText.getText().toString(),true,fuelEditText.getText().toString()); //call to function with the fuel parameters
                            else
                                closeorder(orderNum,kilometersEditText.getText().toString(),false,"0");//else without fuel parameters
                            break;
                        case Dialog.BUTTON_NEGATIVE:
                            break;
                    }
                }
            };

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which)
                {
                    case Dialog.BUTTON_POSITIVE:
                        AlertDialog.Builder newAlertDialogBuilder = new AlertDialog.Builder(getContext());
                        newAlertDialogBuilder.setTitle("Close order");
                        newAlertDialogBuilder.setView(getActivity().getLayoutInflater().inflate(R.layout.dialog, null));
                        newAlertDialogBuilder.setPositiveButton("Yes",onClickListener);
                        newAlertDialogBuilder.setNegativeButton("No",onClickListener);
                        AlertDialog alertDialog = newAlertDialogBuilder.create();
                        alertDialog.show();
                            break;
                    case Dialog.BUTTON_NEGATIVE:
                        break;
                }
            } };

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setTitle("Close order");
        alertDialogBuilder.setMessage("Are you sure you want to return this car?");
        alertDialogBuilder.setPositiveButton("Yes",onClickListener);
        alertDialogBuilder.setNegativeButton("No",onClickListener);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /**
     * the function close user order, and update details of the car
     * after closing the order we show dialog with message that the order was closed
     * @param ordernum
     * @param k => kilometers
     * @param b => if the user filled fuel or not, boolean
     * @param F => if the user filled fuel, how much
     */
    private void closeorder(final String ordernum, String k, boolean b, String F ) {

        final ContentValues contentValuesorder = new ContentValues();
        final ContentValues contentValuescar = new ContentValues();
        try {

            contentValuesorder.put(RentConst.OrderConst.TOTAL_PAY,TotalPay()); //call function TotalPay to calculate how much the user need to pay
            contentValuesorder.put(RentConst.OrderConst.ORDER_NUMBER,ordernum);
            contentValuesorder.put(RentConst.OrderConst.END_KM,k);
            contentValuesorder.put(RentConst.OrderConst.DO_FUEL,b);
            contentValuesorder.put(RentConst.OrderConst.LITERS_FUEL,F);

            contentValuescar.put(RentConst.CarConst.CAR_NUMBER,carnum);
            contentValuescar.put(RentConst.CarConst.KILOMETERS,k);


            new AsyncTask<Void, Void, String>() {

                @Override
                protected String doInBackground(Void... params) {
                    String toPay= DB_ManagerFactory.getManager().closeOrder(contentValuesorder);
                    DB_ManagerFactory.getManager().CloseUpdateCar(contentValuescar);
                    return toPay;
                }

                /**
                 * the dialog with message to the user that his order closed and how much he will be debit
                 * @param toPay
                 */
                @Override
                protected void onPostExecute(String toPay) {
                    super.onPostExecute(toPay);
                    new AlertDialog.Builder(getContext())
                            .setIcon(R.drawable.ic_report_black_24dp)
                            .setTitle("Closing Order")
                            .setMessage("Your order " +ordernum+" has been closed, your account will be debit for "+ toPay +" NIS")
                            .setPositiveButton("Ok", null)
                            .show();
                    adapter.notifyDataSetChanged();
                }
            }.execute();
        }
        catch (Exception e)
        {

        }
    }

    /**
     * the function calculate how much the user need to pay.
     * for each car there is a cost for one day, if the user filled fuel we reduce one shekel for each liter from the total pay
     * @return
     */
    private int TotalPay() {
        int totalPay;
        for(Order order:orders)//find the cost and start rent for calculate the total pay
        {
            if(order.getOrderNumber()==orderNum)
            {
                startRent=order.getStartRent();
                for(Car car:cars)
                {
                    if(car.getCarNumber().equals(order.getCarNumber()))
                    {
                        cost=car.getCost();
                        break;
                    }
                }
            }
        }
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        Date nowDate=new Date();
        int days=((int)(nowDate.getTime()-startRent.getTime()))/(1000*60*60*24);

        if (days==0) { //if the user rent and return the car in the same day
            totalPay=cost;
        }
        else totalPay=days*cost;

        if(fuelCheckBox.isChecked()) //if the user filled fuel
        {
            totalPay-=Integer.parseInt(fuelEditText.getText().toString());
        }
        return totalPay;
    }





}
