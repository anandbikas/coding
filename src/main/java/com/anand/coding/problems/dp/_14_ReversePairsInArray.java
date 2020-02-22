package com.anand.coding.problems.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * Find all the reverse pairs in an array.
 * (i, j) is a reverse pair if i < j and A[i] > 2*A[j]
 *
 */
public class _14_ReversePairsInArray {

    /**
     * Using DP with 2D array
     *
     * @param A
     * @return
     */
    public static int allReversePairs1(int[] A) {

        int n = A.length;
        if(n<=1){
            return 0;
        }

        int [][]DP  = new int[n][n];

        // Reverse pairs for 1 element array is 0

        // Now calculate for 2 elements.
        for(int i=0; i<n-1; i++){
            int j= i+1;
            DP[i][j] = ((A[i]-1)>>1) >= A[j] ? 1: 0;
        }

        // Now calculate for 3 elements onward.
        for(int k=3; k<=n; k++){

           for(int i=0; i<n-k+1; i++){
               int j= i+k-1;
               DP[i][j] = DP[i][j-1] + DP[i+1][j] - DP[i+1][j-1]  + (((A[i]-1)>>1) >= A[j] ? 1: 0);
           }
        }

        printDPArray(DP,n-1,n-1);

        return DP[0][n-1];
    }

    /**
     * Using DP  with hashMap on key="i.j" to save space
     * @param A
     * @return
     */
    public static int allReversePairs2(int[] A) {

        int n = A.length;
        if(n<=1){
            return 0;
        }

        Map<String,Integer> map = new HashMap<>();

        // Calculate for 1 and 2 elements.
        for(int i=0; i<n-1; i++){
            map.put(String.format("%d.%d",i,i),0);
            int j= i+1;
            map.put(String.format("%d.%d",i,j),((A[i]-1)>>1) >= A[j] ? 1: 0);
        }

        // Now calculate for 3 elements onward.
        for(int k=3; k<=n; k++){

            for(int i=0; i<n-k+1; i++){
                int j= i+k-1;
                map.put(String.format("%d.%d",i,j),
                        map.get(String.format("%d.%d",i,j-1))
                            + map.get(String.format("%d.%d",i+1,j))
                                - map.get(String.format("%d.%d",i+1,j-1))
                                    + (((A[i]-1)>>1) >= A[j] ? 1: 0));
            }
        }

        return map.get(String.format("%d.%d",0,n-1));
    }

    /**
     *
     * @param A
     * @return
     */
    public static int allReversePairs(int[] A) {
        Map<String,Integer> map = new HashMap<>();

        return allReversePairsRec(A,0,A.length-1,map);
    }

    /**
     * Top-Down Recursive using memoization.
     *
     * @param A
     * @param i
     * @param j
     * @param map
     * @return
     */
    public static int allReversePairsRec(int[] A, int i, int j, Map<String, Integer> map) {

        final String key = String.format("%d.%d",i,j);

        int n = j-i+1;
        if(n<=1){
            map.put(key,0);
            return 0;
        }

        if(n==2){
            map.put(key,((A[i]-1)>>1) >= A[j] ? 1: 0);
            return ((A[i]-1)>>1) >= A[j] ? 1: 0;
        }

        if(map.containsKey(key)){
            return map.get(key);
        }

        map.put(key, allReversePairsRec(A,i,j-1,map)
                        + allReversePairsRec(A,i+1,j,map)
                        - allReversePairsRec(A,i+1,j-1,map)
                        + (((A[i]-1)>>1) >= A[j] ? 1: 0));

        return map.get(key);
    }

    /**
     *
     * @param A
     * @return
     */
    public static int allReversePairsNLogN(int[] A) {
        //TODO: Implmentation
        return 0;
    }

    public static void main(String []args){

        int [] A = {1,3,2,3,1};
        System.out.println(allReversePairs(A));

        int [] B = {2,4,3,5,1};
        System.out.println(allReversePairs(B));

        int [] C = {2147483647,2147483647,2147483647,2147483647,2147483647,2147483647};
        System.out.println(allReversePairs(C));

        int [] D = {2147483647,2147483647,2147483647,2147483647,2147483647,2147483647};
        System.out.println(allReversePairs(D));

    }

    /**
     *
     * @param DP
     * @param n
     * @param m
     */
    public static void printDPArray(int [][] DP, int n, int m)
    {
        System.out.println();
        for(int i=0; i<=n; i++){

            for(int j =0; j<=m; j++){
                System.out.print(String.format("%4d", DP[i][j]));
            }
            System.out.println();
        }
    }
}
