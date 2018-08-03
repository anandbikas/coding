package com.anand.coding.java.designpattern.creationalpattern.singletonpattern;

/**
 *
 */
public class SingletonPatternThread implements Runnable{

    private String name;
    private Thread thread;


    public SingletonPatternThread(final String name) {
        this.name = name;
        thread = new Thread(this, name);

        System.out.println("Child thread started: " + thread);
        thread.start();
    }

    public void run() {
        for(int i=15; i>0; i--) {
            SingletonClazz.getInstance().display();
        }
        System.out.println("Child thread exiting: " + name);
    }

    public static void main(String[] args) {

        SingletonPatternThread threads[] = {
                new SingletonPatternThread("A"),
                new SingletonPatternThread("B"),
                new SingletonPatternThread("C")
        };

        for(int i = 10; i>0; i--) {
            SingletonClazz.getInstance().display();
        }

        System.out.println("Waiting for child threads to finish...\n");
        try {
            for(SingletonPatternThread thread: threads){
                thread.thread.join();
            }
        } catch(InterruptedException ie) {
            System.out.println("Thread Join failed. ");
            ie.printStackTrace();
        }

        System.out.println("Main thread exiting");
    }
}
