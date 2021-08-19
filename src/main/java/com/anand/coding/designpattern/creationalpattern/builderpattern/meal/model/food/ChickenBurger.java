package com.anand.coding.designpattern.creationalpattern.builderpattern.meal.model.food;

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