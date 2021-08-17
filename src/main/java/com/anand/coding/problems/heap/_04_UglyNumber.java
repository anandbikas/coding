package com.anand.coding.problems.heap;

import java.util.*;

/**
 * A positive integer whose prime factors contains only 2, 3, and 5 is call ugly number
 *
 * Find n'th ugly integer.
 *
 */
public class _04_UglyNumber {

    public static int uglyNumber(int n){

        PriorityQueue<Long> pq = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();

        pq.add(1L); set.add(1L);

        long x=1;
        while(!pq.isEmpty() && n-->0){
            x = pq.remove();
            for(int factor: new int[]{2,3,5}){
                long uglyNumber = x * factor;
                if(!set.contains(uglyNumber)){
                    pq.add(uglyNumber);
                    set.add(uglyNumber);
                }
            }
        }

        return (int)x;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args) {
        System.out.println(uglyNumber(50));
    }
}
