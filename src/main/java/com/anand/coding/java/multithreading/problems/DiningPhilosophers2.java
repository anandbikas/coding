package com.anand.coding.java.multithreading.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 */
class DiningPhilosophers2 {
    private static final int repeat = 10;

    private final int n;
    private final Semaphore [] forks;

    public DiningPhilosophers2(int n) {
        this.n = n;
        forks = new Semaphore[n];
        IntStream.range(0,n).forEach(i -> forks[i] = new Semaphore(1));
    }

    public void wantsToEat(int i) {

        int right = i; int left = (i+1) % 5;

        try {
            if (i == 0 ) {
                forks[left].acquire();
                forks[right].acquire();
            } else {
                forks[right].acquire();
                forks[left].acquire();
            }

            System.out.print(i + " ");
        }
        catch (InterruptedException ignored){}
        finally {
            forks[left].release();
            forks[right].release();
        }
    }

    public static void main(String[] args) throws Exception{
        int n=5;
        DiningPhilosophers2 dp = new DiningPhilosophers2(n);

        List<Thread> list = new ArrayList<>();

        for(int i=0; i<repeat; i++) {
            list.addAll(IntStream.range(0, n).mapToObj(x -> new Thread(() -> dp.wantsToEat(x))).collect(Collectors.toList()));
        }

        list.forEach(Thread::start);
        list.forEach(t -> {try { t.join(); } catch (InterruptedException ignored){}});
    }
}