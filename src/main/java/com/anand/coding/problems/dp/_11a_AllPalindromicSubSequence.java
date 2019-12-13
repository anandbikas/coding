package com.anand.coding.problems.dp;

/**
 * Given a string A, count number of all the possible palindromic sub-sequence.
 * //TODO: Count distinct palindromes
 */
public class _11a_AllPalindromicSubSequence {

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
                DP[i][j] = DP[i][j-1] + DP[i+1][j] - DP[i + 1][j - 1];

                if(s.charAt(i-1)==s.charAt(j-1)) {
                    //1. Both will make another same number of palindromes when combined with all the palindromes in between them
                    //2. Both together will make one palindrome
                    DP[i][j] += DP[i+1][j-1] + 1;
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
         *    1   0   0   0   0
         *    0   1   2   4   9
         *    0   0   1   3   4
         *    0   0   0   1   2
         *    0   0   0   0   1
         * 9
         */
        System.out.println(lps("bccb")); //9

        System.out.println(lps("aaa"));  //7
        System.out.println(lps("aba"));  //5
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
