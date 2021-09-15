package com.anand.coding.problems.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 */
public class _12_KthSmallestInSortedMatrix {


    public static int kthSmallest(int[][] matrix, int k) {

        PriorityQueue<IndexedArray> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.A[x.index]));

        for(int []A: matrix){
            pq.offer(new IndexedArray(A, 0));
        }

        int count=0;
        while (!pq.isEmpty()){
            IndexedArray indexedArray = pq.remove();
            count++;
            if(count==k){
                return indexedArray.A[indexedArray.index];
            }
            indexedArray.index++;
            if(indexedArray.index<indexedArray.A.length) {
                pq.offer(indexedArray);
            }
        }

        return -1;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        int[][] A = {{1,5,9},{10,11,13},{12,13,15}};
        System.out.println(kthSmallest(A,8));
    }

    private static class IndexedArray{
        public int index, A[];

        public IndexedArray(int[] a, int index) {
            this.A = a;
            this.index = index;
        }
    }
}
