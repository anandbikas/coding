package com.anand.coding.java.multithreading.synchronization;

/**
 * TransactionThread
 * performs transaction from a fixed fromAccount to a randon toAccount
 */
public class TransactionThread extends Thread
{
    private final Bank bank;
    private int fromAccount;
    private int maxAmount;
    private static final int SLEEP_MILLI_SECONDS=10;


    public TransactionThread(Bank bank, int fromAccount, int maxAmount) {
        this.bank = bank;
        this.fromAccount = fromAccount;
        this.maxAmount = maxAmount;
    }

    public void run() {
        try {
            while(!interrupted()) {
                int toAccount = (int)(bank.size() * Math.random());
                long amount = (long)(maxAmount * Math.random());
                bank.transferBalance(fromAccount, toAccount, amount);
                Thread.sleep((int)(SLEEP_MILLI_SECONDS * Math.random()));
            }
        } catch(InterruptedException e) {
            System.out.println("TransactionThread interrupted.");
        }
    }
}