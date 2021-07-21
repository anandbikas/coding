package com.anand.coding.problems.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Check if an array of positive integers be partitioned into two equal subsets?
 *
 * 0_1 Knapsack, trick is to assume weight equals price and find the maximum weight for capacity sum/2.
 *
 */
public class _01_Knapsack_0_1_PartitionEqually {

    public static boolean canPartitionEqually(int [] nums) {

        int sum = Arrays.stream(nums).sum();
        if(sum==0 || sum%2==1){
            return false;
        }

        int n = nums.length;
        int weight = sum/2;
        int [] wt = nums;

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
                    DP[i][w] = Math.max( DP[i-1][w], wt[itemIndex] + DP[i-1][w-wt[itemIndex]]);
                }
            }
        }
        printDPArray(DP, n, weight);

        // Print the partitioned lists.
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();
        int w=weight;
        for(int i=n; i>0;i--){
            if (DP[i][w]==DP[i-1][w]) {
                B.add(wt[i-1]);
            } else {
                A.add(wt[i-1]);
                w-=wt[i-1];
            }
        }
        System.out.println(A + "\n" + B);
        return weight==DP[n][weight];
        //For minimum possible absolute difference.
        //return sum-DP[n][weight]-DP[n][weight];

    }

    /**
     *
     * @param args
     */
    public static void main(String [] args) {

        System.out.println(canPartitionEqually(new int[]{1,5,11,5}));
        System.out.println(canPartitionEqually(new int[]{1,2,3,4,5,6,7}));
    }

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
