package com.anand.coding.designpattern.creationalpattern.abstractfactorypattern.car.factory;
import com.anand.coding.designpattern.creationalpattern.abstractfactorypattern.car.enums.*;
import com.anand.coding.designpattern.creationalpattern.abstractfactorypattern.car.model.*;

/**
 *
 */
public interface UsaCarFactory {

    //Car properties in USA
    final static Location location = Location.USA;
    final static StearingType stearingType = StearingType.LEFT;

    public static Car buildCar(CarType type){
        Car car;
        switch (type){
            case XUV:
                car = new XuvCar(location, stearingType);
                break;
            case SEDAN:
                car = new SedanCar(location, stearingType);
                break;
            case HATCHBACK:
                car = new HatchbackCar(location, stearingType);
                break;
            default:
                car = null;
        }
        return car;
    }
}
