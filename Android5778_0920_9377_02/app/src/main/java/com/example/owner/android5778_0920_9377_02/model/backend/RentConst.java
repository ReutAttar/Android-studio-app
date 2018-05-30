package com.example.owner.android5778_0920_9377_02.model.backend;

import android.content.ContentValues;

import com.example.owner.android5778_0920_9377_02.model.entities.Address;
import com.example.owner.android5778_0920_9377_02.model.entities.Branch;
import com.example.owner.android5778_0920_9377_02.model.entities.Car;
import com.example.owner.android5778_0920_9377_02.model.entities.Client;
import com.example.owner.android5778_0920_9377_02.model.entities.Enums.Status;
import com.example.owner.android5778_0920_9377_02.model.entities.Order;

import java.sql.Date;


/**
 * Created by inbal on 20/11/2017.
 */

public class RentConst {
    public static class AddressConst
    {
        public static final String CITY = "city";
        public static final String STREET = "street";
        public static final String NUMBER = "number";
    }
    public static ContentValues AddressToContentValues(Address address) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AddressConst.CITY, address.getCity());
        contentValues.put(AddressConst.STREET, address.getStreet());
        contentValues.put(AddressConst.NUMBER, address.getNumber());
        return contentValues;
    }
    public static Address ContentValuesToAddress(ContentValues contentValues)
    {
        Address address = new Address();
        address.setCity(contentValues.getAsString(AddressConst.CITY));
        address.setStreet(contentValues.getAsString(AddressConst.STREET));
        address.setNumber(contentValues.getAsInteger(AddressConst.NUMBER));
        return address;
    }
    public static class BranchConst
    {
        public static final String ADDRESS="address";
        public static final String BRANCH_CITY = "city";
        public static final String BRANCH_STREET = "street";
        public static final String BRANCH_ADDRESS_NUMBER = "number";
        public static final String PARKING_SPACES = "parkingSpaces";
        public static final String BRANCH_NUMBER = "branchNumber";
    }

    public static ContentValues BranchToContentValues(Branch branch) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BranchConst.ADDRESS , (branch.getAddress()).toString());
        contentValues.put(BranchConst.PARKING_SPACES, branch.getParkingSpaces());
        contentValues.put(BranchConst.BRANCH_NUMBER, branch.getBranchNumber());
        return contentValues;
    }
    public static Branch ContentValuesToBranch(ContentValues contentValues)
    {
        Branch branch = new Branch();
        Address a=new Address();
        a.setCity(contentValues.getAsString(BranchConst.BRANCH_CITY));
        a.setStreet(contentValues.getAsString(BranchConst.BRANCH_STREET));
        a.setNumber(contentValues.getAsInteger(BranchConst.BRANCH_ADDRESS_NUMBER ));
        branch.setAddress(a);
        branch.setParkingSpaces(contentValues.getAsInteger(BranchConst.PARKING_SPACES));
        branch.setBranchNumber(contentValues.getAsString(BranchConst.BRANCH_NUMBER));
        return branch;
    }
    public static class CarConst
    {
        public static final String BRANCH_PARKING_NUMBER = "branchParkingNumber";
        public static final String CAR_MODEL = "carModel";
        public static final String KILOMETERS = "kilometers";
        public static final String CAR_NUMBER = "carNumber";
        public static final String STATUS = "status";
        public static final String COST = "cost";
    }
    public static ContentValues CarToContentValues(Car car)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CarConst.BRANCH_PARKING_NUMBER, car.getBranchParkingNumber());
        contentValues.put(CarConst.CAR_MODEL, car.getCarModel());
        contentValues.put(CarConst.KILOMETERS, car.getKilometers());
        contentValues.put(CarConst.CAR_NUMBER, car.getCarNumber());
        contentValues.put(CarConst.STATUS, String.valueOf(car.getStatus()));
        contentValues.put(CarConst.COST,car.getCost());
        return contentValues;
    }
    public static Car ContentValuesToCar(ContentValues contentValues)
    {
        Car car = new Car();
        car.setBranchParkingNumber(contentValues.getAsString(CarConst.BRANCH_PARKING_NUMBER));
        car.setCarModel(contentValues.getAsString(CarConst.CAR_MODEL));
        car.setKilometers(contentValues.getAsInteger(CarConst.KILOMETERS));
        car.setCarNumber(contentValues.getAsString(CarConst.CAR_NUMBER));
        car.setStatus(Status.valueOf(contentValues.getAsString(CarConst.STATUS)));
        car.setCost(contentValues.getAsInteger(CarConst.COST));
        return car;
    }

    public static class ClientConst
    {
        public static final String ID = "_id";
        public static final String LAST_NAME = "lastName";
        public static final String FIRST_NAME = "firstName";
        public static final String PHONE_NUMBER = "phoneNumber";
        public static final String EMAIL = "email";
        public static final String CREDIT_CARD_NUMBER = "creditCardNumber";
    }

    public static ContentValues ClientToContentValues(Client client)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ClientConst.ID, client.getID());
        contentValues.put(ClientConst.LAST_NAME, client.getLastName());
        contentValues.put(ClientConst.FIRST_NAME, client.getFirstName());
        contentValues.put(ClientConst.PHONE_NUMBER, client.getPhoneNumber());
        contentValues.put(ClientConst.EMAIL, client.getEmail());
        contentValues.put(ClientConst.CREDIT_CARD_NUMBER, client.getCreditCardNumber());
        return contentValues;
    }
    public static Client ContentValuesToClient(ContentValues contentValues)
    {
        Client costumer = new Client();
        costumer.setID(contentValues.getAsString(ClientConst.ID));
        costumer.setLastName(contentValues.getAsString(ClientConst.LAST_NAME));
        costumer.setFirstName(contentValues.getAsString(ClientConst.FIRST_NAME));
        costumer.setPhoneNumber(contentValues.getAsString(ClientConst.PHONE_NUMBER));
        costumer.setEmail(contentValues.getAsString(ClientConst.EMAIL));
        costumer.setCreditCardNumber(contentValues.getAsString(ClientConst.CREDIT_CARD_NUMBER));
        return costumer;
    }

    public static class OrderConst
    {
        public static final String ORDER_NUMBER = "_orderNumber";
        public static final String CLIENT_NUMBER = "clientNumber";
        public static final String CAR_NUMBER = "carNumber";
        public static final String OPEN_ORDER = "openOrder";
        public static final String START_RENT = "startRent";
        public static final String END_RENT = "endRent";
        public static final String START_KM = "startKM";
        public static final String END_KM = "endKM";
        public static final String DO_FUEL = "doFuel";
        public static final String LITERS_FUEL = "litersFuel";
        public static final String TOTAL_PAY = "totalPay";
    }

    public static ContentValues OrderToContentValues(Order order)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(OrderConst.ORDER_NUMBER, order.getOrderNumber());
        contentValues.put(OrderConst.CLIENT_NUMBER, order.getClientNumber());
        contentValues.put(OrderConst.CAR_NUMBER, order.getCarNumber());
        contentValues.put(OrderConst.OPEN_ORDER, order.isOpenOrder());
        contentValues.put(OrderConst.START_RENT, String.valueOf(order.getStartRent()));
        contentValues.put(OrderConst.END_RENT, String.valueOf(order.getEndRent()));
        contentValues.put(OrderConst.START_KM, order.getStartKM());
        contentValues.put(OrderConst.END_KM, order.getEndKM());
        contentValues.put(OrderConst.DO_FUEL, order.isDoFuel());
        contentValues.put(OrderConst.LITERS_FUEL, order.getLitersFuel());
        contentValues.put(OrderConst.TOTAL_PAY, order.getTotalPay());
        return contentValues;
    }
    public static Order ContentValuesToOrder(ContentValues contentValues)
    {
        Order order = new Order();
        order.setOrderNumber(contentValues.getAsString(OrderConst.ORDER_NUMBER));
        order.setClientNumber(contentValues.getAsString(OrderConst.CLIENT_NUMBER));
        order.setCarNumber(contentValues.getAsString(OrderConst.CAR_NUMBER));
        order.setOpenOrder(contentValues.getAsBoolean(OrderConst.OPEN_ORDER));
        order.setStartRent(Date.valueOf(contentValues.getAsString(OrderConst.START_RENT)));
        order.setEndRent(Date.valueOf(contentValues.getAsString(OrderConst.END_RENT)));
        order.setStartKM(contentValues.getAsFloat(OrderConst.START_KM));
        order.setEndKM(contentValues.getAsFloat(OrderConst.END_KM));
        order.setDoFuel(contentValues.getAsBoolean(OrderConst.DO_FUEL));
        order.setLitersFuel(contentValues.getAsFloat(OrderConst.LITERS_FUEL));
        order.setTotalPay(contentValues.getAsFloat(OrderConst.TOTAL_PAY));
        return order;
    }

}
