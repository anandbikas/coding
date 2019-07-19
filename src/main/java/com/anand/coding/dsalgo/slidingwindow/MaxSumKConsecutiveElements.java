package com.anand.coding.dsalgo.slidingwindow;

import java.io.IOException;

/**
 *
 */
public class MaxSumKConsecutiveElements {


    /**
     *
     * @param A
     * @return
     */
    public static int maxSumKConsecutiveElements(int [] A, int k){

        if(A.length<k){
            return 0;
        }

        int sumOfKElements=0;

        for(int i=0; i<k; i++){
            sumOfKElements += A[i];
        }

        int maxSum = sumOfKElements;
        for(int i=k; i<A.length; i++){
            sumOfKElements = sumOfKElements + A[i] - A[i-k];
            if(maxSum<sumOfKElements){
                maxSum = sumOfKElements;
            }
        }

        return maxSum;
    }

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String [] args) throws IOException {

        int [] A= {1,2,3,1,2,3,4,5,6,7,8,3};

        System.out.println(maxSumKConsecutiveElements(A,4));
    }
}
