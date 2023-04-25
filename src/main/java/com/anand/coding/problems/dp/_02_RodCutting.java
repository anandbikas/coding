package com.anand.coding.problems.dp;

import java.util.Arrays;

/**
 * There is a rod of n unit and value/price list per unit of the rod.
 *
 * 1. Find the maximum profit by cutting and selling the rod.
 *
 * Note: This can be solved by greedy approach as well by sorting on the basis of price-per-unit.
 *       But this does not always give correct answer.
 *       EG:
 *          price[] = {1,5,8,10} for n=4
 *          price/unit = 1,2.5,2.6,2.5
 *          Greedy answer = 9 (unit 3,1)
 *          DP answer = 10 (unit 2,2)
 */
public class _02_RodCutting {

    /**
     * Recursive solution with memoization
     */
    public static int rodCuttingRec(int[] val, int n) {

        int [] DP = new int[n+1];   //Length
        int result = rodCuttingRec(val, n, DP);

        System.out.println(Arrays.toString(DP));
        return result;
    }
    private static int rodCuttingRec(int[] val, int n, int[] DP) {

        if(n==0) {
            return 0;
        }
        if(DP[n]>0) {
            return DP[n];
        }

        int profit = val[n-1]; // profit when no cut.
        for(int i=n-1; i>n/2; i--) {
            profit = Math.max(profit,
                            rodCuttingRec(val, i, DP) + rodCuttingRec(val, n-i, DP));
        }
        return DP[n] = profit;
    }

    /**
     * DP Tabulation solution
     */
    public static int rodCutting(int[] val, int n) {

        if(n==0) {
            return 0;
        }

        int [] DP = new int[n+1];   //Length

        for(int k=1; k<=n; k++) {

            int profit = val[k-1];
            for(int i=k-1; i>k/2; i--) {
                profit = Math.max(profit,
                            DP[i] + DP[k-i]);
            }
            DP[k]=profit;
        }

        System.out.println(Arrays.toString(DP));
        return DP[n];
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        int[] val = new int[] {1, 5, 8, 9, 10, 17, 17, 20};
        System.out.println(rodCuttingRec(val, val.length));

        System.out.println(rodCutting(val, val.length));
    }
}
