package com.example.inbal.android5778_0920_9377_01.model.entities;

import com.example.inbal.android5778_0920_9377_01.model.entities.Enums.Status;

/**
 * Created by inbal on 19/11/2017.
 */

public class Car {
    private String branchParkingNumber;
    private String carModel;
    private float kilometers;
    private String carNumber;
    private Status status;
    private int cost;

    public String getBranchParkingNumber() {
        return branchParkingNumber;
    }

    public void setBranchParkingNumber(String branchParkingNumber) {
        this.branchParkingNumber = branchParkingNumber;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public float getKilometers() {
        return kilometers;
    }

    public void setKilometers(float kilometers) {
        this.kilometers = kilometers;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return ("Car model:"+carModel+"\nCar number:"+carNumber+"\nKilometers:"+kilometers+"\nBranch Parking Number: "+ branchParkingNumber+"\nStatus: "+status+"\nCost:"+cost+"\n");
    }

}
