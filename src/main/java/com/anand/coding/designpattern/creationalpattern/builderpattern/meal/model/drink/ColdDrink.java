package com.anand.coding.designpattern.creationalpattern.builderpattern.meal.model.drink;

import com.anand.coding.designpattern.creationalpattern.builderpattern.meal.model.Item;
import com.anand.coding.designpattern.creationalpattern.builderpattern.meal.model.packing.Bottle;
import com.anand.coding.designpattern.creationalpattern.builderpattern.meal.model.packing.Packing;

/**
 *
 */
public abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }
}