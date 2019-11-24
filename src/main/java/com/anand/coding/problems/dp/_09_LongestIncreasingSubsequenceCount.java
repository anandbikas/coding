package com.anand.coding.problems.dp;

/**
 * Find total count of longest increasing sub sequence from an array of integers.
 *
 */
public class _09_LongestIncreasingSubsequenceCount {


    public static int lisCount(int []A){

        int n = A.length;
        if(n<=1){
            return n;
        }

        int [] DP = new int[n];
        int [] COUNT = new int[n];

        int max = 0;
        for(int i=0; i<n; i++){
            DP[i]=1;
            COUNT[i]=1;
            for(int j=i-1; j>=0; j--){
                if(A[j]<A[i]){
                    if(DP[j]+1>DP[i]){
                        DP[i] = DP[j]+1;
                        COUNT[i] = COUNT[j];
                    } else if(DP[j]+1==DP[i]){
                        COUNT[i] +=COUNT[j];
                    }
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
        for(int i=0; i<n; i++){
            System.out.print(COUNT[i] + "    ");
        }
        System.out.println();

        int totalCount=0;
        for(int i=0; i<n; i++) {
            if(DP[i]==max){
                totalCount += COUNT[i];
            }
        }
        return totalCount;
    }


    /**
     *
     * @param args
     */
    public static void main(String []args){

        int A[] = {10, 20, 9, 33, 21, 50, 41, 60, 80,1};

        System.out.println(lisCount(A));

        int B[] = {1,3,5,4,7};
        System.out.println(lisCount(B));

        int C[] = {2,2,2,2,2};
        System.out.println(lisCount(C));

        int D[] = {1,2,4,3,5,4,7,2};
        System.out.println(lisCount(D));
    }
}
