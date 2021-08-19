package com.anand.coding.designpattern.creationalpattern.factorypattern.shape.model;

/**
 *
 */
public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}