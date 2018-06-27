package com.anand.coding.java.multithreading.synchronization;

/**
 * BankTransactionTest class
 */
public class BankTransactionTest {

    private static final int NUMBER_OF_ACCOUNTS = 10;
    private static final int INITIAL_BALANCE = 10000;

    public static void main(String[] args) {

        final Bank bank = new Bank(NUMBER_OF_ACCOUNTS, INITIAL_BALANCE);

        for(int i=0; i<NUMBER_OF_ACCOUNTS; i++) {

            TransactionThread transactionThread = new TransactionThread(bank, i, INITIAL_BALANCE);
            transactionThread.setPriority(Thread.NORM_PRIORITY + i%2);
            transactionThread.start();
        }
    }
}
