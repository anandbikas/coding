package com.anand.coding.java.designpattern.creationalpattern.abstractfactorypattern.car.model;

import com.anand.coding.java.designpattern.creationalpattern.abstractfactorypattern.car.enums.CarType;
import com.anand.coding.java.designpattern.creationalpattern.abstractfactorypattern.car.enums.Location;
import com.anand.coding.java.designpattern.creationalpattern.abstractfactorypattern.car.enums.StearingType;

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
