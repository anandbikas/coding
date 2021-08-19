package com.anand.coding.designpattern.creationalpattern.builderpattern.meal.model.food;

import com.anand.coding.designpattern.creationalpattern.builderpattern.meal.model.Item;
import com.anand.coding.designpattern.creationalpattern.builderpattern.meal.model.packing.Packing;
import com.anand.coding.designpattern.creationalpattern.builderpattern.meal.model.packing.Wrapper;

/**
 *
 */
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }
}