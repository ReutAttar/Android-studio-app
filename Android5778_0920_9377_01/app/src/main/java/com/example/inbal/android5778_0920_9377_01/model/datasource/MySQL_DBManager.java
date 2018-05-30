package com.example.inbal.android5778_0920_9377_01.model.datasource;

import android.content.ContentValues;

import com.example.inbal.android5778_0920_9377_01.model.backend.DB_manager;
import com.example.inbal.android5778_0920_9377_01.model.backend.RentConst;
import com.example.inbal.android5778_0920_9377_01.model.entities.Branch;
import com.example.inbal.android5778_0920_9377_01.model.entities.Car;
import com.example.inbal.android5778_0920_9377_01.model.entities.CarModel;
import com.example.inbal.android5778_0920_9377_01.model.entities.Client;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by owner on 19/12/2017.
 */

public class MySQL_DBManager implements DB_manager{

    private String WEB_URL="http://rattar.vlab.jct.ac.il/TAKE%26GO";

    @Override
    public boolean clientExist(ContentValues value) {
        try {
            String result = PHPtools.POST(WEB_URL + "/checkClient.php", value);
            if (result.equals("1"))
                return true;
            else return false;
        }
        catch (IOException e) {
            System.out.println("checkClient Exception:\n" + e);
            return false;
        }
    }

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

    @Override
    public String addCarModel(ContentValues carModel) {
        try {
            String result = PHPtools.POST(WEB_URL + "/addCarModel.php", carModel);
            return result;     }
        catch (IOException e) {
            System.out.println("addCarModel Exception:\n" + e);
            return null;
        }
    }

    @Override
    public String addCar(ContentValues car) {
        try {
            String result = PHPtools.POST(WEB_URL + "/addCar.php", car);
            return result;     }
        catch (IOException e) {
            System.out.println("addCar Exception:\n" + e);
            return null;
        }
    }

    @Override
    public List<CarModel> getAllModels() {
        List<CarModel> result = new ArrayList<CarModel>();
        try
        {
            String str = PHPtools.GET(WEB_URL + "/allCarsModels.php");
            JSONArray array = new JSONObject(str).getJSONArray("cars models:");
            for (int i = 0; i < array.length(); i++)
            {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                CarModel carModel = RentConst.ContentValuesToCarModel(contentValues);
                result.add(carModel);
            }
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

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
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Client client = RentConst.ContentValuesToClient(contentValues);
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
}
