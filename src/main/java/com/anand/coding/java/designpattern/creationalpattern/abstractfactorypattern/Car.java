package com.anand.coding.java.designpattern.creationalpattern.abstractfactorypattern;

/**
 *
 */
public abstract class Car {
    private CarType type;
    private Location location;
    private StearingType stearingType;

    public Car(CarType type, Location location, StearingType stearingType) {
        this.type = type;
        this.location = location;
        this.stearingType = stearingType;
    }

    public abstract void construct();

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public StearingType getStearingType() {
        return stearingType;
    }

    public void setStearingType(StearingType stearingType) {
        this.stearingType = stearingType;
    }

    @Override
    public String toString() {
        return "CarType: " + type + ", StearingType: " + stearingType + ", Location: " + location + "\n";
    }
}