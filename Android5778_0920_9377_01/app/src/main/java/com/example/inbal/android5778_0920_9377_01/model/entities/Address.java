package com.example.inbal.android5778_0920_9377_01.model.entities;

/**
 * Created by inbal on 19/11/2017.
 */

public class Address {
    private String city;
    private String street;
    private int number;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String toString()
    {
        return(street+" "+number+", "+city+"\n");
    }
}
