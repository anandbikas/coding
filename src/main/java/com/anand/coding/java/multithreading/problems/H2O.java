package com.anand.coding.java.multithreading.problems;

import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

/**
 *
 */
public class H2O {

    private final Semaphore hydrogenIn = new Semaphore(2);
    private final Semaphore hydrogenOut = new Semaphore(0);

    public void hydrogen() {
        try {
            hydrogenIn.acquire();
            System.out.print("H ");
            hydrogenOut.release();

        } catch (InterruptedException ignored){}
    }

    public void oxygen() {

        try {
            hydrogenOut.acquire(2);
            System.out.print("O ");
            hydrogenIn.release(2);

        } catch (InterruptedException ignored){}
    }

    public static void main(String[] args) throws Exception{

        H2O h2O = new H2O();
        IntStream.rangeClosed(1,10).forEach(i->new Thread(h2O::oxygen).start());
        IntStream.rangeClosed(1,6).forEach(i->new Thread(h2O::hydrogen).start());
    }
}
