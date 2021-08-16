package com.anand.coding.problems.advanced;

import java.io.IOException;
import java.util.Arrays;

/**
 * In an array of integers, at a time an element can be incremented or decremented by one.
 *
 * Find minimum operations to make all the elements equal
 *
 * Solution:
 * 1. Average is not suitable for this operation in case.
 *
 * 2. Median helps identity the middle element to which other elements can be equated.
 */
public class _07_MinOperationsToMakeElementsEqual {

    /**
     *
     * @param A
     * @return
     */
    public static int minOperationsToMakeElementsEqual(int[] A) {

//        int sum = Arrays.stream(A).sum();
//        double doubleAvg =(double)sum/A.length;
//        int avg = (doubleAvg-(int)doubleAvg >0.5) ? (int)Math.ceil(doubleAvg) : (int)Math.floor(doubleAvg);
//
//        return Arrays.stream(A).map(x -> Math.abs(avg-x)).sum();


        Arrays.sort(A);

        int steps = 0;
        for (int i=0, j=A.length-1; i<j; i++, j--) {
            steps += A[j]-A[i];
        }
        return steps;
    }

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String [] args) throws IOException {

        System.out.println(minOperationsToMakeElementsEqual(new int[]{3,2,1,1,2,3,1}));
        System.out.println(minOperationsToMakeElementsEqual(new int[]{8,0,1,4,0,6}));

    }
}
