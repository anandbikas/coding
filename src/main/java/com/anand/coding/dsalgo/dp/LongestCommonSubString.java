package com.anand.coding.dsalgo.dp;

/**
 * Given two strings A and B, find the longest common sub-string in the two strings.
 */
public class LongestCommonSubString {

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

        int maxI=0;
        int maxJ=0;

        for(int i=1; i<=n; i++){

            for(int j=1; j<=m; j++){

                if(A[i-1] == B[j-1]) {
                    DP[i][j] = 1 + DP[i-1][j-1];

                    if (DP[i][j] > DP[maxI][maxJ]) {
                        maxI=i;
                        maxJ=j;
                    }
                }
            }
        }

        printDPArray(DP,n,m);
        int maxLength = DP[maxI][maxJ];

        System.out.println(new String(A, maxI-maxLength, maxLength));
        return maxLength;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {

        char[] A = "geeksforgeeksstring".toCharArray();
        char[] B = "geeksquizstring".toCharArray();

        System.out.println(lcs(A, A.length, B, B.length));
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
