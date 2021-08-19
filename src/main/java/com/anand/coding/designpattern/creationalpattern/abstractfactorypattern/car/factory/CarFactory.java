package com.anand.coding.designpattern.creationalpattern.abstractfactorypattern.car.factory;

import com.anand.coding.designpattern.creationalpattern.abstractfactorypattern.car.enums.*;
import com.anand.coding.designpattern.creationalpattern.abstractfactorypattern.car.model.Car;

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
