package com.example.owner.android5778_0920_9377_02.model.entities;

/**
 * Created by inbal on 19/11/2017.
 */

public class Client {
    private String lastName;
    private String firstName;
    private String ID;
    private String phoneNumber;
    private String email;
    private String creditCardNumber;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    @Override
    public String toString() {
        return ("ID: "+ID+"  First name: "+firstName+"  Last name: "+lastName+"\nPhone number: "+phoneNumber+"\nEmail: "+email+"\nCredit card number: "+creditCardNumber+"\n");
    }
}
