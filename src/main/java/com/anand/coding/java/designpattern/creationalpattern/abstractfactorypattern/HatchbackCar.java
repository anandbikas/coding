package com.anand.coding.java.designpattern.creationalpattern.abstractfactorypattern;

/**
 *
 */
public class HatchbackCar extends Car {

    public HatchbackCar(Location location, StearingType stearingType) {
        super(CarType.HATCHBACK, location, stearingType);
        construct();
    }

    @Override
    public void construct() {
        System.out.println("Constructing a car: " + super.toString());
    }
}
