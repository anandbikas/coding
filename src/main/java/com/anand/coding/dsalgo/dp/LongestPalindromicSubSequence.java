package com.anand.coding.dsalgo.dp;

/**
 * Given a string A, find the longest palindromic sub-sequence.
 */
public class LongestPalindromicSubSequence {

    /**
     * DP Tabulation solution
     *
     * @param A
     * @return  lps
     */
    public static int lps(char[] A) {
        int n = A.length;

        int [][]DP = new int[n+1][n+1];

        if(n==0){
            return 0;
        }
        //String of length=1 and 0 is palindromic
        for(int i=0; i<=n; i++) {
            DP[0][i] = 0;
            DP[i][i] = 1;
        }

        //String of length=2 is palindromic
        for(int i=1; i<=n-1; i++) {
            DP[i][i+1] = A[i-1]==A[i] ? 2 : 1;
        }

        //length 3 onward
        for(int numberOfElement=3; numberOfElement<=n; numberOfElement++){

            //To process DP one side only
            for(int i=1; i+numberOfElement-1<=n; i++){
                int j = i+numberOfElement-1;

                if(A[i-1]==A[j-1]) {
                    DP[i][j] = 2 + DP[i+1][j-1];

                } else {
                    DP[i][j] = Math.max(DP[i+1][j], DP[i][j-1]);
                }
            }
        }

        printDPArray(DP,n,n);
        return (DP[1][n]);
    }


    /*
     * @param A
     * @return
     */
    public static int lpsRec(char[] A) {

        int n = A.length;

        if(n==0){
            return 0;
        }

        int [][]DP = new int[n+1][n+1];

        int lpsLength = lpsRec(A,1, A.length, DP);
        printDPArray(DP,n,n);

        return lpsLength;
    }

    /**
     *
     * @param A
     * @param l
     * @param r
     * @param DP
     */
    private static int lpsRec(char[] A, int l, int r, int [][]DP) {

        if(l>r){
            return 0;
        }

        if(l==r){
            return DP[l][r]= 1;
        }

        //Already calculated?
        if(DP[l][r] >0){
            return DP[l][r];
        }

        if(A[l-1] == A[r-1]) {
            return DP[l][r] = 2 + lpsRec(A, l+1, r-1, DP);
        }

        return DP[l][r] = Math.max(lpsRec(A, l+1, r, DP), lpsRec(A, l, r-1, DP));
    }


    /**
     * @param args
     */
    public static void main(String[] args) {

        char[] A = "anjbna".toCharArray();


        /**
         *    1   0   0   0   0   0   0
         *    0   1   1   1   1   3   5
         *    0   0   1   1   1   3   3
         *    0   0   0   1   1   1   1
         *    0   0   0   0   1   1   1
         *    0   0   0   0   0   1   1
         *    0   0   0   0   0   0   1
         * 5
         */
        System.out.println(lps(A));


        /**
         *    0   0   0   0   0   0   0
         *    0   0   0   0   0   0   5
         *    0   0   0   0   0   3   0
         *    0   0   0   1   1   0   0
         *    0   0   0   0   1   0   0
         *    0   0   0   0   0   0   0
         *    0   0   0   0   0   0   0
         * 5
         */
        System.out.println(lpsRec(A));
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
