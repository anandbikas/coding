package com.anand.coding.problems.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 */
public class _02_MinRangeKSortedLists {


    /**
     *
     * @param list
     * @return
     */
    public static int [] minRangeKSortedLists(List<int []> list){

        int minRange = Integer.MAX_VALUE;
        int minRangeMin = -1;
        int minRangeMax = -1;

        int [] kPointers = new int[list.size()];

        int range,min,max;

        int minValueArrayIndex = 0;
        min = max = list.get(0)[0];

        for(int i=1; i<list.size(); i++){
            if(list.get(i)[0] < min){
                min = list.get(i)[0];
                minValueArrayIndex = i;
            } else if(list.get(i)[0] > max){
                max = list.get(i)[0];
            }
        }


        while(true){
            range = max-min;
            if(minRange > range){
                minRange = range;
                minRangeMin = min;
                minRangeMax = max;
            }

            kPointers[minValueArrayIndex]++;
            if( kPointers[minValueArrayIndex] >= list.get(minValueArrayIndex).length){
                break;
            }

            //Calculate min/max for next round
            max = Math.max(max, list.get(minValueArrayIndex)[kPointers[minValueArrayIndex]]);

            minValueArrayIndex = 0;
            min = list.get(0)[kPointers[0]];
            for(int i=1; i<list.size(); i++){
                if(list.get(i)[kPointers[i]] < min){
                    min = list.get(i)[kPointers[i]];
                    minValueArrayIndex = i;
                }
            }
        }

        return new int[]{minRangeMin, minRangeMax};
    }

    /**
     *
     * @param list
     * @return
     */
    public static int [] minRangeKSortedListsUsingBinaryMeanHeap(List<int []> list){

        int minRange = Integer.MAX_VALUE;
        int rangeMin=-1, rangeMax=-1;

        PriorityQueue<IndexedArray> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.A[x.index]));

        int max = list.get(0)[0];
        for(int []A: list){
            max = Math.max(max,A[0]);
            pq.offer(new IndexedArray(A, 0));
        }

        while (!pq.isEmpty()){
            IndexedArray indexedArray = pq.remove();
            int min = indexedArray.A[indexedArray.index++];
            if(max-min < minRange){
                minRange = max-min; rangeMin=min; rangeMax=max;
            }
            if(indexedArray.index==indexedArray.A.length) {
                break;
            }
            pq.offer(indexedArray);
            max = Math.max(max, indexedArray.A[indexedArray.index]);
        }

        return new int[]{rangeMin, rangeMax};
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

        int [] minRange = minRangeKSortedLists(list);
        System.out.println(minRange[0] + " -- " + minRange[1]);

        int [] minRange1 = minRangeKSortedListsUsingBinaryMeanHeap(list);
        System.out.println(minRange1[0] + " -- " + minRange1[1]);
    }

    private static class IndexedArray{
        public int index, A[];

        public IndexedArray(int[] a, int index) {
            this.A = a;
            this.index = index;
        }
    }
}
