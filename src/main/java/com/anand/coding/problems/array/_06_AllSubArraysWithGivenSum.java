package com.anand.coding.problems.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class _06_AllSubArraysWithGivenSum {

    /**
     * Problem:
     * Find all possible sub-array's, sum of all the elements matching a given number S in an unsorted array A of size n with all non negative integers.
     *
     * Solution O(n2):

     * Example:
     *  A= [1, 3, 4, 5, 6]
     *  sum = 12
     *
     *  Result:
     *      Range(1, 3), Sub-Array[3, 4, 5]
     *
     * @param A
     * @param sum
     * @return
     */
    public static List<int[]> findSubArraysWithGivenSumNonNegativeNumbers(int [] A, int sum, boolean firstOccurrenceOnly){

        List<int[]> resultedList = new ArrayList<>();

        for(int i=0; i<A.length; i++){

            int calculatedSum = A[i];
            if(calculatedSum==sum){
                resultedList.add(new int[]{i,i});
                if(firstOccurrenceOnly) {
                    return resultedList;
                }
            }

            for(int j=i+1; j<A.length && calculatedSum<sum; j++) {
                calculatedSum += A[j];

                if(calculatedSum==sum){
                    resultedList.add(new int[]{i,j});
                    if(firstOccurrenceOnly) {
                        return resultedList;
                    }
                }
            }
        }

        return resultedList;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        int [] A = new int[]{1,3,4,5,6};

        List<int[]> resultedList = findSubArraysWithGivenSumNonNegativeNumbers(A, 12, false);

        resultedList.forEach(indexArray -> {
            for (int i = indexArray[0]; i <= indexArray[1]; i++) {
                System.out.print(A[i] + " ");
            }
            System.out.println();
        });
    }

    /**
     * Test Case Example:
     *
     * Input:
     * 2(test-cases)
     * 5(number-of-elements) 12(sum)
     * 1 2 3 7 5 (array)
     * 10 15
     * 1 2 3 4 5 6 7 8 9 10
     * 1 12
     * 8
     *
     * Output:
     * 2 4 (Sub-array 1 index range of first occurrence if any)
     * 1 5
     * -1  (-1 if no such sub-array found)
     *
     * @param args
     */
    public static void main1(String [] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs;
        final String SPACE_REGEX = "\\s+";

        int t = Integer.parseInt(br.readLine());

        while(t-- > 0) {

            strs = br.readLine().split(SPACE_REGEX);
            int n = Integer.parseInt(strs[0]);
            int sum = Integer.parseInt(strs[1]);

            strs = br.readLine().split(SPACE_REGEX);
            int[] A = new int[n];
            for (int i = 0; i < n; i++) {
                A[i] = Integer.parseInt(strs[i]);
            }

            List<int[]> resultedList = findSubArraysWithGivenSumNonNegativeNumbers(A, sum, true);

            if (resultedList.isEmpty()) {
                System.out.println(-1);
            } else {
                resultedList.forEach(indexArray -> {
                    System.out.println(String.format("%s %s", indexArray[0] + 1, indexArray[1] + 1));
                });
            }
        }
    }
}
