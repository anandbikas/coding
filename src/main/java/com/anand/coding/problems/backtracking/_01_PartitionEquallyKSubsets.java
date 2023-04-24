package com.anand.coding.problems.backtracking;

import java.util.Arrays;

/**
 * Check if an array of positive integers be partitioned into k equal subsets?
 * Backtracking.
 * /problems/partition-to-k-equal-sum-subsets
 */
public class _01_PartitionEquallyKSubsets {

    public static boolean canPartitionEquallyKSubsetsBacktracking(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if(sum==0 || sum%k!=0) {
            return false;
        }
        Arrays.sort(nums);

        return findPartitions(nums, 0, 0, sum/k, new boolean[nums.length], k);
    }

    private static boolean findPartitions(int [] nums, int i, int currSum, int target, boolean []visited, int k) {
        if(k==1) {
            return true;
        }

        if(currSum==target) {
            return findPartitions(nums, 0, 0, target, visited, k-1);
        }

        for(int j=i; j<nums.length; j++) {
            if(visited[j] )             continue;
            if(currSum+nums[j]>target)  return false; //Because next are all the greater values

            visited[j]=true;
            if(findPartitions(nums, j+1, currSum + nums[j], target, visited, k)) {
                return true;
            }
            visited[j]=false;
            while(j<nums.length-1 && nums[j]==nums[j+1]) j++; // If sorted, we can optimise by fast-tracking for same value.
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

        System.out.println();
        System.out.println(canPartitionEquallyKSubsetsBacktracking(new int[]{10,10,7,8,10,4,9,7,9,10,4,6,7,1,8,5}, 5));
    }
}
