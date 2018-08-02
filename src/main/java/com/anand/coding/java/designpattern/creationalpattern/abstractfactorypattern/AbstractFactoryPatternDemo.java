package com.anand.coding.java.designpattern.creationalpattern.abstractfactorypattern;

/**
 *
 */
public class AbstractFactoryPatternDemo {

    public static void main(String[] args) {

        CarFactory.buildCar(Location.INDIA, CarType.SEDAN);
        CarFactory.buildCar(Location.USA, CarType.XUV);
        CarFactory.buildCar(Location.INDIA, CarType.HATCHBACK);
    }

}
