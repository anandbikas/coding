package com.anand.coding.problems.tree.dp;

import java.util.stream.IntStream;

/**
 * Given integers from 1 to n, find count of all the unique BSTs.
 *
 */
public class _01_UniqueBSTCount {

    public static int maxUniqueBSTRec(int n) {

        int []DP = new int[n+1];
        DP[0]=1;
        return maxUniqueBSTRec(n,DP);
    }

    public static int maxUniqueBSTRec(int n, int []DP) {
        if(DP[n]!=0){
            return DP[n];
        }
        int total=0;
        for(int k=1; k<=n; k++){
            total += maxUniqueBSTRec(k-1, DP) *  maxUniqueBSTRec(n-k, DP);
        }

        return DP[n]=total;
    }

    public static int maxUniqueBSTs(int n) {

        int []DP = new int[n+1];
        DP[0]=1; DP[1]=1;

        for(int k=2; k<=n; k++){
            for(int j=1; j<=k; j++) {
                DP[k] +=  DP[j-1] * DP[k-j];
            }
        }
        return DP[n];
    }


    /**
     * @param args
     */
    public static void main(String[] args) {

        IntStream.range(1,20).forEach(n -> System.out.println(n + " : " + maxUniqueBSTs(n)));
    }
}
