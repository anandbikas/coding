package com.anand.coding.java.designpattern.creationalpattern.builderpattern;

/**
 *
 */
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }
}