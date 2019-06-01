package com.anand.coding.dsalgo.array.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Find all the triplets such that the sum of two elements equals to the third element.
 */
public class Triplets {

    /**
     * O(n3)
     *
     * @param A
     * @return
     */
    public static List<int[]> triplets(int A[]){

        List<int[]> triplets= new ArrayList<>();

        for(int i=0; i<A.length; i++){
            for(int j=i+1; j<A.length; j++){
                for(int k=j+1; k<A.length; k++){

                    if(A[i]+A[j]==A[k] || A[j]+A[k]==A[i] || A[k]+A[i]==A[j]){
                        triplets.add(new int[]{A[i],A[j],A[k]});
                    }
                }
            }
        }

        return triplets;
    }


    /**
     * Efficient algorithm using sort.
     *
     * @param A
     * @return
     */
    public static List<int[]> triplets1(int A[]){

        List<int[]> triplets= new ArrayList<>();

        Arrays.sort(A);

        for(int i=0; i<A.length; i++){
            for(int j=i+1; j<A.length; j++){
                for(int k=j+1; k<A.length && A[i]+A[j]>=A[k]; k++){

                    if(A[i]+A[j]==A[k]){
                        triplets.add(new int[]{A[i],A[j],A[k]});
                    }
                }
            }
        }

        return triplets;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        int [] A = {1,5,3,2};

        List<int[]> triplets = triplets1(A);

        triplets.forEach(triplet -> {
            System.out.println(String.format("%s %s %s", triplet[0], triplet[1], triplet[2]));
        });
    }

    /**
     * Test Case Example:
     *
     * Input:
     * 2 (test-cases)
     * 4 (number-of-elements)
     * 1 5 3 2 (array)
     * 3
     * 3 2 7
     * Output:
     * 2 (number of triplets found)
     * -1
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

            strs = br.readLine().split(SPACE_REGEX);
            int[] A = new int[n];
            for (int i = 0; i < n; i++) {
                A[i] = Integer.parseInt(strs[i]);
            }

            List<int[]> triplets = triplets1(A);

            if (triplets.isEmpty()) {
                System.out.println(-1);
            } else {
                System.out.println(triplets.size());
            }
        }
    }
}
