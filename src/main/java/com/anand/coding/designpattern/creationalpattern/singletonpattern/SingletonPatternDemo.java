package com.anand.coding.designpattern.creationalpattern.singletonpattern;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 *
 */
public class SingletonPatternDemo {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        List<Thread> childThreads = new ArrayList<>();

        IntStream.range(1, 4).forEach(threadName->{
                Thread thread = new Thread(() -> {
                    System.out.println("Child thread started: " + threadName);

                    try {
                        for (int i = 15; i > 0; i--) {
                            SingletonClazz.getInstance().display();
                            Thread.sleep((long) (2 * Math.random()));
                        }
                    } catch (InterruptedException ignored) {
                    }

                    System.out.println("\nChild thread exiting: " + threadName);
                }, "Thread-" + threadName);

                childThreads.add(thread);
                thread.start();
            });

        try {
            for (int i = 10; i > 0; i--) {
                SingletonClazz.getInstance().display();
                Thread.sleep((long)(1* Math.random()));
            }

            System.out.println("Waiting for child threads to finish...\n");
            for(Thread t: childThreads){
                t.join();
            }
        }catch(InterruptedException ignored){}

        System.out.println("\nMain thread exiting");
    }
}
