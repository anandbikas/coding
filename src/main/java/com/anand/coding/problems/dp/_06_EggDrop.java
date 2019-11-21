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
 *  Ref: https://brilliant.org/wiki/egg-dropping/
 */
public class _06_EggDrop {


    /**
     *  Recursive approach with memoization.
     *
     * @param n
     * @param k
     * @return
     */
    public static int eggDrop(int n, int k){

        int DP[][] = new int[n+1][k+1];   //Egg-Floor

        return eggDrop(n,k,DP);
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

//        // Approach 1:
//        int minTrials = Integer.MAX_VALUE;
//
//        // Consider all droppings from 1st floor to jth floor and find minimum trials.
//        for (int x = 1; x <= k; x++) {
//
//            //trails in left side if breaks
//            int left    = DP[n-1][x-1];                 //one egg and one floor gone till now.
//
//            //trails in right side if does not break
//            int right   = DP[n][k-x];               //x floors gone till now.
//
//            int trials = Math.max(left, right)+1;
//
//            if(trials<minTrials){
//                minTrials = trials;
//            }
//        }
//        return DP[n][k] = minTrials;


        //Approach 2: Binary search
        int low=1;
        int high = k;
        int minTrials = k;
        while(low < high) {
            int midFloor = low + (high-low)/2;

            //trails in left side if breaks
            int left = eggDrop(n-1, midFloor-1, DP);    //one egg and one floor gone till now.

            //trails in right side if does not break
            int right = eggDrop(n, k-midFloor, DP);         //x(midFloor) floors gone till now.

            int trials = Math.max(left,right) +1;

            if(trials<minTrials){
                minTrials = trials;
            }

            if(left==right){
                break;
            } else if(left<right){
                low = midFloor+1;
            } else {
                high = midFloor;    // here, the midFloor also required as left side calculation had
                // considered this floor lost/broke one egg.
                // So next calculation will consider this floor with all n eggs remaining.
            }
        }
        return DP[n][k] = minTrials;
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

        int [][] DP = new int[n+1][k+1];    //Egg-Floor

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

        //Calculate for 2 eggs onwards.
        for(int i=2; i<=n; i++) {
            //Calculate for 2nd floors onwards
            for(int j=2; j<=k; j++){

//                //Approach 1:
//                int minTrials = Integer.MAX_VALUE;
//
//                // Consider all droppings from 1st floor to jth floor and find minimum trials.
//                for (int x = 1; x <= j; x++) {
//
//                    //trails in left side if breaks
//                    int left    = DP[i-1][x-1];                     //one egg and one floor gone till now.
//
//                    //trails in right side if does not break
//                    int right   = DP[i][j-x];                       //x floors gone till now.
//
//                    int trials = Math.max(left, right) + 1;
//
//                    if(trials<minTrials){
//                        minTrials = trials;
//                    }
//                }
//                DP[i][j] = minTrials;
//

                //Approach 2: Binary search
                int low=1;
                int high = j;
                int minTrials = j;
                while(low < high) {
                    int midFloor = low + (high-low)/2;

                    //trails in left side if breaks
                    int left = DP[i-1][midFloor-1];                 //one egg and one floor gone till now.

                    //trails in right side if does not break
                    int right = DP[i][j-midFloor];                  //x(midFloor) floors gone till now.

                    int trials = Math.max(left,right) +1;

                    if(trials<minTrials){
                        minTrials = trials;
                    }

                    if(left==right){
                        break;
                    } else if(left<right){
                        low = midFloor+1;
                    } else {
                        high = midFloor;    // here, the midFloor also required as left side calculation had
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