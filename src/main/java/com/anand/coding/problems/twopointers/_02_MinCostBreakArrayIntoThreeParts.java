package com.anand.coding.problems.twopointers;

/**
 * Divide an array into three parts by breaking the array at two positions.
 * The array represents a chain and breaking it at a position destroys the element.
 * Find the minimum cost to break the chain.
 *
 * Ex:
 * A = {5,2,4,6,3,7}
 *
 * Break the array at A[1]=2 and A[5]=3, Total cost 2+3 = 5
 *
 */
public class _02_MinCostBreakArrayIntoThreeParts {

    public static int solution(int[] A) {

        int P=1; //Index P keeps changing to point to minimum A[P] value;
        int minSum = A[P]+A[3];

        for(int Q=4; Q<A.length-1; Q++){
            if(A[Q-2]<A[P]){
                P=Q-2;
            }
            minSum = Math.min(minSum, A[P]+A[Q]);
        }
        return minSum;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {

        int []A = {5,2,4,6,3,7};
        System.out.println(solution(A));
    }
}
