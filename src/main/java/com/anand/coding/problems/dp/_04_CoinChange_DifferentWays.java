package com.anand.coding.problems.dp;

import java.util.Arrays;

/**
 * CoinChange: DifferentWaysToFindASum where different arrangements are not allowed.
 *
 * Given a set of coin denominations (infinite quantity), find total number of ways to form 'N'
 * using the sum of the given three numbers(repetitions allowed but different arrangements not allowed).
 *
 * ex:
 *  A = {1, 3, 5}, find total number of ways to form N=8
 *
 * /problems/coin-change-ii
 */
public class _04_CoinChange_DifferentWays {

    public static long coinChangeRec(int []A , int sum) {

        int n = A.length;
        long [][] DP =  new long[n+1][sum+1];   //Item(coin)-Sum
        for(int i=1; i<=n; i++) {
            DP[i][0] = 1; // For sum == 0, the result is 1
        }

        long result = coinChangeRec(A, n, sum, DP);
        Util.printDPArray(DP,n, sum);

        return result;
    }
    private static long coinChangeRec(int []A, int n, int sum, long [][]DP) {

        if (n <= 0)  return 0;
        if (sum < 0) return 0;

        if(DP[n][sum]>0){
            return DP[n][sum];
        }

        return DP[n][sum] = coinChangeRec(A, n-1, sum, DP) + coinChangeRec(A, n, sum-A[n-1], DP);
    }

    /**
     *
     * @param A
     * @param sum
     * @return
     */
    public static long coinChangeIterative(int[] A , int sum) {

        int n = A.length;
        long [][]DP = new long[n+1][sum+1];     //Item(coin)-Sum
        for(int i=1; i<=n; i++) {
            DP[i][0] = 1; // For sum == 0, the result is 1
        }

        //Populate DP from 1st element onwards
        for(int i=1; i<=n; i++) {

            int item = i-1;
            for(int s=1; s<=sum; s++) {
                DP[i][s] = (A[item]>s)
                        ? DP[i-1][s]
                        : DP[i-1][s] + DP[i][s-A[item]];
                        // CoinChange is similar to unbounded Knapsack_0_1
            }
        }
        Util.printDPArray(DP,n, sum);

        return DP[n][sum];
    }

    /**
     * differentWaysToFindASum method
     * An efficient DP algorithm with O(sum) space complexity.
     *
     * @param A
     * @param sum
     * @return
     */
    public static long coinChangeIterativeEfficient(int []A, int sum) {
        long []DP = new long[sum+1];    //Sum
        DP[0]=1;

//      for(int i=1; i<=sum; i++) {
//            for (int x : A) {
//                if(x<=i) {
//                    DP[i] += DP[i-x];
//                }
//            }
//        }

        //Modify differentWaysToFindASum logic to consider coins first rather than sum.
        //This ensures, the coin is not repeated.
        for(int x: A) {
            for(int i=x; i<=sum; i++) {
                DP[i] += DP[i-x];
            }
        }

        System.out.println(Arrays.toString(DP));
        return DP[sum];
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){
        int []A = {1,3,5};

        System.out.println(coinChangeRec(A, 8));
        System.out.println(coinChangeIterative(A, 8));

        System.out.println(coinChangeIterativeEfficient(A, 8));
    }
}
