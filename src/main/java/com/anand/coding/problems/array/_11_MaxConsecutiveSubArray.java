package com.anand.coding.problems.array;

/**
 * Given an array of unsorted positive integers,
 * 1. find the length of maximum sub array containing consecutive integers.
 * 2. find the length of maximum sub array containing increasing integers.
 */
public class _11_MaxConsecutiveSubArray {

    /**
     *
     * @param A
     * @return
     */
    public static int maxConsecutiveSubArray(int [] A){

        int maxConsecutiveLength=0;

        int j;
        for(int i=0; i<A.length; i=j){
            for(j=i+1; j<A.length && A[j]-1==A[j-1]; j++);
            maxConsecutiveLength = Math.max(j-i, maxConsecutiveLength);
        }

        return maxConsecutiveLength;
    }

    /**
     *
     * @param A
     * @return
     */
    public static int maxIncreasingSubArray(int [] A){

        int maxConsecutiveLength=0;

        int j;
        for(int i=0; i<A.length; i=j){
            for(j=i+1; j<A.length && A[j]>A[j-1]; j++);
            maxConsecutiveLength = Math.max(j-i, maxConsecutiveLength);
        }

        return maxConsecutiveLength;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        int A[] = {5,6,4,8,2,3,1,3,8};
        System.out.println(maxConsecutiveSubArray(A));
        System.out.println(maxIncreasingSubArray(A));

    }
}
