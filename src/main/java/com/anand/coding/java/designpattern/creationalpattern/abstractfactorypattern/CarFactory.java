package com.anand.coding.java.designpattern.creationalpattern.abstractfactorypattern;

/**
 *
 */
public abstract class CarFactory {

    public static Car buildCar(Location location, CarType type){
        Car car;
        switch (location){
            case INDIA:
                car = IndiaCarFactory.buildCar(type);
                break;
            case USA:
                car = UsaCarFactory.buildCar(type);
                break;
            default:
                car = null;
        }
        return car;
    }
}
