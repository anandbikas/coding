package com.anand.coding.problems.heap;

import com.anand.coding.dsalgo.array.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Approach:
 * 1. using kPointers [] : O(n * k)
 *
 * 2. optimized using BinaryMinHeap(PriorityQueue) : O(n log(k))
 *
 * leetcode.com/problems/merge-k-sorted-lists
 */
public class _01_MergeKSortedArrays {

    /**
     *
     * @param list list of arrays
     * @return
     */
    public static int [] mergeKSortedArrays(List<int[]> list) {

        PriorityQueue<IndexedArray> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.A[x.idx]));

        list.stream().filter(A->A.length>0).map(IndexedArray::new).forEach(pq::offer);

        List<Integer> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            IndexedArray idxArray = pq.poll();
            res.add(idxArray.A[idxArray.idx++]);

            if(idxArray.idx<idxArray.A.length){
                pq.offer(idxArray);
            }
        }

        return res.stream().mapToInt(x->x).toArray();
    }

    /**
     *
     * @param list list of arrays
     * @return
     */
    public static int [] mergeKSortedArrays1(List<int[]> list) {

        int [] kPointers = new int[list.size()];

        int totalCount = 0;
        for(int i=0; i<list.size(); i++) {
            totalCount +=list.get(i).length;
            kPointers[i]=0;
        }

        int A[] = new int[totalCount];

        int i=0;
        while(true) {
            int minVal=Integer.MAX_VALUE;
            int idx = -1;
            for(int j=0; j<list.size(); j++){
                if(kPointers[j] < list.get(j).length) {
                    if (list.get(j)[kPointers[j]] < minVal) {
                        minVal = list.get(j)[kPointers[j]];
                        idx = j;
                    }
                }
            }
            if(idx==-1) break; //Nothing found break

            A[i++] = list.get(idx)[kPointers[idx]];
            kPointers[idx]++;
        }

        return A;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        List<int []> list = new ArrayList<>();

        list.add( new int[]{4,10,15,24});
        list.add( new int[]{0,9,12,20});
        list.add( new int[]{5,18,22,30});

        int []A = mergeKSortedArrays(list);
        Array.display(A);

        int []B = mergeKSortedArrays1(list);
        Array.display(B);
    }

    private static class IndexedArray {
        public int idx, A[];
        public IndexedArray(int[] A) { this.A = A;}
    }
}
