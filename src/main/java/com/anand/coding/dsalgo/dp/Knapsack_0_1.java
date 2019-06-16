package com.anand.coding.dsalgo.dp;

/**
 * Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value
 *
 */
public class Knapsack_0_1 {

    /**
     * Recursive solution with memoization
     *
     * @param wt
     * @param val
     * @param n
     * @param weight
     * @return
     */
    public static int knapsackRec(int [] wt, int [] val, int n, int weight) {

        int [][] DP =  new int[n][weight+1];
        int finalResult = knapsackRec(wt, val, n, weight, DP);
        printDPArray(DP, n, weight);

        return finalResult;
    }
    private static int knapsackRec(int [] wt, int [] val, int n, int weight, int [][] DP){

        if(n<=0 || weight<=0){
            return 0;
        }

        int index = n-1;

        if(DP[index][weight] >0){
            return DP[index][weight];
        }

        if(wt[index]>weight){
            return DP[index][weight] = knapsackRec(wt, val, n-1, weight, DP);
        }

        return DP[index][weight] = Math.max(val[index] + knapsackRec(wt, val, n-1, weight-wt[index], DP),
                                knapsackRec(wt, val, n-1, weight, DP));
    }

    /**
     * DP Tabulation solution
     *
     * @param wt
     * @param val
     * @param n
     * @param weight
     */
    public static int knapsack(int [] wt, int [] val, int n, int weight){

        if(n==0 || weight==0){
            return 0;
        }

        int [][] DP = new int[n+1][weight+1];

        //Populate DP for first element
        if(wt[0] <= weight) {
            for (int w = wt[0]; w<=weight; w++){
                DP[0][w] = val[0];
            }
        }

        //Populate DP from 2nd element onwards
        for(int i=1; i<n; i++){

            for(int w=1; w<=weight; w++){

                //Can't take i'th element upto now
                if(wt[i]>w) {
                    DP[i][w] = DP[i-1][w];
                }
                else{
                    DP[i][w] = Math.max(val[i] + DP[i-1][w-wt[i]], DP[i-1][w]);
                }
            }
        }
        printDPArray(DP, n, weight);
        return DP[n-1][weight];
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        // Example 1:
        int []val1 = {10, 20, 30};
        int []wt1 =  { 2,  5,  6};
        int weight1 = 9;

        /* Recursive Solution DP Array:
         *
         *    0   0   0  10  10   0   0   0   0  10
         *    0   0   0  10   0   0   0   0   0  30
         *    0   0   0   0   0   0   0   0   0  40
         */
        System.out.println(knapsackRec(wt1, val1, wt1.length, weight1));

        /* Tapulation Solution DP Array:
         *
         *    0   0  10  10  10  10  10  10  10  10
         *    0   0  10  10  10  20  20  30  30  30
         *    0   0  10  10  10  20  30  30  40  40
         */
        System.out.println(knapsack(wt1, val1, wt1.length, weight1));


        // Example 2:
        int []val2 = {10, 20, 30, 60, 30, 10, 50, 100, 500, 30, 20};
        int []wt2 =  {2,   5,  6,  3,  1,  9,  8,  3,   5,   6,  3};
        int weight2 = 9;

        System.out.println(knapsackRec(wt2, val2, wt2.length, weight2));
        System.out.println(knapsack(wt2, val2, wt2.length, weight2));


        // Example 3:
        int []val = {60, 100, 120};
        int []wt =  {10,  20,  30};
        int weight = 50;

        System.out.println(knapsackRec(wt, val, wt.length, weight));
        System.out.println(knapsack(wt, val, wt.length, weight));
    }

    /**
     *
     * @param DP
     * @param n
     * @param weight
     */
    public static void printDPArray(int [][] DP, int n, int weight)
    {
        System.out.println();
        for(int i=0; i<n; i++){

            for(int w =0; w<=weight; w++){
                System.out.print(String.format("%4d", DP[i][w]));
            }
            System.out.println();
        }
    }
}
