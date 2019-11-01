package com.anand.coding.dsalgo.tree.arraybased.problems;

import com.anand.coding.dsalgo.array.Array;
import com.anand.coding.dsalgo.graph.adjacencylist.Pair;
import com.anand.coding.dsalgo.tree.arraybased.BinaryMinHeap;

import java.util.ArrayList;
import java.util.List;

/**
 * Approach:
 * 1. using kPointers [] : O(n * k)
 *
 * 2. optimized using BinaryMinHeap : O(n log(k))
 *
 */
public class MergeKSortedArrays {

    /**
     *
     * @param list list of arrays
     * @return
     */
    public static int [] mergeKSortedArrays(List<int[]> list){

        int [] kPointers = new int[list.size()];

        //MinHeap entry is a Pair:(number, array_index_which_contains_the_number)
        //                          key             value
        BinaryMinHeap<Pair<Integer, Integer>> binaryMinHeap = new BinaryMinHeap<>(list.size());

        int totalCount = 0;
        for(int i=0; i<list.size(); i++){
            totalCount +=list.get(i).length;
            if(list.get(i).length>0) {
                binaryMinHeap.insert(new Pair<>(list.get(i)[0], i));
            }
            kPointers[i]=0;
        }

        int A[] = new int[totalCount];

        int i=0;
        while (!binaryMinHeap.isEmpty()){
            Pair pair = binaryMinHeap.extractMin();
            A[i++] = (Integer) pair.getKey();

            int minValueArrayIndex = (Integer) pair.getValue();
            kPointers[minValueArrayIndex]++;
            if(kPointers[minValueArrayIndex]<list.get(minValueArrayIndex).length){
                binaryMinHeap.insert(new Pair<>(list.get(minValueArrayIndex)[kPointers[minValueArrayIndex]], minValueArrayIndex));
            }
        }

        return A;
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
}
