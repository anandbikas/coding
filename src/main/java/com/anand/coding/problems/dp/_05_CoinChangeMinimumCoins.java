package com.anand.coding.problems.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given coin denominations set {1,5,6,8},
 *
 * 1. Find minimum number of coins required to make a given sum.
 * 2. Print those coins comprising the sum.
 *
 * Note: This can be solved using Greedy algorithm as well by sorting the denominations.
 *       But this may not always work.
 *       Eg:
 *        coins= {9, 6, 5, 1} and V = 11.
 *        Greedy: {9,1,1}.
 *        DP    : {5,6}
 */
public class _05_CoinChangeMinimumCoins {

    public static int INF = Integer.MAX_VALUE-1;   // decrease by 1 to support addition of 1.

    /**
     * DP Tabulation solution
     *
     * @param coins
     * @param sum
     */
    public static List<Integer> coinChangeIterative(int [] coins, int sum){
        int []DP = new int[sum+1];
        int []COINS_TRACK = new int[sum+1];

        Arrays.fill(DP, INF);
        DP[0] = 0;

        for(int x: coins){
            for(int i=x; i<=sum; i++){
                if(1+DP[i-x] < DP[i]) {
                    DP[i] = 1 + DP[i-x];
                    COINS_TRACK[i] = x;
                }
            }
        }



        for(int i=0; i<=sum; i++){
            System.out.print(String.format("%10d", DP[i] == INF ? -1 : DP[i]));
        }
        System.out.println();

        for(int i=0; i<=sum; i++){
            System.out.print(String.format("%10d", COINS_TRACK[i]));
        }
        System.out.println();

        final List<Integer> resultList = new ArrayList<>();

        if(DP[sum]!=INF) {
            while (sum != 0) {
                int coin = COINS_TRACK[sum];
                resultList.add(coin);
                sum -= coin;
            }
        }
        return resultList;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        // Example 1:
        int []coins1 = {5, 6, 8};
        int sum1 = 7;
        System.out.println(coinChangeIterative(coins1, sum1));

        // Example 2:
        int []coins2 = {1,5, 6, 8};
        int sum2 = 11;
        System.out.println(coinChangeIterative(coins2, sum2));

        // Example 3:
        int []coins3 = {7,2,3,6};
        int sum3 = 13;
        System.out.println(coinChangeIterative(coins3, sum3));

        // Example 4:
        int []coins4 = {5,10,20,50,100,500,1000,2000};
        int sum4 = 35;
        System.out.println(coinChangeIterative(coins4, sum4));

        System.out.println(coinChangeIterative(new int[]{1,10,25}, 14));

    }
}
