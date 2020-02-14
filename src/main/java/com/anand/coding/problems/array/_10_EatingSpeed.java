package com.anand.coding.problems.array;

import java.util.Arrays;

/**
 * There are n rooms each having random number of bananas. If you enter a room, you are locked for an hour there.
 * If the eating speed is k, you can eat <=k bananas per hour.
 *
 * Find the minimum speed k so that all the bananas can be eaten in H hours.
 */
public class _10_EatingSpeed {

    /**
     *
     * @param A
     * @return
     */
    public static int minEatingSpeed(int A[], int h){

        int i=1;
        int j=Arrays.stream(A).max().orElse(0);

        while (i<j){
            int mid = i + (j-i)/2;

            int hoursRequired=0;
            for(int p=0; p<A.length && hoursRequired<=h; p++){
                hoursRequired += Math.ceil(A[p]/(double)mid);
            }

            if(hoursRequired>h){
                i=mid+1;
            } else {
                j=mid;
            }
        }
        return i;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        int [] A = {3,6,7,11};
        System.out.println(minEatingSpeed(A, 8));

        int [] B = {30,11,23,4,20};
        System.out.println(minEatingSpeed(B, 5));

        int [] C = {30,11,23,4,20};
        System.out.println(minEatingSpeed(C, 6));
    }
}
