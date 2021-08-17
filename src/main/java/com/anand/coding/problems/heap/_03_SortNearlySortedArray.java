package com.anand.coding.problems.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * In a nearly sorted array or k sorted array, element at each index gets position from i-k to i+k in the sorted list.
 *
 * 1. Insertion sort can be used with complexity O(nk)
 * 2. BinaryMinHeap can be used to further optimize it to O(n log(k))
 */
public class _03_SortNearlySortedArray {


    /**
     *
     * @param A
     * @param k
     */
    public static void sortKSortedArray(int []A, int k){

        PriorityQueue<Integer> pq = new PriorityQueue<>(k+1);

        int i=0,j=0;
        while(i<=k) pq.offer(A[i++]);

        while (i<A.length){
            A[j++] = pq.remove();
            pq.offer(A[i++]);
        }

        while (!pq.isEmpty()) A[j++] = pq.poll();
    }

    /**
     *
     * @param args
     */
    public static void main(String []args){
       int []A = {6,5,3,2,8,10,9};

        sortKSortedArray(A,3);
        System.out.println(Arrays.toString(A));
    }
}
