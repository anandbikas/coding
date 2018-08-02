package com.anand.coding.java.designpattern.creationalpattern.abstractfactorypattern;

/**
 *
 */
public class SedanCar extends Car {

    public SedanCar(Location location, StearingType stearingType) {
        super(CarType.SEDAN, location, stearingType);
        construct();
    }

    @Override
    public void construct() {
        System.out.println("Constructing a car: " + super.toString());
    }
}
