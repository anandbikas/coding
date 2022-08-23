package com.anand.coding.java.multithreading.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 */
public class DiningPhilosophers {
    private static final int repeat = 10;

    private final int n;
    private final Object [] forks;
    private final Semaphore philosopher;

    public DiningPhilosophers(int n) {
        this.n = n;
        forks = new Object[n];
        philosopher = new Semaphore(n-1, true);
        IntStream.range(0,n).forEach(i -> forks[i] = new Object());
    }

    public void wantsToEat(int i) {

        try {
            philosopher.acquire();

            synchronized (forks[i]) {
                synchronized (forks[(i + 1) % n]) {
                    System.out.print(i + " ");
                }
            }
        }
        catch (InterruptedException ignored){}
        finally {
            philosopher.release();
        }
    }

    public static void main(String[] args) throws Exception{
        int n=5;
        DiningPhilosophers dp = new DiningPhilosophers(n);

        List<Thread> list = new ArrayList<>();

        for(int i=0; i<repeat; i++) {
            list.addAll(IntStream.range(0, n).mapToObj(x -> new Thread(() -> dp.wantsToEat(x))).collect(Collectors.toList()));
        }

        list.forEach(Thread::start);
        list.forEach(t -> {try { t.join(); } catch (InterruptedException ignored){}});
    }
}