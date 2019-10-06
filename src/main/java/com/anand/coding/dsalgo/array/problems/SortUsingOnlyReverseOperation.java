package com.anand.coding.dsalgo.array.problems;

import com.anand.coding.dsalgo.array.Array;

/**
 *
 */
public class SortUsingOnlyReverseOperation {

    /**
     *
     * @param A
     * @param right
     */
    public static void rev(int A[], int right){

        for (int left=0; left<right; left++, right--){
            int temp = A[left];
            A[left]=A[right];
            A[right]=temp;
        }
    }

    /**
     *
     * @param A
     */
    public static void sort(int A[]){

        Array.display(A);
        for(int i=0; i<A.length-1;){
            if(A[i]>A[i+1]){
                rev(A, i);              Array.display(A);
                rev(A, i+1);      Array.display(A);
                i=0;
            } else {
                i++;
            }
        }
    }


    /**
     *
     * @param args
     */
    public static void main(String [] args) {

        int A[] = {75,11,33,2,3,4,34,77};
        Array.display(A);
        sort(A);

        for(int i=0; i<=9; i++) {
            for (int j = 0; j <= 9; j++) {
                for(int k=0; k<=9; k++){
                    for(int l=0; l<=9; l++) {

                        sort(new int[]{i, j, k, l});
                        System.out.println("########## Sorted ###########");
                    }
                }
            }
        }
    }
}
