package com.anand.coding.problems.dp;

/**
 * Find longest increasing sub sequence from an array of integers.
 *
 */
public class _09_LongestIncreasingSubsequence {


    public static int lis(int []A){

        int n = A.length;
        if(n<=1){
            return n;
        }

        int [] DP = new int[n];
        int max = 0;
        for(int i=0; i<n; i++){
            DP[i]=1;
            for(int j=i-1; j>=0; j--){
                if(A[j]<=A[i] && DP[j]+1>=DP[i]){
                    DP[i] = DP[j]+1;
                }
            }
            if(DP[i]>max){
                max = DP[i];
            }
        }

        for(int i=0; i<n; i++){
            System.out.print(DP[i] + "    ");
        }
        System.out.println();

        //Print sequence
        int value = max;
        while(--n>=0){
            if(DP[n]==value){
                System.out.print(A[n] + "    ");
                value--;
            }
        }
        System.out.println();

        return max;
    }


    /**
     *
     * @param args
     */
    public static void main(String []args){

        int A[] = {10, 20, 9, 33, 21, 50, 41, 60, 80,1};

        System.out.println(lis(A));


    }
}
