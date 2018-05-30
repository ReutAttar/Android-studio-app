package com.example.inbal.android5778_0920_9377_01.model.entities;

import com.example.inbal.android5778_0920_9377_01.model.entities.Enums.Color;
import com.example.inbal.android5778_0920_9377_01.model.entities.Enums.Fuel;
import com.example.inbal.android5778_0920_9377_01.model.entities.Enums.GearBox;
import com.example.inbal.android5778_0920_9377_01.model.entities.Enums.Seats;

/**
 * Created by inbal on 19/11/2017.
 */

public class CarModel {
    private String codeModel;
    private String companyName;
    private String modelName;
    private int engineCapacity;
    private GearBox gearbox;
    private Seats seatsNumber;
    private Fuel fuelType;
    private Color color;

    public String getCodeModel() {
        return codeModel;
    }

    public void setCodeModel(String codeModel) {
        this.codeModel = codeModel;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {this.modelName = modelName;}

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public GearBox getGearbox() {
        return gearbox;
    }

    public void setGearbox(GearBox gearbox) {
        this.gearbox = gearbox;
    }

    public Seats getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(Seats seatsNumber) {this.seatsNumber = seatsNumber;}

    public Fuel getFuelType() {return fuelType;}

    public void setFuelType(Fuel fuelType) {this.fuelType = fuelType;}

    public Color getColor() {return color;}

    public void setColor(Color color) {this.color = color;}

    @Override
    public String toString() {
        return "code Model: "+codeModel+"\ncompany Name: "+companyName+"\nmodel Name: "+modelName+"\nengine Capacity: "+engineCapacity+"\ngearbox: "+ gearbox+"\nfuel type: "+fuelType+"\nseats Number: "+ seatsNumber+"\ncolor:"+color+"\n";
    }
}
