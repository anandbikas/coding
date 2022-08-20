package com.anand.coding.java.multithreading;

import java.util.stream.IntStream;

/**
 * Daemon threads are meant to serve user threads and are only needed while user threads are running,
 * they won't prevent the JVM from exiting once all user threads have finished their execution.
 *
 * Ref:
 * <a href="https://www.baeldung.com/java-daemon-thread">...</a>
 */
public class DaemonThreads {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        IntStream.range(0,10).forEach(x -> {
            Thread thread = new Thread(()-> {
                System.out.printf("Thread: %s, %s\n", x, Thread.currentThread().getName());
            });
            thread.setDaemon(true);
            thread.start();
        });

        System.out.println("Main Exited");

    }
}
