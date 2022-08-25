package com.anand.coding.java.multithreading.problems;

/**
 * Print for 1 to n as per the condition.
 *
 * "fizzbuzz" if i is divisible by 3 and 5,
 * "fizz" if i is divisible by 3 and not 5,
 * "buzz" if i is divisible by 5 and not 3, or
 * i if i is not divisible by 3 or 5.
 *
 */
public class FizzBuzz {
    private final int n;
    private int i = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }

    public synchronized void fizz() {
        try {
            while (i <= n) {
                if (i % 3 != 0 || i % 5 == 0) {
                    wait();
                } else {
                    System.out.print("fizz ");
                    i++;
                    notifyAll();
                }
            }
        } catch (InterruptedException ignored){}
    }

    public synchronized void buzz() {
        try {
            while (i <= n) {
                if (i%5 != 0 || i%3 == 0) {
                    wait();
                } else {
                    System.out.print("buzz ");
                    i++;
                    notifyAll();
                }
            }
        } catch (InterruptedException ignored){}
    }

    public synchronized void fizzbuzz() {
        try {
            while (i <= n) {
                if (i%3 != 0 || i%5 != 0) {
                    wait();
                } else {
                    System.out.print("fizzbuzz ");
                    i++;
                    notifyAll();
                }
            }
        } catch (InterruptedException ignored){}
    }

    public synchronized void number() {
        try {
            while (i <= n) {
                if (i%3 == 0 || i%5 == 0) {
                    wait();
                } else {
                    System.out.print(i+ " ");
                    i++;
                    notifyAll();
                }
            }
        } catch (InterruptedException ignored){}
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        FizzBuzz fb = new FizzBuzz(5);

        new Thread(fb::fizz).start();
        new Thread(fb::buzz).start();
        new Thread(fb::fizzbuzz).start();
        new Thread(fb::number).start();
    }
}