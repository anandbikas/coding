package com.anand.coding.designpattern.creationalpattern.abstractfactorypattern.car.model;

import com.anand.coding.designpattern.creationalpattern.abstractfactorypattern.car.enums.CarType;
import com.anand.coding.designpattern.creationalpattern.abstractfactorypattern.car.enums.Location;
import com.anand.coding.designpattern.creationalpattern.abstractfactorypattern.car.enums.StearingType;

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
