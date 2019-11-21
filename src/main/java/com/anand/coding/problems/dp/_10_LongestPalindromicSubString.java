package com.anand.coding.problems.dp;

/**
 * Given a string A, find the longest palindromic sub-string.
 */
public class _10_LongestPalindromicSubString {

    /**
     * DP Tabulation solution
     *
     * @param s
     * @return  lps
     */
    public static String lps(String s) {

        if(s==null || s.length()<2){
            return s;
        }

        int n = s.length();
        boolean [][]DP = new boolean[n+1][n+1];     //Left-Right (Is string from left index upto right index a palindrome?)

        //String of length=1 and 0 is palindromic
        for(int i=0; i<=n; i++) {
            DP[0][i] = DP[i][0]= true;    DP[i][i] = true;
        }

        int maxI=1, maxJ=1;

        //length 2 onwards
        for(int l=2; l<=n; l++){
            for(int i=1; i<=n-l+1; i++){

                int j = i+l-1;
                if(s.charAt(i-1)==s.charAt(j-1)) {
                    DP[i][j] = (j==i+1) || DP[i+1][j-1];

                    if (DP[i][j] && maxJ-maxI < j-1) {
                        maxI=i; maxJ=j;
                    }
                }
            }
        }

        printDPArray(DP,n,n);
        return (s.substring(maxI-1, maxJ));
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        /**
         *    1   1   1   1   1   1   1   1   1
         *    1   1   0   1   0   0   0   0   0
         *    1   0   1   0   0   0   0   0   1
         *    1   0   0   1   0   0   0   1   0
         *    1   0   0   0   1   0   1   0   0
         *    1   0   0   0   0   1   0   0   0
         *    1   0   0   0   0   0   1   0   0
         *    1   0   0   0   0   0   0   1   0
         *    1   0   0   0   0   0   0   0   1
         * xanjnax
         */
        System.out.println(lps("axanjnax"));

        System.out.println(lps("abcda"));

        System.out.println(lps("cbbd"));
    }

    /**
     *
     * @param DP
     * @param n
     * @param m
     */
    public static void printDPArray(boolean [][] DP, int n, int m)
    {
        System.out.println();
        for(int i=0; i<=n; i++){

            for(int w =0; w<=m; w++){
                System.out.print(String.format("%4d", DP[i][w] ? 1 : 0));
            }
            System.out.println();
        }
    }
}
