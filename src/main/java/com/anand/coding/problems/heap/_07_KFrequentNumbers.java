package com.anand.coding.problems.heap;

import java.util.*;

/**
 * k frequent numbers
 */
public class _07_KFrequentNumbers {

    public static int[] topKFrequentNumbers(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparing(map::get).reversed());

        Arrays.stream(nums).forEach(x -> map.put(x, map.containsKey(x) ? map.get(x)+1 : 1));
        pq.addAll(map.keySet());

        int []A = new int[k];
        for(int i=0; i<k && !pq.isEmpty(); i++){
            A[i]=pq.remove();
        }

        return A;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args) {

       System.out.println(Arrays.toString(topKFrequentNumbers(new int[]{1,1,1,2,2,3}, 2)));
    }
}
