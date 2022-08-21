package com.anand.coding.java.multithreading.problems;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Print 1 2 3 in order using three threads.
 */
public class PrintInOrder{

    private Integer x=1;
    private final AtomicInteger atomicInt = new AtomicInteger(1);

    private synchronized void display(int n) {
        try {
            while (x != n) {
                wait();
            }
            System.out.println(n + " ");

            x++;
            notifyAll();
        } catch (InterruptedException ex) {
        }

    }

    private synchronized void displayAtomicInt() {
        //Synchronized is required for the println method.
        System.out.println(atomicInt.getAndIncrement() + " ");
    }

    public static void main(String[] args) {

        PrintInOrder printInOrder = new PrintInOrder();

        IntStream.rangeClosed(1, 3).forEach(n ->
                new Thread(() -> printInOrder.display(n)).start());

        IntStream.rangeClosed(1,3).forEach(n->
                new Thread(printInOrder::displayAtomicInt).start());
    }
}