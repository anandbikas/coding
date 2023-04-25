package com.anand.coding.problems.dp;

import java.util.Arrays;

/**
 * Example: Given 3 numbers {1, 3, 5}, find total number of ways to form 'N'
 * using the sum of the given three numbers(repetitions and different arrangements allowed).
 *
 * State:
 *      solve(n) is a state and tells total number of ways to form n using the elements in the array.
 *
 * State Transition:
 *      solve (n) = solve(n-A[0]) + solve(n-A[1]) + .....
 *                   where: solve(0) = 1;
 *                          solve(<0) = 0;
 *
 * state results:
 * -------------------------------
 * state(1) = [1]
 * state(2) = [1, 1]
 * state(3) = [1, 1, 1],
 *            [3]
 *
 * state(4) = [1,1,1,1],
 *            [1, 3],
 *            [3, 1]
 * --------------------------------
 *
 * Recursion Tree solve(4)
 * -------------------------------
 *                  s(4)
 *                /     \
 *             s(3)     s(1)
 *            /   \        \
 *         s(2)   s(0)     s(0)
 *         /
 *       s(1)
 *       /
 *     s(0)
 * --------------------------------
 *
 * Note: If different arrangements not allowed, see CoinChange problem.
 *
 * /problems/combination-sum-iv/
 */
public class _03_DifferentWaysToFindASum {


    /**
     *
     * @param A
     * @param sum
     * @return
     */
    public static long solveRec(int []A, int sum) {

        long []DP = new long[sum+1];    //Sum
        DP[0] = 1;

        long result = solveRec(A, sum, DP);
        System.out.println(Arrays.toString(DP));
        return result;
    }
    private static long solveRec(int []A, int sum, long []DP) {

        if (sum < 0)   return 0;
        if (DP[sum]>0) return DP[sum];

        for (int x : A) {
            DP[sum] += solveRec(A, sum - x, DP);
        }

        return DP[sum];
    }

    /**
     *
     * @param A
     * @param sum
     * @return
     */
    public static long solveIterative(int []A, int sum) {
        long []DP = new long[sum+1];    //Sum
        DP[0]=1;

        for(int i=1; i<=sum; i++) {
            for (int x : A) {
                if(x<=i) {
                    DP[i] += DP[i-x];
                }
            }
        }

        System.out.println(Arrays.toString(DP));
        return DP[sum];
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){
        int []A = {1,3,5};

        System.out.println(solveRec(A, 8));
        System.out.println(solveIterative(A, 8));
    }
}
