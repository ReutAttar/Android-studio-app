package com.example.inbal.android5778_0920_9377_01.model.datasource;

import android.content.ContentValues;

import com.example.inbal.android5778_0920_9377_01.model.backend.DB_manager;
import com.example.inbal.android5778_0920_9377_01.model.entities.Address;
import com.example.inbal.android5778_0920_9377_01.model.entities.Branch;
import com.example.inbal.android5778_0920_9377_01.model.entities.Car;
import com.example.inbal.android5778_0920_9377_01.model.entities.CarModel;
import com.example.inbal.android5778_0920_9377_01.model.entities.Client;

import java.util.ArrayList;
import java.util.List;

import static com.example.inbal.android5778_0920_9377_01.model.backend.RentConst.ContentValuesToCar;
import static com.example.inbal.android5778_0920_9377_01.model.backend.RentConst.ContentValuesToCarModel;
import static com.example.inbal.android5778_0920_9377_01.model.backend.RentConst.ContentValuesToClient;

/**
 * Created by inbal on 19/11/2017.
 */

public class List_DBManager implements DB_manager
{
    public static List<Car> cars;
    public static List<Client> clients;
    public static List<CarModel> carsModels;
    public static List<Branch> branches;

    static {
        cars = new ArrayList<>();
        clients = new ArrayList<>();
        carsModels = new ArrayList<>();
        branches = new ArrayList<>();
    }

    @Override
    public boolean clientExist(ContentValues values)
    {
        Client client=ContentValuesToClient(values);
        for(Client c:clients)
            if(c.getID().equals(client.getID()))
                return true;
        return false;
    }

    @Override
    public String addClient(ContentValues values)
    {
        Client client=ContentValuesToClient(values);
        clients.add(client);
        return client.getID();
    }

    @Override
    public String addCarModel(ContentValues values)
    {
        CarModel carModel=ContentValuesToCarModel(values);
        carsModels.add(carModel);
        return carModel.getCodeModel();
    }

    @Override
    public String addCar(ContentValues values)
    {
        Car car=ContentValuesToCar(values);
        cars.add(car);
        return car.getCarNumber();
    }
    public static void initBranches()
    {
        Address a=new Address();
        a.setCity("jer");
        a.setStreet("hatzvi");
        a.setNumber(24);
        Branch b=new Branch();
        b.setBranchNumber("12");
        b.setParkingSpaces(200);
        b.setAddress(a);
        branches.add(b);
        branches.add(b);
        branches.add(b);
    }
    @Override
    public List<CarModel> getAllModels() {
        return carsModels;
    }

    @Override
    public List<Client> getAllClients() {
        return clients;
    }

    @Override
    public List<Branch> getAllBranchs() {return branches;}

    @Override
    public List<Car> getAllCars() {
        return cars;
    }
}
