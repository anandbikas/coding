package com.anand.coding.dsalgo.dp;

/**
 * Given a string A, find the longest palindromic sub-string.
 */
public class LongestPalindromicSubString {

    /**
     * DP Tabulation solution
     *
     * @param A
     * @return  lps
     */
    public static String lps(char[] A) {
        int n = A.length;

        char [][]DP = new char[n+1][n+1];

        if(n==0){
            return null;
        }
        //String of length=1 and 0 is palindromic
        for(int i=0; i<=n; i++) {
            DP[i][i] = DP[i][0] = DP[0][i] = 'y';

        }

        int iMax=1, jMax=1;

        //String of length=2 is palindromic
        for(int i=1; i<=n-1; i++) {
            DP[i][i+1] = DP[i+1][i] = A[i-1]==A[i] ? 'y' : '-';
            if(DP[i][i+1]=='y' && jMax-iMax < 1){
                iMax=i;
                jMax=i+1;
            }
        }

        //length 3 onward
        for(int numberOfElement=3; numberOfElement<=n; numberOfElement++){

            //To process DP one side only
            for(int i=1; i+numberOfElement-1<=n; i++){
                int j = i+numberOfElement-1;

                if(A[i-1]==A[j-1]) {
                    DP[i][j] = DP[j][i] = DP[i+1][j-1];

                    if(DP[i][j]=='y' && jMax-iMax < j-i){
                        iMax=i;
                        jMax=j;
                    }
                } else {
                    DP[i][j] = DP[j][i] = '-';
                }
            }
        }

        printDPArray(DP,n,n);
        return (new String(A, iMax-1, jMax-iMax+1));
    }

     /*
     * @param A
     * @return
     */
    public static String lpsRec(char[] A) {

        int n = A.length;

        if(n==0){
            return null;
        }

        char [][]DP = new char[n+1][n+1];
        int [] resultedIndex = new int[]{1,1};

        lpsRec(A,1, A.length, DP, resultedIndex);
        printDPArray(DP,n,n);

        int iMax = resultedIndex[0];
        int jMax = resultedIndex[1];

        return (new String(A, iMax-1, jMax-iMax+1));
    }

    /**
     *
     * @param A
     * @param l
     * @param r
     * @param DP
     * @param resultedIndex
     */
    private static char lpsRec(char[] A, int l, int r, char [][]DP, int []resultedIndex) {

        if(l>r){
            return 'y';
        }

        //Already calculated?
        if(DP[l][r] == 'y' || DP[l][r] == '-'){
            return DP[l][r];
        }

        char result = '-';

        if(A[l-1] == A[r-1]) {
            result = lpsRec(A, l + 1, r - 1, DP, resultedIndex);
        }

        if(result == 'y') {
            if (resultedIndex[1] - resultedIndex[0] < r - l) {
                resultedIndex[0] = l;
                resultedIndex[1] = r;
            }
        } else {
            lpsRec(A, l + 1, r, DP, resultedIndex);
            lpsRec(A, l, r - 1, DP, resultedIndex);
        }

        return  DP[l][r] = DP[r][l] = result;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {

        char[] A = "axanjnax".toCharArray();

        /**
         *
         *    y   y   y   y   y   y   y   y   y
         *    y   y   -   y   -   -   -   -   -
         *    y   -   y   -   -   -   -   -   y
         *    y   y   -   y   -   -   -   y   -
         *    y   -   -   -   y   -   y   -   -
         *    y   -   -   -   -   y   -   -   -
         *    y   -   -   -   y   -   y   -   -
         *    y   -   -   y   -   -   -   y   -
         *    y   -   y   -   -   -   -   -   y
         * xanjnax
         */
        System.out.println(lps(A));


        /**
         *
         *                                     
         *                y   -   -   -   -   -
         *            y   -   -   -   -   -   y
         *        y   -   y   -   -   -   y    
         *        -   -   -   y   -   y        
         *        -   -   -   -   y            
         *        -   -   -   y                
         *        -   -   y                    
         *        -   y                        
         * xanjnax
         */
        System.out.println(lpsRec(A));
    }

    /**
     *
     * @param DP
     * @param n
     * @param m
     */
    public static void printDPArray(char [][] DP, int n, int m)
    {
        System.out.println();
        for(int i=0; i<=n; i++){

            for(int w =0; w<=m; w++){
                System.out.print(String.format("%4c", DP[i][w]));
            }
            System.out.println();
        }
    }
}
