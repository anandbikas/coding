package com.anand.coding.java.designpattern.creationalpattern.factorypattern;

/**
 *
 */
public class FactoryPatternDemo {

    public static void main(String[] args) {

        Shape circle = Shape.getShapeObject(ShapeType.CIRCLE);
        circle.draw();

        Shape rectangle = Shape.getShapeObject(ShapeType.RECTANGLE);
        rectangle.draw();

        Shape square = Shape.getShapeObject(ShapeType.SQUARE);
        square.draw();
    }
}