package com.anand.coding.problems.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

/**
 * Check if an array of positive integers be partitioned into k equal subsets?
 *
 * 0_1 Knapsack, trick is to assume weight equals val and find the maximum weight for capacity sum/k k times.
 *
 */
public class _01_Knapsack_0_1_PartitionEquallyKSubsets {

    /**
     * DP Tabulation solution
     */
    public static boolean canPartitionEquallyKSubsets(int[] nums, int k) {

        int sum = Arrays.stream(nums).sum();
        if(sum==0 || sum%k!=0){
            return false;
        }

        int n = nums.length;
        int weight = sum/k;
        int [] wt = Arrays.stream(nums).boxed().sorted(Collections.reverseOrder())
                        .mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(wt));

        //Find k times.
        while(k-->0) {
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
            printDPArray(DP, n, weight);

            if(weight!=DP[n][weight]) {
                return false;
            }

            //Create the partition list
            List<Integer> A = new ArrayList<>();
            int w=weight;
            for(int i=n; i>0 && w>0; i--) {
                int item = i-1;
                if (DP[i][w] != DP[i-1][w]) {
                    A.add(wt[item]);
                    w -= wt[item];
                    wt[item] = 0; //Mark that this element has been picked up.
                }
            }
            System.out.println(A);

            //Remainder list to be used to find the next partition
            int x=0;
            for(int i=0; i<n; i++) {
                if(wt[i]!=0) {
                    wt[x++]=wt[i];
                }
            }
            n = x;
        }
        return true;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args) {

        System.out.println(canPartitionEquallyKSubsets(new int[]{4,3,2,3,5,2,1}, 4));

        System.out.println();
        System.out.println(canPartitionEquallyKSubsets(new int[]{1,2,3,5}, 2));

        // Knapsack solution doesn't work. This below test case should make 5 parts with sum=23
        // But the result is false due to different element combination.
        System.out.println();
        System.out.println(canPartitionEquallyKSubsets(new int[]{10,10,7,8,10,4,9,7,9,10,4,6,7,1,8,5}, 5));
    }

    public static void printDPArray(int [][] DP, int n, int m) {
        for(int i=0; i<=n; i++) {
            for(int j =0; j<=m; j++) {
                System.out.printf("%4d", DP[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
