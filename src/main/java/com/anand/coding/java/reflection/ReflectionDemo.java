package com.anand.coding.java.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * RunTimeInfo Reflection
 */
class ReflectionDemo {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) {

        String className;

        System.out.println("Enter class name (java.util.Date, ReflectionDemo etc...)");
        className = scanner.nextLine();

        Class clazz;
        try {
            clazz = Class.forName(className);
        } catch(ClassNotFoundException e) {
            System.out.println("Class not found: " + className);
            e.printStackTrace();
            return;
        }

        Class superClazz = clazz.getSuperclass();

        System.out.println("Class: " + clazz.getName());

        if(superClazz != Object.class) {
            System.out.println("Extends: " + superClazz.getName());
        }

        System.out.println("\nAll Declared Constructors: ");
        printConstructors(clazz);

        System.out.println("\nAll Declared Methods: ");
        printMethods(clazz);

        System.out.println("\nAll Declared Fields: ");
        printFields(clazz);

        System.out.println("\nThe class: " + clazz);

        System.exit(0);
    }

    /**
     * Print all the constructors of a class
     */
    public static void printConstructors(Class clazz) {

        for (Constructor constructor: clazz.getDeclaredConstructors()){

            System.out.print(Modifier.toString(constructor.getModifiers()) +
                    " " + constructor.getName() + "(");

            if(constructor.getParameterTypes().length!=0) {
                for (Class parameterType : constructor.getParameterTypes()) {
                    System.out.print(parameterType.getName() + ", ");
                }
                System.out.println("\b\b)");
            }
        }
    }

    /**
     * Print all the methods of a class
     */
    public static void printMethods(Class clazz) {

        for(Method method: clazz.getDeclaredMethods()){

            System.out.print(Modifier.toString(method.getModifiers()) +
                    " " + method.getReturnType().getName() +
                    " " + method.getName() + "(");

            if(method.getParameterTypes().length!=0) {
                for (Class parameterType : method.getParameterTypes()) {
                    System.out.print(parameterType.getName() + ", ");
                }
                System.out.println("\b\b)");
            }
        }
    }

    /**
     * Print all the fields of a class
     */
    public static void printFields(Class clazz) {

        for(Field field: clazz.getDeclaredFields()){
            System.out.println(Modifier.toString(field.getModifiers()) +
                    " " + field.getType().getName() +
                    " " + field.getName() + ";");
        }
    }
}
