package com.anand.coding.java.multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * A CountDownLatch can also be used to track count of completed tasks.
 * A CyclicBarrier is used to wait for the number of threads to arrive before doing a task.
 *
 * Ref:
 * <a href="https://www.baeldung.com/java-cyclicbarrier-countdownlatch">...</a>
 */
public class CyclicBarrierAndCountDownLatch {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        // CountDownLatch
        CountDownLatch latch = new CountDownLatch(5);
        IntStream.range(0,5).forEach(x -> {
            Thread thread = new Thread(()-> {
                try {
                    System.out.printf("%s\n",Thread.currentThread().getName());
                } finally {
                    latch.countDown();
                }
            });
            thread.start();
        });

        try {latch.await();} catch (InterruptedException e){}
        System.out.println("CountDownLatch finished.\n");



        // CyclicBarrier
        java.util.concurrent.CyclicBarrier barrier = new java.util.concurrent.CyclicBarrier(5);
        IntStream.range(0,5).forEach(x -> {
            Thread thread = new Thread(()-> {
                try {
                    barrier.await();
                    System.out.println("Barrier resolved, starting task\n.");

                    System.out.printf("%s\n",Thread.currentThread().getName());
                } catch (InterruptedException | BrokenBarrierException ex){}
            });
            thread.start();
        });

    }
}
