package com.anand.coding.java.designpattern.creationalpattern.builderpattern;

/**
 *
 */
public class Pepsi extends ColdDrink {

    @Override
    public float price() {
        return 35.0f;
    }

    @Override
    public String name() {
        return "Pepsi";
    }
}