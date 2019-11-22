package com.anand.coding.problems.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class _07_SubArrayWithMaximumSum {

    /**
     * Problem:
     * Find a sub-array with maximum sum in an array of integers.
     *
     * Solution O(n2):

     * Example:
     *  A= [1, 2, 3, -2, 5]
     *
     *  Result:
     *      Sum=9, Sub-Array[1, 2, 3, -2, 5]
     *
     * @param A
     * @return
     */
    public static int findSubArrayMaximumSum(int [] A){

        if(A.length ==0){
            return 0;
        }

        int maximumSum = A[0];
        int sum= A[0];

        for(int i=1; i<A.length; i++){
            sum = Math.max(A[i], sum+A[i]);
            maximumSum = Math.max(sum,maximumSum);

        }
        return maximumSum;
    }

    /**
     *
     * @param args
     */
    public static void main1(String [] args){

        int [] A = new int[]{1, 2, 3, -2, 5};

        int sum  = findSubArrayMaximumSum(A);
        System.out.println(sum);
    }

    /**
     * Test Case Example:
     *
     *Input
     * 2
     * 5
     * 1 2 3 -2 5
     * 4
     * -1 -2 -3 -4
     * Output
     * 9
     * -1
     *
     * @param args
     */
    public static void main(String [] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs;
        final String SPACE_REGEX = "\\s+";

        int t = Integer.parseInt(br.readLine());

        while(t-- > 0) {

            strs = br.readLine().split(SPACE_REGEX);
            int n = Integer.parseInt(strs[0]);

            strs = br.readLine().split(SPACE_REGEX);
            int[] A = new int[n];
            for (int i = 0; i < n; i++) {
                A[i] = Integer.parseInt(strs[i]);
            }

            int sum  = findSubArrayMaximumSum(A);
            System.out.println(sum);
        }
    }
}
