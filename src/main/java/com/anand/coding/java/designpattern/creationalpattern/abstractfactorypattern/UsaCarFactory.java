package com.anand.coding.java.designpattern.creationalpattern.abstractfactorypattern;

/**
 *
 */
public class UsaCarFactory {

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
