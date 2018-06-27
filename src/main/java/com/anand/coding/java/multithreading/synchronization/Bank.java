package com.anand.coding.java.multithreading.synchronization;

import java.util.Arrays;

/**
 * Bank Class
 */
public class Bank {

    public static final int TEST_CORRUPTION_ON_TRANSACTIONS_COUNT = 10;

    private long[] balanceAccounts;
    private long numberOfIntraBankTransactions = 0;

    /**
     *
     * @param numberOfAccounts
     * @param initialBalance
     */
    public Bank(final int numberOfAccounts, final long initialBalance) {
        balanceAccounts = new long[numberOfAccounts];
        Arrays.fill(balanceAccounts, initialBalance);
    }

    /**
     *
     * @param fromAccount
     * @param toAccount
     * @param amount
     */
    public void transferBalance(final int fromAccount, final int toAccount, final long amount) {
        if(balanceAccounts[fromAccount] < amount){
            System.out.println("Transfer bounced, Low balance in account: " + fromAccount);
            return;
        }
        balanceAccounts[fromAccount] -= amount;
        balanceAccounts[toAccount] += amount;
        numberOfIntraBankTransactions++;

        if(numberOfIntraBankTransactions % TEST_CORRUPTION_ON_TRANSACTIONS_COUNT == 0){
            testAccountCorruption();
        }
    }

    /**
     *
     */
    private void testAccountCorruption() {
        long sum = Arrays.stream(balanceAccounts).sum();
        System.out.println("Total transactions = " + numberOfIntraBankTransactions + ", Total sum = " + sum);
    }

    /**
     *
     * @return
     */
    public int size() {
        return balanceAccounts.length;
    }
}
