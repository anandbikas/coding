package com.anand.coding.problems.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Tug of Wars.
 * Partition an array of n integers into two approximately equal sum subsets each containing either n/2 or n/2+1 elements depending on the input size is even or odd.
 *
 * Backtracking.
 *  Ex:
 *  Array:
 *      {23, 45, -34, 12, 0, 98, -99, 4, 189, -1, 4}.
 *  Result:
 *      {45, -34, 12, 98, -1}       sum= 120
 *      {23, 0, -99, 4, 189, 4}.    sum= 121
 *
 */
public class _02_PartitionApproximately_TugOfWar {

    private static class IntBox{
        int intValue=Integer.MIN_VALUE;
    }

    public static List<List<Integer>> partitionApproximatelyEqualSubsets(int[] nums) {

        if(nums.length==0){
            return null;
        }

        int sum = Arrays.stream(nums).sum();

        boolean [] visited=new boolean[nums.length];
        boolean [] visitedForMaxSumBackup=new boolean[nums.length];
        IntBox maxSum = new IntBox();

        findPartitions(nums, visited,0,sum/2,0, visitedForMaxSumBackup, maxSum,0);

        // Partitioned lists.
        List<Integer> A = new ArrayList<>(); int sumA=0;
        List<Integer> B = new ArrayList<>(); int sumB=0;
        for(int i=0; i<nums.length;i++){
            if(visitedForMaxSumBackup[i]){
                A.add(nums[i]); sumA+=nums[i];
            } else {
                B.add(nums[i]); sumB+=nums[i];
            }
        }

        System.out.println(sumA + " : " + sumB);
        return Arrays.asList(A,B);
    }

    private static void findPartitions(
            int [] nums, boolean []visited, int visitedCount, int target, int currSum, boolean []visitedForMaxSumBackup, IntBox maxSum, int i){

        if(visitedCount==nums.length/2) {
            if(currSum<=target && currSum>maxSum.intValue){
                maxSum.intValue = currSum;
                System.arraycopy(visited, 0, visitedForMaxSumBackup, 0, visited.length);
            }
            return;
        }

        for(int j=i;j<nums.length;j++){
            visited[j]=true; visitedCount++;
            findPartitions(nums, visited, visitedCount, target, currSum + nums[j], visitedForMaxSumBackup, maxSum, j+1);
            visited[j]=false; visitedCount--;
        }
    }


    /**
     *
     * @param args
     */
    public static void main(String [] args) {

        System.out.println(partitionApproximatelyEqualSubsets(new int[]{3, 4, 5, -3, 100, 1, 89, 54, 23, 20}));
        System.out.println(partitionApproximatelyEqualSubsets(new int[]{23, 45, -34, 12, 0, 98, -99, 4, 189, -1, 4}));
        System.out.println(partitionApproximatelyEqualSubsets(new int[]{-2,-3,-1,-8}));

    }
}
