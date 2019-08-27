package com.anand.coding.dsalgo.dp;

/**
 *  Find minimum number of trials needed in worst case with n eggs and k floors
 *  to find the critical floor upto which the egg won't break on dropping.
 *
 * Example:
 * 1. 2 eggs, k floors:
 *
 *    Let the minimum trials be x with trials: x, x+(x-1), x+(x-1)+(x-2) ...
 *    Then total coverage,
 *          x + (x-1) + (x-2) + ...  + 2 + 1 = x(x+1)/2
 *
 *    Now for k floors:
 *             x(x+1)/2 >= k
 *          => x2 +x - 2k = 0
 *          => x = (-1 + √1+8k) / 2
 *
 *    for k=100, x = 13.65 = 14 (integer)
 *
 *
 * 2. For n eggs, k floors:
 *    -> Recursive approach (with memoization) to find all possible combinations and take minimum among them
 *
 *    -> Iterative tabulation approach.
 *
 *  Ref: https://brilliant.org/wiki/egg-dropping/
 */
public class EggDrop {


    /**
     *  Recursive approach with memoization.
     *
     * @param n
     * @param k
     * @return
     */
    public static int eggDrop(int n, int k){
        return eggDrop(n,k,new int[n+1][k+1]);
    }

    private static int eggDrop(int n, int k, int [][]DP) {

        // 0 trials for no floor
        // 1 trials for one floor.
        if (k == 1 || k == 0) {
            return k;
        }

        // k trials for one egg and k floors
        if (n == 1) {
            return k;
        }

        if(DP[n][k]>1){
            return DP[n][k];
        }

        int min = Integer.MAX_VALUE;

        // Consider all droppings from 1st floor to kth floor and
        // find the minimum of these values plus 1.
        for (int x = 1; x <= k; x++) {
            min = Math.min(
                    Math.max(eggDrop(n-1, x-1, DP), eggDrop(n, k-x, DP)),
                    min
                );
        }
        return DP[n][k] = min + 1;
    }


    /**
     *  Iterative approach with tabulation.
     *
     * @param n
     * @param k
     * @return
     */
    public static int eggDropIterative(int n, int k){

        if (k == 1 || k == 0) {
            return k;
        }

        if (n == 1) {
            return k;
        }

        int [][] DP = new int[n+1][k+1];

        // 0 trials for no floor
        // 1 trials for one floor.
        for(int i=0; i<=n; i++){
            DP[i][0] = 0;
            DP[i][1] = 1;
        }

        // k trials for one egg and k floors
        for(int j=1; j<=k; j++){
            DP[1][j] = j;
        }

        for(int i=2; i<=n; i++) {
            for(int j=2; j<=k; j++){
                int min = Integer.MAX_VALUE;

                // Consider all droppings from 1st floor to jth floor and
                // find the minimum of these values plus 1.
                for (int x = 1; x <= j; x++) {
                    min = Math.min(
                            Math.max(DP[i-1][x-1], DP[i][j-x]),
                            min
                    );
                }
                DP[i][j] = min + 1;
            }
        }

        return DP[n][k];
    }

    /**
     *
     * @param args
     */
    public static void main(String args[])
    {
        int n = 2;
        // int k = 10;

        for(int k=0; k<33; k++) {
            System.out.println(k + " -> " + eggDrop(n, k));
            System.out.println(k + " -> " + eggDropIterative(n, k));
            System.out.println();
        }
    }
}