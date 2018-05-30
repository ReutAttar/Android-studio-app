package com.example.inbal.android5778_0920_9377_01.model.entities;

/**
 * Created by inbal on 19/11/2017.
 */

public class Branch {
    private Address address;
    private int parkingSpaces;
    private String branchNumber;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getParkingSpaces() {
        return parkingSpaces;
    }

    public void setParkingSpaces(int parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }

    public String getBranchNumber() {
        return branchNumber;
    }

    public void setBranchNumber(String branchNumber) {
        this.branchNumber = branchNumber;
    }

    @Override
    public String toString() {
        return ("branch Number: "+ branchNumber+"\n"+address.toString()+"parking Spaces:"+parkingSpaces+"\n");
    }
}
