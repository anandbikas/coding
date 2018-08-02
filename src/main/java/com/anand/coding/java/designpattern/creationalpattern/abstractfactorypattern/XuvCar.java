package com.anand.coding.java.designpattern.creationalpattern.abstractfactorypattern;

/**
 *
 */
public class XuvCar extends Car {


    public XuvCar(Location location, StearingType stearingType) {
        super(CarType.XUV, location, stearingType);
        construct();
    }

    @Override
    public void construct() {
        System.out.println("Constructing a car: " + super.toString());
    }
}
