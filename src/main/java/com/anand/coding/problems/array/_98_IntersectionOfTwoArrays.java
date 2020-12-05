package com.anand.coding.problems.array;

import java.util.*;

/**
 *
 */
public class _98_IntersectionOfTwoArrays {


    /**
     * Given two arrays, write a function to compute their intersection. The result can be in any order.
     *
     * Follow up:
     *
     * 1. If the given arrays are already sorted, traverse both and build the intersection.
     * 2. If elements of array A are stored on disk, and the memory is limited, Create map of the other array B and
     *    read array A in chunks to build the intersection.
     * 3. If both the arrays can't fit in the memory, sort (external) them and read both in chunks to build the intersection.
     *
     * @param A
     * @param B
     * @return
     */
    public static int[] intersection(int A[], int B[]){

        Map<Integer,Integer> map = new HashMap<>();

        for(int x: A){
            int count=0;
            if(map.containsKey(x)){
                count=map.get(x);
            }
            map.put(x, count+1);
        }

        List<Integer> list = new ArrayList<>();

        for(int y: B){
            if(map.containsKey(y) && map.get(y)>0){
                list.add(y);
                map.put(y,map.get(y)-1);
            }
        }

        return list.stream().mapToInt(i->i).toArray();

    }

    /**
     *
     * @param args
     */
    public static void main(String args[]){

        int A[] = {4,9,5};
        int B[] = {9,4,9,8,4};

        System.out.print(Arrays.toString(intersection(A,B)));
    }
}

