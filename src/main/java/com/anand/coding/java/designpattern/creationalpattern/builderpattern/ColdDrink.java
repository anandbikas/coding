package com.anand.coding.java.designpattern.creationalpattern.builderpattern;

/**
 *
 */
public abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }
}