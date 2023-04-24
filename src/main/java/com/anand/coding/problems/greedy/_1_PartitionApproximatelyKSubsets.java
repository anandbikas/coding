package com.anand.coding.problems.greedy;

import java.util.*;

/**
 * Partition a sorted array of positive integers into k approximately equal subsets?
 * https://leetcode.com/discuss/interview-question/430981/
 *
 * Greedy approach.
 * Example:
 * {1, 2, 3, 4, 5}
 *
 * Result:
 * [[5], [3, 2], [4, 1]]
 *
 */
public class _1_PartitionApproximatelyKSubsets {

    public static List<List<Integer>> partitionApproximatelyKSubsets(List<Integer> list, int k){

        int[] sums = new int[k];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->sums[a]-sums[b]);

        List<List<Integer>> subsetList = new ArrayList<>();
        for (int i=0; i<k; i++) {
            subsetList.add(new ArrayList<>());
            pq.add(i);
        }

        for (int i=list.size()-1; i>=0; i--) {
            int subsetNumber = pq.remove();
            subsetList.get(subsetNumber).add(list.get(i));
            sums[subsetNumber] += list.get(i);
            pq.add(subsetNumber);
        }

        return subsetList;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        System.out.println(partitionApproximatelyKSubsets(Arrays.asList(1, 2, 3, 4, 5), 3));

    }

}
