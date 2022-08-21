package com.anand.coding.java.multithreading.problems;

/**
 * Print Foo Bar Alternately
 */
public class PrintAlternatively {

    private boolean foo=true;
    private int n;

    public PrintAlternatively(int n) {
        this.n = n;
    }

    public synchronized void foo() {

        try {
            for(int i=0; i<n; i++) {
                while (!foo) {
                    wait();
                }
                System.out.println("Foo");
                foo = !foo;
                notifyAll();
            }
        } catch (InterruptedException ie){};
    }

    public synchronized void bar(){

        try {
            for(int i=0; i<n; i++) {
                while (foo) {
                    wait();
                }
                System.out.println("Bar");
                foo = !foo;
                notifyAll();
            }
        } catch (InterruptedException ie){};
    }

    public static void main(String[] args) throws Exception{

        PrintAlternatively printAlternatively = new PrintAlternatively(5);

        new Thread(printAlternatively::foo).start();
        new Thread(printAlternatively::bar).start();
    }
}