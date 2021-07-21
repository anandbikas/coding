package com.anand.coding.problems.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Check if an array of positive integers be partitioned into k equal subsets?
 *
 * 0_1 Knapsack, trick is to assume weight equals price and find the maximum weight for capacity sum/k k times.
 *
 */
public class _01_Knapsack_0_1_PartitionEquallyKSubsets {

    public static boolean canPartitionEquallyKSubsets(int [] nums, int k) {

        int sum = Arrays.stream(nums).sum();
        if(sum==0 || sum/k==0 || sum%k!=0){
            return false;
        }

        int n = nums.length;
        int weight = sum/k;
        int [] wt = Arrays.stream(nums).boxed().sorted(Collections.reverseOrder())
                        .mapToInt(Integer::intValue).toArray();

        while(k-->0) {
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
                        DP[i][w] = Math.max(DP[i-1][w], wt[itemIndex] + DP[i-1][w-wt[itemIndex]]);
                    }
                }
            }
            printDPArray(DP, n, weight);

            if(weight!=DP[n][weight]){
                return false;
            }

            // Print this partition list
            List<Integer> A = new ArrayList<>();
            int w=weight;
            for(int i=n; i>0;i--){
                if (DP[i][w]!=DP[i-1][w]) {
                    A.add(wt[i-1]);
                    w-=wt[i-1];
                    wt[i-1]=0; //Mark that this element has been picked up.
                }
            }
            System.out.println(A);

            //Remainder list to be used to find the next partition
            int x=0;
            for(int i=0; i<n;i++){
                if(wt[i]!=0){
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
