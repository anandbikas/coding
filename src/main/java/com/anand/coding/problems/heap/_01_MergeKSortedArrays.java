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
 */
public class _01_MergeKSortedArrays {

    /**
     *
     * @param list list of arrays
     * @return
     */
    public static int [] mergeKSortedArrays(List<int[]> list){

        PriorityQueue<IndexedArray> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.A[x.index]));

        for(int i=0; i<list.size(); i++){
            if(list.get(i).length>0) {
                pq.offer(new IndexedArray(list.get(i), 0));
            }
        }

        List<Integer> sortedList = new ArrayList<>();
        while (!pq.isEmpty()){
            IndexedArray indexedArray = pq.poll();
            sortedList.add(indexedArray.A[indexedArray.index++]);
            if(indexedArray.index<indexedArray.A.length){
                pq.offer(indexedArray);
            }
        }

        return sortedList.stream().mapToInt(x->x).toArray();
    }

    /**
     *
     * @param list list of arrays
     * @return
     */
    public static int [] mergeKSortedArrays1(List<int[]> list){

        int [] kPointers = new int[list.size()];

        int totalCount = 0;
        for(int i=0; i<list.size(); i++){
            totalCount +=list.get(i).length;
            kPointers[i]=0;
        }

        int A[] = new int[totalCount];

        int i=0;
        while(true){
            int minValue=Integer.MAX_VALUE;
            int minValueArrayIndex = -1;
            for(int j=0; j<list.size(); j++){
                if(kPointers[j]>=list.get(j).length){
                    continue;
                }
                if(list.get(j)[kPointers[j]] < minValue){
                    minValue = list.get(j)[kPointers[j]];
                    minValueArrayIndex =j;
                }
            }
            if(minValueArrayIndex==-1){
                //Nothing found break
                break;
            }

            A[i++] = list.get(minValueArrayIndex)[kPointers[minValueArrayIndex]];
            kPointers[minValueArrayIndex]++;
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

    private static class IndexedArray{
        public int index, A[];

        public IndexedArray(int[] a, int index) {
            this.A = a;
            this.index = index;
        }
    }
}
