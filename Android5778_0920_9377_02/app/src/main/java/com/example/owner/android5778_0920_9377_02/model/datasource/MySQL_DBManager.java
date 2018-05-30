package com.example.owner.android5778_0920_9377_02.model.datasource;

import android.content.ContentValues;

import com.example.owner.android5778_0920_9377_02.model.backend.DB_manager;
import com.example.owner.android5778_0920_9377_02.model.backend.RentConst;
import com.example.owner.android5778_0920_9377_02.model.entities.Branch;
import com.example.owner.android5778_0920_9377_02.model.entities.Car;
import com.example.owner.android5778_0920_9377_02.model.entities.Client;
import com.example.owner.android5778_0920_9377_02.model.entities.Order;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * all functions here connected to our database by php files
 */

public class MySQL_DBManager implements DB_manager{

    private String WEB_URL="http://rattar.vlab.jct.ac.il/TAKE%26GO"; //the address for our database

    /**
     *get an id and check if the client exist
     */
    @Override
    public boolean clientExist(ContentValues values) {
        try {
            String result = PHPtools.POST(WEB_URL + "/checkClient.php", values).trim();
            if (result.equals("1"))
                return true;
            else return false;
        }
        catch (IOException e)
        {
            System.out.println("checkClient Exception:\n" + e);
            return false;
        }
    }

    /**
     *get a client and add him to the database
     */
    @Override
    public String addClient(ContentValues client) {
        try {
            String result = PHPtools.POST(WEB_URL + "/addClient.php", client);
            return result;     }
        catch (IOException e) {
            System.out.println("addClient Exception:\n" + e);
            return null;
        }
    }
    /**
     *add an order
     */
    @Override
    public String addOrder(ContentValues order) {
        try {
            String result = PHPtools.POST(WEB_URL + "/addOrder.php", order);
            return result;
        }
        catch (IOException e) {
            System.out.println("addOrder Exception:\n" + e);
            return null;
        }
    }

    /**
     * when we add an order we call also to this function for update the car to be not available
     */
    @Override
    public String OpenUpdateCar(String carnum) {
        try {
            final ContentValues contentValues = new ContentValues();
            contentValues.put(RentConst.CarConst.CAR_NUMBER,carnum);
            String result = PHPtools.POST(WEB_URL + "/OpenUpdateCar.php", contentValues);
            return result;
        }
        catch (IOException e) {
            System.out.println("OpenUpdateCar Exception:\n" + e);
            return null;
        }
    }

    /**
     *close an order
     */
    @Override
    public String closeOrder(ContentValues order) {
        try {
            String result = PHPtools.POST(WEB_URL + "/closeOrder.php", order);
            return result;
        }
        catch (IOException e) {
            System.out.println("closeOrder Exception:\n" + e);
            return null;
        }
    }

    /**
     * when we close an order we call also to this function for update the car to be available and her current kilometers
     */
    @Override
    public String CloseUpdateCar(ContentValues car) {
        try {
            String result = PHPtools.POST(WEB_URL + "/CloseUpdateCar.php", car);
            return result;
        }
        catch (IOException e) {
            System.out.println("CloseUpdateCar Exception:\n" + e);
            return null;
        }
    }

    /**
     * returns all the clients list
     */
    @Override
    public List<Client> getAllClients() {
        List<Client> result = new ArrayList<Client>();
        try
        {
            String str = PHPtools.GET(WEB_URL + "/allClients.php");
            JSONArray array = new JSONObject(str).getJSONArray("clients:");
            for (int i = 0; i < array.length(); i++)
            {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject); //convert from Json to contentValue
                Client client = RentConst.ContentValuesToClient(contentValues);//convert from contentValue to client
                result.add(client);
            }
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *returns all the branches list
     */
    @Override
    public List<Branch> getAllBranchs() {
        List<Branch> result = new ArrayList<Branch>();
        try
        {
            String str = PHPtools.GET(WEB_URL + "/allBranches.php");
            JSONArray array = new JSONObject(str).getJSONArray("branches:");
            for (int i = 0; i < array.length(); i++)
            {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Branch branch = RentConst.ContentValuesToBranch(contentValues);
                result.add(branch);
            }
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *returns all the cars list
     */
    @Override
    public List<Car> getAllCars() {
        List<Car> result = new ArrayList<Car>();
        try
        {
            String str = PHPtools.GET(WEB_URL + "/allCars.php");
            JSONArray array = new JSONObject(str).getJSONArray("cars:");
            for (int i = 0; i < array.length(); i++)
            {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Car car = RentConst.ContentValuesToCar(contentValues);
                result.add(car);
            }
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *returns all the available cars list
     */
    @Override
    public List<Car> getAllAvailableCars() {
        List<Car> result = new ArrayList<Car>();
        try {
            String str = PHPtools.GET(WEB_URL + "/allAvailableCars.php");
            JSONArray array = new JSONObject(str).getJSONArray("Available cars:");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Car car = RentConst.ContentValuesToCar(contentValues);
                result.add(car);
            }
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     returns all the available cars in specific branch
     */
    @Override
    public List<Car> getAvailableCarsByBranch(ContentValues branchNumber) {
        List<Car> result = new ArrayList<Car>();
        try
        {
            String str = PHPtools.POST(WEB_URL + "/availableCarsByBranch.php",branchNumber);
            if (str.equals("0 results"))//if there are no available cars in this branch
                return result;
            JSONArray array = new JSONObject(str).getJSONArray("Available cars in branch:");
            for (int i = 0; i < array.length(); i++)
            {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Car car = RentConst.ContentValuesToCar(contentValues);
                result.add(car);
            }
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     returns all the open orders
     */
    @Override
    public List<Order> getAllOpenOrders() {
        List<Order> result = new ArrayList<Order>();
        try {
            String str = PHPtools.GET(WEB_URL + "/allOpenOrders.php");
            if (str.equals("0 results")) //if there are no open orders
                return result;
            JSONArray array = new JSONObject(str).getJSONArray("Open Orders:");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Order order = RentConst.ContentValuesToOrder(contentValues);
                result.add(order);
            }
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}