package com.anand.coding.problems.heap;

import com.anand.coding.dsalgo.graph.adjacencylist.Pair;
import com.anand.coding.dsalgo.tree.arraybased.BinaryMinHeap;

import java.util.ArrayList;
import java.util.List;

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
        int minRangeMin = -1;
        int minRangeMax = -1;

        int [] kPointers = new int[list.size()];

        int range,min,max;

        max = list.get(0)[0];
        for(int i=1; i<list.size(); i++){
            if(list.get(i)[0] > max){
                max = list.get(i)[0];
            }
        }

        //MinHeap entry is a Pair:(number, array_index_which_contains_the_number)
        //                          key             value
        BinaryMinHeap<Pair<Integer, Integer>> binaryMinHeap = new BinaryMinHeap<>(list.size());
        for(int i=0; i<list.size(); i++){
            if(list.get(i).length>0) {
                binaryMinHeap.insert(new Pair<>(list.get(i)[0], i));
            }
        }


        while (true){
            Pair pair = binaryMinHeap.extractMin();
            min = (Integer) pair.key;
            int minValueArrayIndex = (Integer) pair.value;

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

            binaryMinHeap.insert(new Pair<>(list.get(minValueArrayIndex)[kPointers[minValueArrayIndex]], minValueArrayIndex));
            // Check if the new element is greater than max
            max = Math.max(max, list.get(minValueArrayIndex)[kPointers[minValueArrayIndex]]);
        }

        return new int[]{minRangeMin, minRangeMax};
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
}
