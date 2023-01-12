package com.anand.coding.problems.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Find pair with given sum in an unsorted array. One number should be possibly the largest.
 *
 */
public class _03_PairMatchingSumUnsortedArray {

    public static int[] pairSum(int []A, int target){

        List<Integer> list = Arrays.stream(A).boxed().collect(Collectors.toList());
        Arrays.sort(A);

        for(int i=0, j=A.length-1; i<j;) {
            int sum = A[i]+A[j];
            if(sum == target) {
                int [] res = new int[]{list.indexOf(A[i]),list.lastIndexOf(A[j])};
                Arrays.sort(res);
                return res;
            }

            if (sum > target) j--; else i++;
        }

        return new int[]{-1,-1};
    }

    public static int[] pairSumUsingHashMap(int []A, int target){
        Map<Integer, Integer> indexMap = new HashMap<>();

        for(int i=0; i<A.length; i++) {
            int diff = target-A[i];
            if(indexMap.containsKey(diff)){
                return new int[]{indexMap.get(diff), i};
            }
            indexMap.put(A[i], i);
        }

        return new int[]{-1,-1};
    }

    /**
     *
     * @param args
     */
    public static void main(String []args){

        System.out.println(Arrays.toString(pairSum(new int[] {100,180,40,120,10}, 220)));
        System.out.println(Arrays.toString(pairSumUsingHashMap(new int[] {100,180,40,120,10}, 220)));
    }
}
