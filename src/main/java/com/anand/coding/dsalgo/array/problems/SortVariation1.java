package com.anand.coding.dsalgo.array.problems;

import com.anand.coding.dsalgo.tree.map.BSTMap;

import java.util.*;

/**
 * Sort an array according to absolute difference with a given value diff.
 *
 * Input : arr[] : x = 7, arr[] = {10, 5, 3, 9, 2}
 * Output : arr[] = {5, 9, 10, 3, 2}
 *
 * Explanation:
 * 7 - 10 = 3(abs)
 * 7 - 5 = 2
 * 7 - 3 = 4
 * 7 - 9 = 2(abs)
 * 7 - 2 = 5
 * So according to the difference with X,
 * elements are arranged as 5, 9, 10, 3, 2.
 *
 */
public class SortVariation1 {

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        int arr[] = {10, 5, 3, 9, 2};
        int diff = 7;

        BSTMap<Integer, List<Integer>> bstMap = new BSTMap<>();

        Arrays.stream(arr).forEach(num -> {
            int key = Math.abs(num-diff);
            if(!bstMap.contains(key)){
                bstMap.put(key, new ArrayList<>());
            }
            bstMap.get(key).add(num);
        });

        System.out.println(bstMap.values());
        System.out.println();


        //Using HashMap
        Map<Integer, List<Integer>> hashMap = new HashMap<>();

        Arrays.stream(arr).forEach(num -> {
            int key = Math.abs(num-diff);
            if(!hashMap.containsKey(key)){
                hashMap.put(key, new ArrayList<>());
            }
            hashMap.get(key).add(num);
        });

        System.out.println(hashMap.values());
    }
}
