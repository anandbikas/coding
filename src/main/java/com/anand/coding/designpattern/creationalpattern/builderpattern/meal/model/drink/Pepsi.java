package com.anand.coding.designpattern.creationalpattern.builderpattern.meal.model.drink;

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