package com.anand.coding.designpattern.creationalpattern.builderpattern.meal.model.drink;

/**
 *
 */
public class Coke extends ColdDrink {

    @Override
    public float price() {
        return 30.0f;
    }

    @Override
    public String name() {
        return "Coke";
    }
}