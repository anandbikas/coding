package com.anand.coding.problems.greedy;

/**
 * Given an array representing stock price for consecutive days.
 * 1. Determine max profit for a single buy-sell transaction.
 *
 * Eg:
 * {7,1,5,3,6,4};
 * Buy on day2  (price 1)
 * Sell on day5 (price 6)
 *
 * Profit: 5
 *
 * 2. Determine max profit for multiple buy-sell transaction. Not greedy bu simple array calculation.
 * Eg:
 * {7,1,5,3,6,4};
 * Buy on day2  (price 1)
 * Sell on day3 (price 5)
 *
 * Buy on day4  (price 3)
 * Sell on day5 (price 6)
 *
 * Profit: 4+3=7
 *
 */
public class _2_BuyAndSellStock {

    /**
     *
      * @param prices
     * @return
     */
    public static int maxProfitSingleTransaction(int[] prices) {

        if (prices.length == 0){
            return 0;
        }
        int buy = prices[0];
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            if ((prices[i] - buy) > 0) {
                profit = Math.max(prices[i] - buy, profit);
            } else {
                buy = prices[i];
            }
        }
        return profit;
    }


    /**
     *
     * @param prices
     * @return
     */
    public static int maxProfitMultipleTransactions(int[] prices) {

        int profit=0;

        int buyAt=0;
        for(int i=1; i<prices.length; i++){
            if(prices[i]<=prices[i-1]){
                profit += prices[i-1] - prices[buyAt];
                buyAt=i;
            }
        }
        //Last transaction if any.
        profit += prices[prices.length-1] - prices[buyAt];


        return profit;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        int [] prices = {7,1,5,3,6,4};

        System.out.println(maxProfitSingleTransaction(prices));
        System.out.println(maxProfitMultipleTransactions(prices));

    }
}
