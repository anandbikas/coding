package com.anand.coding.java.multithreading.problems;

/**
 *
 */
class ZeroEvenOdd {

    private final int n;
    private int count = 1;

    private boolean printZero=true;

    private final int evenEnd;
    private final int oddEnd;

    public ZeroEvenOdd(int n) {
        this.n = n;
        evenEnd = n%2==0 ? n : n-1;
        oddEnd  = n%2==1 ? n : n-1;
    }

    public synchronized void zero() {

        try {
            for(int i=0; i<n; i++) {
                while (!printZero) wait();

                System.out.print(0);
                printZero = false;
                notifyAll();
            }
        } catch (InterruptedException ignored){}
    }

    public synchronized void even() {

        try {
            while(count<=evenEnd) {
                while (printZero || count%2 == 1) wait();

                System.out.print(count++);
                printZero = true;
                notifyAll();
            }
        } catch (InterruptedException ignored){}
    }

    public synchronized void odd() {
        try {
            while(count<=oddEnd) {
                while (printZero || count % 2 == 0) wait();

                System.out.print(count++);
                printZero = true;
                notifyAll();
            }
        } catch (InterruptedException ignored){}
    }

    public static void main(String[] args) {

        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(5);

        new Thread(zeroEvenOdd::zero).start();
        new Thread(zeroEvenOdd::odd).start();
        new Thread(zeroEvenOdd::even).start();
    }
}