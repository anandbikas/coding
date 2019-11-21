package com.anand.coding.problems.dp;

/**
 * Given two strings A and B, find the longest common sub-string in the two strings.
 */
public class _07_LongestCommonSubString {

    /**
     * DP Tabulation solution
     *
     * @param A
     * @param n
     * @param B
     * @param m
     * @return
     */
    public static String lcs(char[] A, int n, char[] B, int m) {
        int DP[][] = new int[n+1][m+1];

        int maxI=0, maxJ=0;

        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){

                if(A[i-1] == B[j-1]) {
                    DP[i][j] = 1 + DP[i-1][j-1];

                    if (DP[i][j] > DP[maxI][maxJ]) {
                        maxI=i; maxJ=j;
                    }
                }
            }
        }

        printDPArray(DP,n,m);
        int maxLength = DP[maxI][maxJ];

        return new String(A, maxI-maxLength, maxLength);
    }

    /**
     * Recursive Top Down with memoization
     *
     * @param A
     * @param n
     * @param B
     * @param m
     * @return
     */
    public static String lcsRec(char[] A, int n, char[] B, int m) {

        int [][]DP = new int[n+1][m+1];

        lcsRec(A, n, B, m, DP);
        printDPArray(DP, n, m);

        int maxI=0, maxJ=0;

        for(int i=0; i<=n; i++){
            for(int j=0; j<=m; j++){
                if (DP[i][j] > DP[maxI][maxJ]) {
                    maxI=i;
                    maxJ=j;
                }
            }
        }

        int maxLength = DP[maxI][maxJ];

        return (new String(A, maxI-maxLength, maxLength));
    }
    private static int lcsRec(char[] A, int n, char[] B, int m, int [][]DP) {

        if (n == 0 || m == 0) {
            return 0;
        }

        //Already calculated?
        //TODO: how to handle the calculated elements with value =0?
        //Integer array can be used in place of int.
        if(DP[n][m] > 0){
            return DP[n][m];
        }

        if(A[n-1] == B[m-1]) {
            DP[n][m] = 1 + lcsRec(A, n-1, B, m-1, DP);
        } else {
            lcsRec(A, n, B, m - 1, DP);
            lcsRec(A, n - 1, B, m, DP);
        }

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
         *    0   1   0   0   0   0   0   0   0   0
         *    0   0   2   0   0   0   0   0   0   0
         *    0   0   0   3   0   0   0   0   0   0
         *    0   0   0   0   0   0   0   0   0   0
         *    0   0   0   0   0   0   0   0   0   0
         *    0   0   0   0   0   0   1   0   0   0
         *    0   0   0   0   0   0   0   2   0   1
         *    0   0   0   0   0   0   0   0   3   0
         *    0   0   0   0   0   0   0   1   0   4
         * java
         */
        System.out.println(lcs(A, A.length, B, B.length));


        /**
         *    0   0   0   0   0   0   0   0   0   0
         *    0   1   0   0   0   0   0   0   0   0
         *    0   0   2   0   0   0   0   0   0   0
         *    0   0   0   3   0   0   0   0   0   0
         *    0   0   0   0   0   0   0   0   0   0
         *    0   0   0   0   0   0   0   0   0   0
         *    0   0   0   0   0   0   1   0   0   0
         *    0   0   0   0   0   0   0   2   0   0
         *    0   0   0   0   0   0   0   0   3   0
         *    0   0   0   0   0   0   0   0   0   4
         * java
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
