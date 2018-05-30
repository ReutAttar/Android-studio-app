package com.example.inbal.android5778_0920_9377_01.model.backend;

import android.content.ContentValues;

import com.example.inbal.android5778_0920_9377_01.model.entities.Address;
import com.example.inbal.android5778_0920_9377_01.model.entities.Branch;
import com.example.inbal.android5778_0920_9377_01.model.entities.Car;
import com.example.inbal.android5778_0920_9377_01.model.entities.CarModel;
import com.example.inbal.android5778_0920_9377_01.model.entities.Client;
import com.example.inbal.android5778_0920_9377_01.model.entities.Enums.Color;
import com.example.inbal.android5778_0920_9377_01.model.entities.Enums.Fuel;
import com.example.inbal.android5778_0920_9377_01.model.entities.Enums.GearBox;
import com.example.inbal.android5778_0920_9377_01.model.entities.Enums.Seats;
import com.example.inbal.android5778_0920_9377_01.model.entities.Enums.Status;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


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
    public static class CarModelConst
    {
        public static final String CODE_MODEL = "codeModel";
        public static final String COMPANY_NAME = "companyName";
        public static final String MODEL_NAME = "modelName";
        public static final String ENGINE_CAPACITY = "engineCapacity";
        public static final String GEARBOX = "gearbox";
        public static final String SEATS_NUMBER = "seatsNumber";
        public static final String COLOR="color";
        public static final String FUEL_TYPE="fuelType";
    }
    public static ContentValues CarModelToContentValues(CarModel carModel)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CarModelConst.CODE_MODEL, carModel.getCodeModel());
        contentValues.put(CarModelConst.COMPANY_NAME, carModel.getCompanyName());
        contentValues.put(CarModelConst.MODEL_NAME, carModel.getModelName());
        contentValues.put(CarModelConst.ENGINE_CAPACITY, carModel.getEngineCapacity());
        contentValues.put(CarModelConst.GEARBOX, String.valueOf(carModel.getGearbox()));
        contentValues.put(CarModelConst.SEATS_NUMBER, String.valueOf(carModel.getSeatsNumber()));
        contentValues.put(CarModelConst.FUEL_TYPE, String.valueOf(carModel.getFuelType()));
        contentValues.put(CarModelConst.COLOR,String.valueOf(carModel.getColor()));
        return contentValues;
    }
    public static CarModel ContentValuesToCarModel(ContentValues contentValues)
    {
        CarModel car = new CarModel();
        car.setCodeModel(contentValues.getAsString(CarModelConst.CODE_MODEL));
        car.setCompanyName(contentValues.getAsString(CarModelConst.COMPANY_NAME));
        car.setModelName(contentValues.getAsString(CarModelConst.MODEL_NAME));
        car.setEngineCapacity(contentValues.getAsInteger(CarModelConst.ENGINE_CAPACITY));
        car.setGearbox(GearBox.valueOf(contentValues.getAsString(CarModelConst.GEARBOX)));
        car.setSeatsNumber(Seats.valueOf(contentValues.getAsString(CarModelConst.SEATS_NUMBER)));
        car.setColor(Color.valueOf(contentValues.getAsString(CarModelConst.COLOR)));
        car.setFuelType(Fuel.valueOf(contentValues.getAsString(CarModelConst.FUEL_TYPE)));
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
}
