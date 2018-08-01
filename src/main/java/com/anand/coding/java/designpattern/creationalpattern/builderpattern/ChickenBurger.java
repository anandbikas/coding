package com.anand.coding.java.designpattern.creationalpattern.builderpattern;

/**
 *
 */
public class ChickenBurger extends Burger {

    @Override
    public float price() {
        return 50.5f;
    }

    @Override
    public String name() {
        return "Chicken Burger";
    }
}