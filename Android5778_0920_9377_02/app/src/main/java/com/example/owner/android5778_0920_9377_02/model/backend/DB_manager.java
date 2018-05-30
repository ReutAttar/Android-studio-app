package com.example.owner.android5778_0920_9377_02.model.backend;

import android.content.ContentValues;

import com.example.owner.android5778_0920_9377_02.model.entities.Branch;
import com.example.owner.android5778_0920_9377_02.model.entities.Car;
import com.example.owner.android5778_0920_9377_02.model.entities.Client;
import com.example.owner.android5778_0920_9377_02.model.entities.Order;

import java.util.List;

/**
 * Created by owner on 31/12/2017.
 */

public interface DB_manager {
    public boolean clientExist(ContentValues client);
    public String addClient(ContentValues client);
    public String addOrder (ContentValues order);
    public String OpenUpdateCar(String car);
    public String CloseUpdateCar(ContentValues car);
    public List<Client> getAllClients();
    public List<Branch> getAllBranchs();
    public List<Car> getAllCars();
    public List<Car> getAllAvailableCars();
    public List<Car> getAvailableCarsByBranch(ContentValues branch);
    public List<Order> getAllOpenOrders();
    public String closeOrder(ContentValues order);

}
