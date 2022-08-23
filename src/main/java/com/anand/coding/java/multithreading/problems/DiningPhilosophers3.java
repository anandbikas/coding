package com.anand.coding.java.multithreading.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 */
class DiningPhilosophers3 {
    private static final int repeat = 10;

    private final int n;
    private final boolean[] forks;

    public DiningPhilosophers3(int n) {
        this.n = n;
        forks = new boolean[n];
    }

    public void wantsToEat(int i) {

        int left = (i+1)%5;
        int right = i;

        try {
            synchronized (this) {
                while (forks[right] || forks[left]) {
                    this.wait();
                }
                forks[left] = forks[right] = true;
            }

            System.out.print(i + " ");

            synchronized (this) {
                forks[left] = forks[right] = false;
                this.notifyAll();
            }
        } catch (InterruptedException ignored){}
    }

    public static void main(String[] args) throws Exception{
        int n=5;
        DiningPhilosophers3 dp = new DiningPhilosophers3(n);

        List<Thread> list = new ArrayList<>();

        for(int i=0; i<repeat; i++) {
            list.addAll(IntStream.range(0, n).mapToObj(x -> new Thread(() -> dp.wantsToEat(x))).collect(Collectors.toList()));
        }

        list.forEach(Thread::start);
        list.forEach(t -> {try { t.join(); } catch (InterruptedException ignored){}});
    }
}