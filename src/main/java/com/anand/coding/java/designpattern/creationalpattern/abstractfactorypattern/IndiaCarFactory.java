package com.anand.coding.java.designpattern.creationalpattern.abstractfactorypattern;

/**
 *
 */
public class IndiaCarFactory {

    //Car properties in India
    final static Location location = Location.INDIA;
    final static StearingType stearingType = StearingType.RIGHT;

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
