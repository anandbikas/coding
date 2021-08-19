package com.anand.coding.designpattern.creationalpattern.builderpattern.meal.model;

import com.anand.coding.designpattern.creationalpattern.builderpattern.meal.model.packing.Packing;

/**
 *
 */
public interface Item {

    String name();
    Packing packing();
    float price();

    /**
     * 1. Interface doesn't allow concrete implementation, hence abstract class can be used instead
     * 2. Class can extend only one class whereas can implement multiple interfaces.
     * 3. Java8 provide default method in interface.
     * 3. To avoid diamond problem (if implementing more than one interfaces with same default method),
     *    it is mandatory to override the default method.
     *
     * 4. Interface members are public by default
     * 5. interface methods are abstract by default
     * 6. variables in interface are by default static and final.
     */
    default void display(){
        System.out.println(
                "Item : " + name() +
                ", Packing : " + packing().pack() +
                ", Price : " + price()
        );
    }
}