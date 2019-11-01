package com.anand.coding.dsalgo.tree.arraybased.problems;

import com.anand.coding.dsalgo.array.Array;
import com.anand.coding.dsalgo.tree.arraybased.BinaryMinHeap;

/**
 * In a nearly sorted array or k sorted array, element at each index gets position from i-k to i+k in the sorted list.
 *
 * 1. Insertion sort can be used with complexity O(nk)
 * 2. BinaryMinHeap can be used to further optimize it to O(n log(k))
 */
public class SortNearlySortedArray {


    /**
     *
     * @param A
     * @param k
     */
    public static void sortKSortedArray(int []A, int k){
        BinaryMinHeap<Integer> binaryMinHeap = new BinaryMinHeap<>(k+1);

        int i=0;
        for(; i<=k; i++){
            binaryMinHeap.insert(A[i]);
        }

        int j=0;
        for(; i<A.length; i++){
            A[j++] = binaryMinHeap.replace(0, A[i]);
        }

        while (!binaryMinHeap.isEmpty()) {
            A[j++] = binaryMinHeap.extractMin();
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String []args){
       int []A = {6,5,3,2,8,10,9};

        sortKSortedArray(A,3);
        Array.display(A);
    }
}
