package com.anand.coding.dsalgo.dp;

/**
 * CoinChange is like DifferentWaysToFindASum where different arrangements are not allowed.
 *
 * Given a set of coin denominations (infinite quantity per denominations), find total number of ways to form 'N'.
 *
 * or
 *s
 * Given 3 numbers {1, 3, 5}, find total number of ways to form 'N'
 * using the sum of the given three numbers(repetitions allowed but different arrangements not allowed).
 *
 */
public class DifferentWaysToFindASumCoinChange {


    /**
     *
     * @param A
     * @param sum
     * @return
     */
    public static long coinChangeRec(int []A , int sum) {

        int n = A.length;

        long [][]DP = new long[n+1][sum+1];

        // For sum == 0, the result is 1
        for(int i=1; i<=n; i++) {
            DP[i][0] = 1;
        }

        long result = coinChangeRec(A, n, sum, DP);

        printDPArray(DP,n, sum);
        return result;
    }
    private static long coinChangeRec(int []A, int n, int sum, long [][]DP) {

        if (sum < 0) {
            return 0;
        }

//        // Already populated in DP array
//        if(sum == 0){
//            return 1;
//        }

        if( n <= 0){
            return 0;
        }

        if(DP[n][sum]>0){
            return DP[n][sum];
        }

        return DP[n][sum] = coinChangeRec(A,n-1, sum, DP) + coinChangeRec(A, n, sum - A[n-1], DP);
    }

    /**
     *
     * @param A
     * @param sum
     * @return
     */
    public static long coinChangeIterative(int []A , int sum) {

        int n = A.length;

        long [][]DP = new long[n+1][sum+1];

        // For sum == 0, the result is 1
        for(int i=1; i<=n; i++) {
            DP[i][0] = 1;
        }


        //Populate DP from 1st element onwards
        for(int i=1; i<=n; i++) {

            int itemIndex = i - 1;
            for (int s = 1; s <= sum; s++) {

                if(A[itemIndex]>s){
                    DP[i][s] = DP[itemIndex][s];
                } else {
                    DP[i][s] = DP[itemIndex][s] + DP[i][s - A[itemIndex]];
                }
            }
        }

        printDPArray(DP,n, sum);
        return DP[n][sum];
    }


    /**
     * An efficient DP algorithm with O(sum) space complexity.
     *
     * @param A
     * @param sum
     * @return
     */
    public static long coinChangeIterativeEfficient(int []A, int sum) {
        long []DP = new long[sum+1];
        DP[0]=1;

        for(int x: A){
            for(int i=x; i<=sum; i++){
                DP[i] += DP[i-x];
            }
        }

        for(int i=0; i<=sum; i++){
            System.out.print(String.format("%10d", DP[i]));
        }
        System.out.println();
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

    /**
     *
     * @param DP
     * @param n
     * @param m
     */
    public static void printDPArray(long [][] DP, int n, int m)
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
