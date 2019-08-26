package com.anand.coding.dsalgo.problems;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MinRangeKSortedLists {


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


        int range;
        int min;
        int max;

        int minArrayIndex;

        while(true){

            minArrayIndex = 0;
            min = max = list.get(minArrayIndex)[kPointers[minArrayIndex]];

            for(int i=1; i<list.size(); i++){
                if(list.get(i)[kPointers[i]] < min){
                    min = list.get(i)[kPointers[i]];
                    minArrayIndex = i;
                } else if(list.get(i)[kPointers[i]] > max){
                    max = list.get(i)[kPointers[i]];
                }
            }

            range = max-min;
            if(minRange > range){
                minRange = range;
                minRangeMin = min;
                minRangeMax = max;
            }

            kPointers[minArrayIndex]++;

            if( kPointers[minArrayIndex] >= list.get(minArrayIndex).length){
                break;
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

        //TODO: Implementation

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
    }
}
