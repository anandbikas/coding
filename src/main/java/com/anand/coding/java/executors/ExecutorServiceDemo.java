package com.anand.coding.java.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 *
 */
public class ExecutorServiceDemo {

    private static final int THREAD_COUNT = 5;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    //TODO: More examples.

    /**
     *
     * @param arts
     */
    public static void main(String arts[]){

        IntStream.range('A', 'Z').forEach(ch -> {

            executorService.submit(()->{
                System.out.print((char)ch  + "  ");
            });
        });
    }
}
