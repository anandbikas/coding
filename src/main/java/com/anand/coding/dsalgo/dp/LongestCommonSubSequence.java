package com.anand.coding.dsalgo.dp;

/**
 * Given two strings A and B, find the longest common sub-sequence in the two strings.
 */
public class LongestCommonSubSequence {

    //TODO: print the lcs string
    /**
     * Recursive Top Down with memoization
     *
     * @param A
     * @param n
     * @param B
     * @param m
     * @return
     */
    public static int lcsRec(char[] A, int n, char[] B, int m) {
        int [][]DP = new int[n+1][m+1];

        int lcsLength = lcsRec(A, n, B, m, DP);
        printDPArray(DP, n, m);

        return lcsLength;
    }
    private static int lcsRec(char[] A, int n, char[] B, int m, int [][]DP) {

        if (n == 0 || m == 0) {
            return 0;
        }

        //Already calculated?
        if(DP[n][m] >0){
             return DP[n][m];
        }

        if (A[n-1] == B[m-1]) {
            return DP[n][m] = 1 + lcsRec(A, n-1, B, m-1, DP);
        }

        return DP[n][m] = Math.max(lcsRec(A, n, B, m - 1, DP), lcsRec(A, n - 1, B, m, DP));
    }

    /**
     * DP Tabulation solution
     *
     * @param A
     * @param n
     * @param B
     * @param m
     * @return
     */
    public static int lcs(char[] A, int n, char[] B, int m) {
        int DP[][] = new int[n+1][m+1];

        for(int i=1; i<=n; i++){

            for(int j=1; j<=m; j++){

                if(A[i-1] == B[j-1]) {
                    DP[i][j] = 1 + DP[i-1][j-1];
                }
                else {
                    DP[i][j] =  Math.max(DP[i-1][j], DP[i][j-1]);
                }
            }
        }

        printDPArray(DP,n,m);
        return DP[n][m];
    }


    /**
     * @param args
     */
    public static void main(String[] args) {

        char[] A = "cupinjava".toCharArray();
        char[] B = "cupofjava".toCharArray();

        /**
         *    0   0   0   0   0   0   0   0   0   0
         *    0   1   1   1   1   1   1   1   1   1
         *    0   1   2   2   2   2   2   2   2   2
         *    0   1   2   3   3   3   3   3   3   3
         *    0   1   2   3   3   3   3   3   3   3
         *    0   1   2   3   3   3   3   3   3   3
         *    0   1   2   3   3   3   4   4   4   4
         *    0   1   2   3   3   3   4   5   5   5
         *    0   1   2   3   3   3   4   5   6   6
         *    0   1   2   3   3   3   4   5   6   7
         * 7
         */
        System.out.println(lcs(A, A.length, B, B.length));


        /**
         *    0   0   0   0   0   0   0   0   0   0
         *    0   1   1   1   1   1   0   0   0   0
         *    0   1   2   2   2   2   0   0   0   0
         *    0   1   2   3   3   3   0   0   0   0
         *    0   1   2   3   3   3   0   0   0   0
         *    0   1   2   3   3   3   0   0   0   0
         *    0   0   0   0   0   0   4   0   0   0
         *    0   0   0   0   0   0   0   5   0   0
         *    0   0   0   0   0   0   0   0   6   0
         *    0   0   0   0   0   0   0   0   0   7
         * 7
         */
        System.out.println(lcsRec(A, A.length, B, B.length));
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

            for(int w =0; w<=m; w++){
                System.out.print(String.format("%4d", DP[i][w]));
            }
            System.out.println();
        }
    }
}
