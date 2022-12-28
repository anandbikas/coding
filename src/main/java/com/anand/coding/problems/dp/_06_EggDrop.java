package com.anand.coding.problems.dp;

/**
 *  Find minimum number of trials needed in worst case with n eggs and k floors
 *  to find the critical floor upto which the egg won't break on dropping.
 *
 * Example:
 * 1. 2 eggs, k floors:
 *
 *    Let the minimum trials be x with trials on floors: x, x+(x-1), x+(x-1)+(x-2) ...
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
 *  Ref: brilliant.org/wiki/egg-dropping/
 *       leetcode.com/problems/super-egg-drop/solutions/158936/super-egg-drop/
 */
public class _06_EggDrop {

    /**
     * For n=2 eggs, k floor.
     */
    public int twoEggDrop(int k) {
        return (int)Math.ceil((-1 + Math.sqrt(1+8*k)) / 2.0);
    }

    /**
     *  Recursive approach with memoization.
     */
    public static int eggDrop(int n, int k){

        int[][] DP = new int[n+1][k+1];   //Egg-Floor

        return eggDrop(n,k,DP);
    }

    private static int eggDrop(int n, int k, int [][]DP) {

        if (k == 1 || k == 0) return k; // 0 trials for no floor, 1 trials for one floor
        if (n == 1) return k;           // k trials for one egg and k floors

        if(DP[n][k]>1) {
            return DP[n][k];
        }

        //Approach: Binary search
        int minTrials = k;
        for(int low=1,high=k; low<high;) {
            int mid = low + (high-low)/2;

            //trails on the left side if breaks
            int left = eggDrop(n-1, mid-1, DP);     // one egg gone till now.

            //trails on the right side if it does not break
            int right = eggDrop(n, k-mid, DP);         // x(midFloor) floors gone till now.

            int trials = Math.max(left,right) + 1;

            if(trials<minTrials) {
                minTrials = trials;
            }

            if(left==right) {
                break;
            } else if(left<right) {
                low = mid+1;
            } else {
                high = mid;     // here, the midFloor also required as left side calculation had
                                // considered this floor lost/broke one egg.
                                // So next calculation will consider this floor with all n eggs remaining.
            }
        }

        return DP[n][k] = minTrials;
    }


    /**
     *  Iterative approach with tabulation.
     */
    public static int eggDropIterative(int n, int k) {

        if (k == 1 || k == 0) return k;
        if (n == 1) return k;

        int [][] DP = new int[n+1][k+1];    //Egg-Floor

        for(int i=0; i<=n; i++) {
            DP[i][0] = 0; // 0 trials for zero floor
            DP[i][1] = 1; // 1 trials for one floor
        }
        for(int j=1; j<=k; j++) {
            DP[1][j] = j; // k trials for one egg and k floors
        }

        for(int i=2; i<=n; i++) { //Calculate for 2 eggs onwards
            for(int j=2; j<=k; j++) { //Calculate for 2nd floors onwards

                //Approach: Binary search
                int minTrials = j;
                for(int low=1,high=j; low<high;) {

                    int mid = low + (high-low)/2;

                    //trails in left side if breaks
                    int left = DP[i-1][mid-1];                 // one egg gone till now.

                    //trails in right side if it does not break
                    int right = DP[i][j-mid];                  // x(midFloor) floors gone till now.

                    int trials = Math.max(left,right) + 1;

                    if(trials<minTrials) {
                        minTrials = trials;
                    }

                    if(left==right){
                        break;
                    } else if(left<right){
                        low = mid+1;
                    } else {
                        high = mid;     // here, the midFloor also required as left side calculation had
                                        // considered this floor lost/broke one egg.
                                        // So next calculation will consider this floor with all n eggs remaining.
                    }
                }
                DP[i][j] = minTrials;
            }
        }

        return DP[n][k];
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        int n = 2;

        for(int k=0; k<=33; k++) {
            System.out.println(k + " -> " + eggDrop(n, k) + ", " + eggDropIterative(n, k));
        }
    }
}