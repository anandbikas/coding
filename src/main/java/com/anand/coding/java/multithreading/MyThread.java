package com.anand.coding.java.multithreading;

class MyThread implements Runnable
{
    private String name;
    private Thread thread;

    private static final int SLEEP_SECONDS=1;
    private static final int SLEEP_MILLI_SECONDS=SLEEP_SECONDS*1000;


    /**
     *
     * @param name
     */
    public MyThread(final String name) {
        this.name = name;
        thread = new Thread(this, name);

        System.out.println("Child thread started: " + thread);
        thread.start();
    }

    /**
     *
     */
    public void run() {
        try {
            for(int i=15; i>0; i--) {
                System.out.println("Child thread: " + name  + " " + i);
                Thread.sleep(SLEEP_MILLI_SECONDS);
            }

        } catch(InterruptedException ie) {
            System.out.println("Child thread interrupted: " + name);
            ie.printStackTrace();
        }

        System.out.println("Child thread exiting: " + name);
    }

    /**
     * main function to test MyThread
     *
     * @param args
     */
    public static void main(String[] args) {

        MyThread myThread1 = new MyThread("A");
        MyThread myThread2 = new MyThread("B");
        MyThread myThread3 = new MyThread("C");

        try {
            for(int i = 10; i>0; i--) {
                System.out.println("Main thread: " + i);
                Thread.sleep(SLEEP_MILLI_SECONDS);
            }
        } catch(InterruptedException ie) {
            System.out.println("Main thread Interrupted");
            ie.printStackTrace();
        }

        System.out.println("Waiting for child threads to finish...\n");

        try {
            if(myThread1.thread.isAlive()) {
                myThread1.thread.join();
            }
            if(myThread2.thread.isAlive()){
                myThread2.thread.join();
            }
            if(myThread3.thread.isAlive()){
                myThread3.thread.join();
            }

        } catch(InterruptedException ie) {
            System.out.println("Thread Join failed. ");
            ie.printStackTrace();
        }

        System.out.println("Main thread exiting");
    }
}