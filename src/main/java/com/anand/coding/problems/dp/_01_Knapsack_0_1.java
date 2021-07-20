package com.anand.coding.problems.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value
 *
 * Note: This can be solved using greedy as well.
 *
 */
public class _01_Knapsack_0_1 {

    /**
     * Recursive solution with memoization
     *
     * @param wt
     * @param val
     * @param n
     * @param weight
     * @return
     */
    public static int knapsackRec(int [] wt, int [] val, int n, int weight) {

        int [][] DP =  new int[n+1][weight+1];  //Item-Weight
        int finalResult = knapsackRec(wt, val, n, weight, DP);
        printDPArray(DP, n, weight);

        return finalResult;
    }
    private static int knapsackRec(int [] wt, int [] val, int n, int w, int [][] DP){

        if(n<=0 || w<=0){
            return 0;
        }

        int itemIndex = n-1;

        if(DP[n][w] >0){
            return DP[n][w];
        }

        if(wt[itemIndex]>w){
            return DP[n][w] = knapsackRec(wt, val, n-1, w, DP);
        }

        return DP[n][w] = Math.max(knapsackRec(wt, val, n-1, w, DP),
                                        val[itemIndex] + knapsackRec(wt, val, n-1, w-wt[itemIndex], DP));
    }

    /**
     * DP Tabulation solution
     *
     * @param wt
     * @param val
     * @param n
     * @param weight
     */
    public static int knapsack(int [] wt, int [] val, int n, int weight){

        if(n==0 || weight==0){
            return 0;
        }

        int [][] DP = new int[n+1][weight+1];   //Item-Weight

        //Populate DP from 1st element onwards
        for(int i=1; i<=n; i++){

            int itemIndex = i-1;
            for(int w=1; w<=weight; w++){

                //Can't take i'th element upto now
                if(wt[itemIndex]>w) {
                    DP[i][w] = DP[i-1][w];
                }
                else{
                    DP[i][w] = Math.max( DP[i-1][w], val[itemIndex] + DP[i-1][w-wt[itemIndex]]);
                }
            }
        }
        printDPArray(DP, n, weight);

        // Print the selected items.
        List<Integer> itemWeights = new ArrayList<>();
        List<Integer> itemValues = new ArrayList<>();
        int w=weight;
        for(int i=n; i>0;i--){
            if (DP[i][w]!=DP[i-1][w]) {
                itemWeights.add(wt[i-1]);
                itemValues.add(val[i-1]);
                w-=wt[i-1];
            }
        }
        System.out.println(itemWeights + "\n" + itemValues);
        return DP[n][weight];
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

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

        /* Tapulation Solution DP Array:
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

    /**
     *
     * @param DP
     * @param n
     * @param m
     */
    public static void printDPArray(int [][] DP, int n, int m)
    {
        System.out.println();
        for(int i=0; i<=n; i++){

            for(int j =0; j<=m; j++){
                System.out.print(String.format("%4d", DP[i][j]));
            }
            System.out.println();
        }
    }
}
