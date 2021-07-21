package com.anand.coding.problems.backtracking;

import java.util.Arrays;

/**
 * Check if an array of positive integers be partitioned into k equal subsets?
 *
 * Backtracking.
 * Also see DP knapsack solution _01_Knapsack_0_1_PartitionEquallyKSubsets
 *
 */
public class _01_PartitionEquallyKSubsets {

    public static boolean canPartitionEquallyKSubsetsBacktracking(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if(sum==0 || sum%k!=0){
            return false;
        }
        return findPartitions(nums, new boolean[nums.length],sum/k,0,0, k);
    }

    private static boolean findPartitions(int [] nums, boolean []visited, int target, int currSum, int i, int k){
        if(k==1){
            return true;
        }

        if(currSum==target) {
            return findPartitions(nums, visited, target, 0, 0, k-1);
        }

        for(int j=i;j<nums.length;j++){
            if(visited[j] || currSum+nums[j]>target) {
                continue;
            }
            visited[j]=true;
            if(findPartitions(nums, visited, target, currSum + nums[j], j+1, k)) {
                return true;
            }
            visited[j]=false;
        }
        return false;
    }


    /**
     *
     * @param args
     */
    public static void main(String [] args) {

        System.out.println(canPartitionEquallyKSubsetsBacktracking(new int[]{4,3,2,3,5,2,1}, 4));
        System.out.println(canPartitionEquallyKSubsetsBacktracking(new int[]{1,2,2,3,3,4,5}, 4));

        System.out.println();

        System.out.println(canPartitionEquallyKSubsetsBacktracking(new int[]{1,2,3,5}, 2));
    }
}
