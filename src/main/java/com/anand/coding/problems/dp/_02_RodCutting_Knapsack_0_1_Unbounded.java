package com.anand.coding.problems.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * There is a rod of n unit and value/price list per unit of the rod.
 *
 * 1. Find the maximum profit by cutting and selling the rod.
 *
 * Can be solved using Unbounded Knapsack where
 *      wt[] = item i has weight i.
 *      val[] = price [];
 *      knapsack weight = n (length)
 */
public class _02_RodCutting_Knapsack_0_1_Unbounded {

    /**
     * @param price
     * @param n
     * @return
     */
    public static int rodCuttingUnboundedKnapsack(int [] price, int n){

        //int []wt = item i has weight i.
        int []val = price;
        int weight = n;

        if(n<=0 || weight<=0) {
            return 0;
        }

        int [][] DP = new int[n+1][weight+1];   //Item-Weight

        //Populate DP from 1st element onwards
        for(int i=1; i<=n; i++) {

            int item = i-1;
            int itemWeight = i;
            for(int w=1; w<=weight; w++) {
                DP[i][w] = (itemWeight>w)
                        ? DP[i-1][w]
                        : Math.max(DP[i-1][w], val[item] + DP[i][w-itemWeight]);
            }
        }

        // Print the selected items.
        List<Integer> items = new ArrayList<>();
        int w=weight;
        for(int i=n; i>0;){
            int itemWeight = i;
            if (DP[i][w]!=DP[i-1][w]) {
                items.add(i);
                w -= itemWeight;
            } else {
                i--;
            }

        }
        items.forEach(i -> System.out.print("(" + val[i-1] + "," + i + "), "));
        System.out.println();

        return DP[n][weight];
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        int[] val = new int[] {1, 5, 8, 9, 10, 17, 17, 20};
        System.out.println(rodCuttingUnboundedKnapsack(val, val.length));

        int[] val1 = new int[] {2,5,3,1};
        System.out.println(rodCuttingUnboundedKnapsack(val1, val1.length));
    }
}
