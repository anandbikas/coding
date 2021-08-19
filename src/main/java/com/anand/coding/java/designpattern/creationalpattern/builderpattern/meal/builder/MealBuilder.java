package com.anand.coding.java.designpattern.creationalpattern.builderpattern.meal.builder;

import com.anand.coding.java.designpattern.creationalpattern.builderpattern.meal.model.*;
import com.anand.coding.java.designpattern.creationalpattern.builderpattern.meal.model.drink.Coke;
import com.anand.coding.java.designpattern.creationalpattern.builderpattern.meal.model.drink.Pepsi;
import com.anand.coding.java.designpattern.creationalpattern.builderpattern.meal.model.food.ChickenBurger;
import com.anand.coding.java.designpattern.creationalpattern.builderpattern.meal.model.food.VegBurger;

/**
 *
 */
public class MealBuilder {

    public Meal prepareVegMeal (){
        return new Meal(){{
            addItem(new VegBurger());
            addItem(new Coke());
        }};
    }

    public Meal prepareNonVegMeal (){
        return new Meal(){{
            addItem(new ChickenBurger());
            addItem(new Pepsi());
        }};
    }
}