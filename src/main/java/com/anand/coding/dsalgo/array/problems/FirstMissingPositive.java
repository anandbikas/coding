package com.anand.coding.dsalgo.array.problems;

/**
 * Find the smallest missing positive integer from an array.
 */
public class FirstMissingPositive {

    /**
     * O(n) time, O(n) space
     *
     * @param A
     * @return
     */
    public static int firstMissingPositive(int A[]){

        int n = A.length;

        //Trick is smallest missing number can't be greater than n+1
        boolean[] H = new boolean[n+2];

        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0 && A[i] <=n+1) {
                H[A[i]] = true;
            }
        }

        for (int i=1; i <= n+1; i++) {
            if (!H[i]) {
                return i;
            }
        }
        return 1;
    }

    /**
     * O(n) time, O(1) space
     *
     * @param A
     * @return
     */
    public static int firstMissingPositiveO1Space(int A[]){

        int n = A.length;

        //Trick is smallest missing number can't be greater than n+1

        for(int i=0; i < n;){

            if(A[i] > 0 && A[i] <= n && A[A[i]-1] != A[i]) {
                int temp = A[A[i]-1];
                A[A[i]-1] = A[i];
                A[i] = temp;
            } else {
                i++;
            }
        }

        for(int i=0; i < n; i++){
            if(A[i] != i+1)
                return i+1;
        }
        return n+1;
    }


    /**
     *
     * @param args
     */
    public static void main(String [] args){

        System.out.println(firstMissingPositive(new int[]{1,2,5}));
        System.out.println(firstMissingPositiveO1Space(new int[]{1,2,5}));

    }
}
