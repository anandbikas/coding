package com.anand.coding.problems.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _01_TheMissingNumber {

    /**
     * An array of size n-1 contains all unique integers from 1 to n with element missing. Find the missing integer.
     *
     * @return
     */
    public static int findTheMissingNumber(int [] A){

        int n = A.length+1;

        long sumFrom1ToN = n * (n+1) /2;

        return (int) (sumFrom1ToN - Arrays.stream(A).sum());

    }

    /**
     * In an array every element appears twice except one. Find that single number.
     *
     * Trick: A^B^C = (A^B)^C = A^(B^C)
     *
     * @param A
     * @return
     */
    public static int singleNumber(int[] A) {

        int result=0;
        for(int x: A){
            result ^= x;
        }

        return result;
    }

    /**
     * Test Case Example:
     *
     *Input
     * 2
     * 5
     * 1 2 3 5
     * 10
     * 1 2 3 4 5 6 7 8 10
     * Output
     * 4
     * 9
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
            int[] A = new int[n-1];
            for (int i = 0; i < n-1; i++) {
                A[i] = Integer.parseInt(strs[i]);
            }

            System.out.println(findTheMissingNumber(A));
        }
    }
}
