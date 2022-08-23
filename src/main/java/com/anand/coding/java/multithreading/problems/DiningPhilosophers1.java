package com.anand.coding.java.multithreading.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 */
class DiningPhilosophers1 {
    private static final int repeat = 10;

    private final int n;
    private final Semaphore [] forks;
    private final Semaphore philosopher;

    public DiningPhilosophers1(int n) {
        this.n = n;
        forks = new Semaphore[n];
        philosopher = new Semaphore(n-1, true);
        IntStream.range(0,n).forEach(i -> forks[i] = new Semaphore(1));
    }

    public void wantsToEat(int i) {

        try {
            philosopher.acquire();

            forks[i].acquire();
            forks[(i+1)%n].acquire();

            System.out.print(i + " ");
        }
        catch (InterruptedException ignored){}
        finally {
            forks[i].release();
            forks[(i+1)%n].release();

            philosopher.release();
        }
    }

    public static void main(String[] args) throws Exception{
        int n=5;
        DiningPhilosophers1 dp = new DiningPhilosophers1(n);

        List<Thread> list = new ArrayList<>();

        for(int i=0; i<repeat; i++) {
            list.addAll(IntStream.range(0, n).mapToObj(x -> new Thread(() -> dp.wantsToEat(x))).collect(Collectors.toList()));
        }

        list.forEach(Thread::start);
        list.forEach(t -> {try { t.join(); } catch (InterruptedException ignored){}});
    }
}