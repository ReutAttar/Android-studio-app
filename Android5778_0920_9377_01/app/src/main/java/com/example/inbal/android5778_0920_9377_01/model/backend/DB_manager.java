package com.example.inbal.android5778_0920_9377_01.model.backend;

import android.content.ContentValues;

import com.example.inbal.android5778_0920_9377_01.model.entities.Branch;
import com.example.inbal.android5778_0920_9377_01.model.entities.Car;
import com.example.inbal.android5778_0920_9377_01.model.entities.CarModel;
import com.example.inbal.android5778_0920_9377_01.model.entities.Client;

import java.util.List;

/**
 * Created by inbal on 19/11/2017.
 */

public interface DB_manager {
    public boolean clientExist(ContentValues client);
    public String addClient (ContentValues client);
    public String addCarModel(ContentValues carModel);
    public String addCar(ContentValues car);
    public List<CarModel> getAllModels();
    public List<Client> getAllClients();
    public List<Branch> getAllBranchs();
    public List<Car> getAllCars();
}
