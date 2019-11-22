package com.anand.coding.problems.greedy;

/**
 * Given an array representing stock price for consecutive days.
 * Determine max profit for a single buy-sell transaction.
 *
 * Eg:
 * {7,1,5,3,6,4};
 * Buy on day2  (price 1)
 * Sell on day5 (price 6)
 *
 * Profit: 5
 */
public class _2_BuyAndSellStock {

    /**
     *
      * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {

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
     * @param args
     */
    public static void main(String[] args) {
        int [] prices = {7,1,5,3,6,4};

        System.out.println(maxProfit(prices));
    }
}
