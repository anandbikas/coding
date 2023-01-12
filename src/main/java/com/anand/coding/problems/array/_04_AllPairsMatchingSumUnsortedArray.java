package com.anand.coding.problems.array;

import com.anand.coding.dsalgo.array.Array;

import java.util.*;

/**
 *
 */
public class _04_AllPairsMatchingSumUnsortedArray {

    public static List<int []> findAllPairsMatchingSum(int []A, final int target){

        Arrays.sort(A);
        List<int []> res = new ArrayList<>();

        for(int i=0, j=A.length-1; i<j;) {
            int sum = A[i]+A[j];
            if(sum == target) {
                res.add(new int[]{A[i],A[j]});
            }

            if (sum > target) j--; else i++;
        }

        return res;
    }

    public static List<int []> findAllPairsMatchingSumUsingHashMap(int []A, final int target){

        List<int []> res = new ArrayList<>();

        //list takes care for duplicate values
        Map<Integer, ArrayList<Integer>> indexMap = new HashMap<>();

        for(int i=0; i<A.length; i++){
            indexMap.computeIfAbsent(A[i], k->new ArrayList<>()).add(i);
        }

        for (int n1 : A) {
            int n2 = target - n1;

            if (indexMap.containsKey(n2)) {

                for (int i : indexMap.get(n1)) {
                    for (int j : indexMap.get(n2)) {
                        // Skip if same index is found.
                        if (i == j) continue;

                        // Index can also be added depending on requirement.
                        res.add(new int[]{n1,n2});
                    }
                }
                indexMap.remove(n1);
                indexMap.remove(n2);
            }
        }
        return res;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        int []A = new int[]{7, 3, 2, 5, 3, 1, 6, 4};

        List<int []> res = findAllPairsMatchingSum(A, 9);
        System.out.println("matchingSumPairs for sum  = " + 9);
        res.forEach(Array::display);

        int []B = new int[]{7, 3, 2, 5, 3, 1, 6, 4};
        res = findAllPairsMatchingSumUsingHashMap(B, 9);
        System.out.println("matchingSumPairs for sum  = " + 9);
        res.forEach(Array::display);
    }
}
