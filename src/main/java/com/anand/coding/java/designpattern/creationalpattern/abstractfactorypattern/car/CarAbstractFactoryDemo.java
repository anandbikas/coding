package com.anand.coding.java.designpattern.creationalpattern.abstractfactorypattern.car;

import com.anand.coding.java.designpattern.creationalpattern.abstractfactorypattern.car.factory.CarFactory;
import com.anand.coding.java.designpattern.creationalpattern.abstractfactorypattern.car.enums.*;

/**
 *
 */
public class CarAbstractFactoryDemo {

    public static void main(String[] args) {

        CarFactory.buildCar(Location.INDIA, CarType.SEDAN);
        CarFactory.buildCar(Location.USA, CarType.XUV);
        CarFactory.buildCar(Location.INDIA, CarType.HATCHBACK);
    }

}
