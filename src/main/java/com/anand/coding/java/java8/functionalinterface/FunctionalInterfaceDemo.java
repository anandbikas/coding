package com.anand.coding.java.java8.functionalinterface;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * A functional interface can contain only one abstract method.
 *
 * Before Java 8, we had to create anonymous inner class objects or implement these interfaces.
 *
 * From Java 8 onwards, lambda expressions can be used to represent the instance of a functional interface.
 * and can have any number of default methods.
 *
 * Example:  Runnable, Comparable
 */
public class FunctionalInterfaceDemo {

    public static void main(String args[]) {

        //Calculator lamda
        Calculator add = (x, y)-> x+y;
        Calculator multiply = (x, y)-> { return x*y;};

        System.out.println(add.calculate(5,4));
        System.out.println(multiply.calculate(5,4));

        //java.util.function package in Java 8 contains many builtin functional interfaces
        // like- Predicate, BinaryOperator
        Predicate<Integer> isEven =  (x) -> x%2==0;
        System.out.println(isEven.test(2));

        Predicate is3 = Predicate.isEqual(3);
        System.out.println(is3.test(3));

        System.out.println(isEven.or(is3).test(6));

        // Create anonymous inner class object
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("New thread created");
                    }
                }).start();

        // statement lambda to create the object
        new Thread(
                ()-> {
                    System.out.println("New thread created");
                }).start();

        // expression lambda to create the object
        new Thread(
                ()-> System.out.println("New thread created")).start();

        // lamda
        Runnable runnableObj = ()-> System.out.println("New thread created");
        runnableObj.run();



        // anonymous inner class with an instance initializer
        // double brace initialization
        ArrayList<Integer> list = new ArrayList<Integer>(){{
            add(1); add(2); add(3); add(4);
        }};

        // lambda expression to print all elements
        list.forEach(n -> System.out.println(n));

        // method reference to print all elements
        list.forEach(System.out::println);

        // Using lambda expression to print even elements
        list.forEach(n -> { if (n%2 == 0) System.out.println(n); });
    }

}
