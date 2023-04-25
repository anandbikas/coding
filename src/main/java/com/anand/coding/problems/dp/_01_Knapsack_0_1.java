package com.anand.coding.problems.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value
 *
 * Note: This can be solved using greedy as well.
 *
 * Two variations:
 * 1. Bounded :     single item count
 * 2. Unbounded :   unlimited item count
 *
 */
public class _01_Knapsack_0_1 {

    /**
     * Recursive solution with memoization
     */
    public static int knapsackRec(int[] wt, int[] val, int n, int weight) {

        int [][] DP =  new int[n+1][weight+1];  //Item-Weight
        int finalResult = knapsackRec(wt, val, n, weight, DP);

        Util.printDPArray(DP, n, weight);
        return finalResult;
    }
    private static int knapsackRec(int[] wt, int[] val, int n, int w, int[][] DP) {

        if(n<=0 || w<=0) {
            return 0;
        }
        if(DP[n][w] >0) {
            return DP[n][w];
        }

        int item = n-1;
        int res = (wt[item]>w)
                ? knapsackRec(wt, val, n-1, w, DP)
                : Math.max(knapsackRec(wt, val, n-1, w, DP),
                            val[item] + knapsackRec(wt, val, n-1, w-wt[item], DP));
                            // For unbounded knapsack, take n... val[item] + knapsackRec(wt, val, n, w-wt[item], DP));

        return DP[n][w] = res;
    }

    /**
     * DP Tabulation solution
     */
    public static int knapsack(int[] wt, int[] val, int n, int weight) {

        if(n<=0 || weight<=0) {
            return 0;
        }

        int [][] DP = new int[n+1][weight+1];   //Item-Weight

        //Populate DP from 1st element onwards
        for(int i=1; i<=n; i++) {

            int item = i-1;
            for(int w=1; w<=weight; w++) {
                DP[i][w] = (wt[item]>w)
                        ? DP[i-1][w]
                        : Math.max(DP[i-1][w], val[item] + DP[i-1][w-wt[item]]);
                        //For unbounded knapsack, take i... val[item] + DP[i][w-wt[item]]);
            }
        }
        Util.printDPArray(DP, n, weight);

        // Print the selected items.
        List<Integer> items = new ArrayList<>();
        int w=weight;
        for(int i=n; i>0 && w>0; i--) {
            int item = i-1;
            if (DP[i][w] != DP[i-1][w]) {
                items.add(item);
                w -= wt[item];
            }
            // For unbounded knapsack use, else { i--; } and remove i-- from for loop.
        }
        items.forEach(i -> System.out.print("(" + val[i] + "," + wt[i] + "), "));
        System.out.println();

        return DP[n][weight];
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args) {

        // Example 1:
        int []val1 = {10, 20, 30};
        int []wt1 =  { 2,  5,  6};
        int weight1 = 9;

        /* Recursive Solution DP Array:
         *
         *    0   0   0   0   0   0   0   0   0   0
         *    0   0   0  10  10   0   0   0   0  10
         *    0   0   0  10   0   0   0   0   0  30
         *    0   0   0   0   0   0   0   0   0  40
         */
        System.out.println(knapsackRec(wt1, val1, wt1.length, weight1));

        /* Tabulation Solution DP Array:
         *
         *    0   0   0   0   0   0   0   0   0   0
         *    0   0  10  10  10  10  10  10  10  10
         *    0   0  10  10  10  20  20  30  30  30
         *    0   0  10  10  10  20  30  30  40  40
         */
        System.out.println(knapsack(wt1, val1, wt1.length, weight1));


        // Example 2:
        int []val2 = {10, 20, 30, 60, 30, 10, 50, 100, 500, 30, 20};
        int []wt2 =  {2,   5,  6,  3,  1,  9,  8,  3,   5,   6,  3};
        int weight2 = 9;

        System.out.println(knapsackRec(wt2, val2, wt2.length, weight2));
        System.out.println(knapsack(wt2, val2, wt2.length, weight2));


        // Example 3:
        int []val = {60, 100, 120};
        int []wt =  {10,  20,  30};
        int weight = 50;

        System.out.println(knapsackRec(wt, val, wt.length, weight));
        System.out.println(knapsack(wt, val, wt.length, weight));
    }
}
