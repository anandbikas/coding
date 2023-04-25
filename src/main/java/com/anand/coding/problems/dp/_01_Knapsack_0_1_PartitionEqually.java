package com.anand.coding.problems.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Check if an array of positive integers be partitioned into two equal subsets?
 * Variation: If not possible, Partition with minimum possible absolute difference
 *
 * 0_1 Knapsack, Assume integers as weights and find the maximum weight for sack capacity sum/2.
 *
 * /problems/partition-equal-subset-sum
 * /problems/last-stone-weight-ii
 */
public class _01_Knapsack_0_1_PartitionEqually {

    /**
     * DP Tabulation solution
     */
    public static boolean canPartitionEqually(int[] wt) {

        int sum = Arrays.stream(wt).sum();
        if(sum==0 || sum%2==1){
            return false;
        }

        int n = wt.length;
        int weight = sum/2;

        int [][] DP = new int[n+1][weight+1];   //Item-Weight

        //Populate DP from 1st element onwards
        for(int i=1; i<=n; i++) {

            int item = i-1;
            for(int w=1; w<=weight; w++) {
                DP[i][w] = (wt[item]>w)
                        ? DP[i-1][w]
                        : Math.max(DP[i-1][w],
                                    wt[item] + DP[i-1][w-wt[item]]);
            }
        }
        Util.printDPArray(DP, n, weight);

        // Print the partitioned lists.
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();
        int w=weight;
        for(int i=n; i>0 && w>0; i--) {
            int item = i-1;
            if (DP[i][w] != DP[i-1][w]) {
                A.add(wt[item]);
                w -= wt[item];
            } else {
                B.add(wt[item]);
            }
        }
        System.out.println(A + "\n" + B);
        return weight==DP[n][weight];
        //For minimum possible absolute difference.
        //return sum-2*DP[n][weight];
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args) {

        System.out.println(canPartitionEqually(new int[]{1,5,11,5}));
        System.out.println(canPartitionEqually(new int[]{1,2,3,4,5,6,7}));
    }
}
