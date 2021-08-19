package com.anand.coding.designpattern.creationalpattern.singletonpattern;

/**
 *  Double Checked Locking based Java implementation of singleton design pattern
 */
public class SingletonClazz {
    private static SingletonClazz singletonObj;

    private SingletonClazz(){
        super();
    }

    public static SingletonClazz getInstance() {
        if (singletonObj == null) {
            // Make thread safe
            synchronized (SingletonClazz.class) {
                System.out.println("Thread " + Thread.currentThread().getName()
                        + " reached synchronized block for singleton object creation.");
                // check again as multiple threads can reach above step
                if (singletonObj==null) {
                    singletonObj = new SingletonClazz();
                    System.out.println("Singleton Object created.\n");
                }
            }
        }
        return singletonObj;
    }

    public void display(){
        System.out.println("Singleton object displaying. Thread: " + Thread.currentThread().getName());
    }
}
