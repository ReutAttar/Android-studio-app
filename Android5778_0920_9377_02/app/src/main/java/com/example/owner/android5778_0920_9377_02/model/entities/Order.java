package com.example.owner.android5778_0920_9377_02.model.entities;

import java.util.Date;

/**
 * Created by inbal on 19/11/2017.
 */

public class Order {
    private String OrderNumber;
    private String clientNumber;
    private boolean openOrder;
    private String carNumber;
    private Date startRent;
    private Date endRent;
    private float startKM;
    private float endKM;
    private boolean doFuel;
    private float litersFuel;
    private float totalPay;

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String OrderNumber) {this.OrderNumber = OrderNumber;}

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Date getStartRent() {
        return startRent;
    }

    public void setStartRent(Date startRent) {
        this.startRent = startRent;
    }

    public Date getEndRent() {
        return endRent;
    }

    public void setEndRent(Date endRent) {
        this.endRent = endRent;
    }

    public float getStartKM() {
        return startKM;
    }

    public void setStartKM(float startKM) {
        this.startKM = startKM;
    }

    public float getEndKM() {
        return endKM;
    }

    public void setEndKM(float endKM) {
        this.endKM = endKM;
    }

    public float getLitersFuel() {
        return litersFuel;
    }

    public void setLitersFuel(float litersFuel) {
        this.litersFuel = litersFuel;
    }

    public float getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(float totalPay) {
        this.totalPay = totalPay;
    }

    public boolean isOpenOrder() {
        return openOrder;
    }

    public void setOpenOrder(boolean openOrder) {
        this.openOrder = openOrder;
    }

    public boolean isDoFuel() {
        return doFuel;
    }

    public void setDoFuel(boolean doFuel) {
        this.doFuel = doFuel;
    }

    @Override
    public String toString() {
        return ("Order Number: "+OrderNumber+"\nclient Number: "+clientNumber+"\ndo Order? "+ openOrder +"\ncar Number: "+carNumber+"\nstart Rent: "+startRent+"  end Rent: "+endRent
        +"\nstart KM: "+startKM+"  end KM: "+endKM+(doFuel?" "+litersFuel:"not fuel")+"\ntotal Pay: "+totalPay+"\n");
    }
}
