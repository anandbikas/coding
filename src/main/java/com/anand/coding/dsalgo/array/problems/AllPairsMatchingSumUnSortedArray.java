package com.anand.coding.dsalgo.array.problems;

import com.anand.coding.dsalgo.array.Array;

import java.util.*;

/**
 *
 */
public class AllPairsMatchingSumUnSortedArray {

    /**
     * Complexity O(n2)
     *
     * @param A
     * @param sum
     * @return
     */
    public static List<int []> findAllPairsWithMatchingSum(int A[], final int sum){

        List<int []> list = new ArrayList<>();

        for(int i=0; i<A.length; i++){
            for(int j=i+1; j<A.length; j++){
                if(A[i]+A[j] == sum){
                    list.add(new int[]{A[i],A[j]});
                }
            }
        }
        return list;
    }

    /**
     * Complexity O(n log n) using binarySearch
     *
     * @param A
     * @param sum
     * @return
     */
    public static List<int []> findAllPairsWithMatchingSumNLogN(int []A, final int sum){

        Arrays.sort(A);
        List<int []> list = new ArrayList<>();

        for(int i=0; i<A.length-1; i++){
            int j = Arrays.binarySearch(A, i+1, A.length, sum-A[i]);
            if( j >= 0){
                list.add(new int[]{A[i],A[j]});
            }
        }
        return list;
    }

    /**
     * using HashMap
     *
     * Complexity: O(n)
     *
     * @param A
     * @param sum
     * @return
     */
    public static List<int []> findAllPairsWithMatchingSumUsingHashMap(int []A, final int sum){

        List<int []> list = new ArrayList<>();

        //list takes care for duplicate values
        Map<Integer, ArrayList<Integer>> valueIndexesMap = new HashMap<>();

        for(int i=0; i<A.length; i++){
            int value = A[i];
            if(!valueIndexesMap.containsKey(value)){
                valueIndexesMap.put(value, new ArrayList<>());
            }
            valueIndexesMap.get(value).add(i);
        }

        for(int i=0; i<A.length; i++){
            int n1 = A[i];
            int n2 = sum-A[i];

            if(valueIndexesMap.containsKey(n2) && valueIndexesMap.get(n2)!=null){

                for(int n1Index: valueIndexesMap.get(n1)) {
                    for (int n2Index : valueIndexesMap.get(n2)) {
                        // Skip if same index is found.
                        if (n1Index == n2Index) {
                            continue;
                        }
                        // Index can also be added depending on requirement.
                        list.add(new int[]{n1, n2});
                    }
                }
                valueIndexesMap.remove(n1);
                valueIndexesMap.remove(n2);
            }
        }
        return list;
    }

    public static void main(String [] args){

        int A[] = new int[]{7, 3, 2, 5, 3, 1, 6, 4};

        int matchingSum =9;
        List<int []> matchingSumPairs = findAllPairsWithMatchingSumNLogN(A, matchingSum);
        System.out.println("matchingSumPairs for sum  = " + matchingSum);
        matchingSumPairs.forEach(Array::display);
        System.out.println();

        matchingSumPairs = findAllPairsWithMatchingSum(A, matchingSum);
        System.out.println("matchingSumPairs for sum  = " + matchingSum);
        matchingSumPairs.forEach(Array::display);
        System.out.println();

        matchingSumPairs = findAllPairsWithMatchingSumUsingHashMap(A, matchingSum);
        System.out.println("matchingSumPairs for sum  = " + matchingSum);
        matchingSumPairs.forEach(Array::display);
        System.out.println();

    }
}
