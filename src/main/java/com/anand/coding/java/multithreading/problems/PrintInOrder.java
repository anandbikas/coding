package com.anand.coding.java.multithreading.problems;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Print 1 2 3 in order using three threads.
 */
public class PrintInOrder{

    private int x=0;

    AtomicInteger atomicInt = new AtomicInteger(0);

    private synchronized void display(int n) throws InterruptedException {

        while(x!=n-1){
            wait();
        }
        System.out.println(n + " ");
        //System.out.print(atomicInt.incrementAndGet() + " ");

        x = (x+1)%3;
        notifyAll();

    }

    public static void main(String[] args){
        PrintInOrder printInOrder = new PrintInOrder();

        IntStream.range(1,4).forEach(n->
            new Thread(() -> {
                try{
                    printInOrder.display(n);
                } catch(InterruptedException e){
                }
            }).start());

    }
}