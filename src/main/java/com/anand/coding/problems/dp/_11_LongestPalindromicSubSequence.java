package com.anand.coding.problems.dp;

/**
 * Given a string A, find the longest palindromic sub-sequence.
 */
public class _11_LongestPalindromicSubSequence {

    /**
     * DP Tabulation solution
     *
     * @param s
     * @return  lps
     */
    public static int lps(String s) {

        if(s==null){
            return 0;
        }
        if(s.length()<2){
            return s.length();
        }

        int n = s.length();
        int [][]DP = new int[n+1][n+1];     //Left-Right (length of palindromic subsequence from left index upto right index a palindrome?)

        //String of length=1 and 0 is palindromic
        for(int i=0; i<=n; i++) {
            DP[0][i] = 0;   DP[i][i] = 1;
        }

        //length 2 onwards
        for(int l=2; l<=n; l++){
            for(int i=1; i<=n-l+1; i++){

                int j = i+l-1;
                if(s.charAt(i-1)==s.charAt(j-1)) {
                    DP[i][j] = 2 + DP[i + 1][j - 1];
                } else {
                    DP[i][j] = Math.max(DP[i+1][j], DP[i][j-1]);
                }
            }
        }

        printDPArray(DP,n,n);
        return (DP[1][n]);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

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
        System.out.println(lps("anjbna"));
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
