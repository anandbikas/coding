package com.anand.coding.problems.array;

/**
 * sum of the first and second largest value in an array.
 *
 * Complexity: O(n)
 */
public class _02_SumOfLargestPairUnsortedArray {


    /**
     *
     * @param A
     * @return
     */
    public static int sumOfLargestPair(int A[]){

        if(A.length<2){
            return Integer.MIN_VALUE;
        }

        int first, second;

        if(A[0]>A[1]){
            first = A[0];
            second = A[1];
        } else {
            first = A[1];
            second = A[0];
        }

        for(int i=2; i<A.length; i++){
            if(A[i]>first){
                second = first;
                first = A[i];
            } else if(A[i]>second && A[i] != first){
                second = A[i];
            }
        }

        return first + second;
    }

    /**
     *
     * @param args
     */
    public static void main(String []args){

        int A[] = {3,4,2,9,5,2,8,0};

        System.out.println(sumOfLargestPair(A));

    }
}
