package com.anand.coding.designpattern.creationalpattern.factorypattern.shape.model;

/**
 *
 */
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}