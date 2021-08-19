package com.anand.coding.designpattern.creationalpattern.factorypattern.shape;

import com.anand.coding.designpattern.creationalpattern.factorypattern.shape.model.ShapeType;
import com.anand.coding.designpattern.creationalpattern.factorypattern.shape.model.Shape;

/**
 *
 */
public class ShapeFactoryDemo {

    public static void main(String[] args) {

        Shape circle = Shape.getShapeObject(ShapeType.CIRCLE);
        circle.draw();

        Shape rectangle = Shape.getShapeObject(ShapeType.RECTANGLE);
        rectangle.draw();

        Shape square = Shape.getShapeObject(ShapeType.SQUARE);
        square.draw();
    }
}