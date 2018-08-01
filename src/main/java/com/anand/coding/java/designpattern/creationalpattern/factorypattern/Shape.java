package com.anand.coding.java.designpattern.creationalpattern.factorypattern;

/**
 *
 */
public interface Shape {

    /**
     *
     */
    void draw();

    /**
     * Factory method to create objects of different types.
     *
     * Note: static vs default: static method can't be overridden in implementation classes.
     *
     * @param shapeType
     * @return
     */
    static Shape getShapeObject(ShapeType shapeType){

        Shape shapeObject;

        switch (shapeType){
            case CIRCLE:
                shapeObject =  new Circle();
                break;
            case RECTANGLE:
                shapeObject = new Rectangle();
                break;
            case SQUARE:
                shapeObject = new Square();
                break;
            default:
                shapeObject = null;
        }

        return shapeObject;
    }
}