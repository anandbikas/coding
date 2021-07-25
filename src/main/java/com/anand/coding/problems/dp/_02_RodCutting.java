package com.anand.coding.problems.dp;

/**
 * There is a rod of n unit and price list per unit of the rod.
 *
 * 1. Find the maximum profit by cutting and selling the rod.
 *
 * Note: This can be solved by greedy approach as well by sorting on the basis of price per unit.
 *       But this does not always give correct answer.
 *       EG:
 *          price[] = {1,5,8,10} for n=4
 *          price/unit = 1,2.5,2.6,2.5
 *          Greedy answer = 9 (unit 3,1)
 *          DP answer = 10 (unit 2,2)
 */
public class _02_RodCutting {


    /**
     *
     * @param price
     * @return
     */
    public static int rodCutting(int price [], int n){

        int [] DP = new int[n+1];   //Length
        DP[1] = price[0];
        int result =  rodCutting(price, n, DP);

        for(int i=0; i<=n; i++){
            System.out.print(String.format("%10d", DP[i]));
        }
        System.out.println();

        return result;
    }

    private static int rodCutting(int price [], int n,  int DP[]) {

        if(n==0){
            return 0;
        }

        if(DP[n]>0){
            return DP[n];
        }

        int profit = price[n-1];
        for(int i=n-1; i>=n/2; i--){
            profit = Math.max(profit, rodCutting(price, i, DP) + rodCutting(price, n-i, DP));

        }
        return DP[n] = profit;
    }

    /**
     *
     * @param price
     * @param n
     * @return
     */
    public static int rodCuttingIterative(int price [], int n) {

        if(n==0){
            return 0;
        }

        int [] DP = new int[n+1];   //Length

        for(int k=1; k<=n; k++) {
            int profit = price[k-1];
            for(int i=k-1; i>=k/2; i--){
                profit = Math.max(profit, DP[i]+DP[k-i]);
            }
            DP[k]=profit;
        }

        for(int i=0; i<=n; i++){
            System.out.print(String.format("%10d", DP[i]));
        }
        System.out.println();
        return DP[n];
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        int price[] = new int[] {1, 5, 8, 9, 10, 17, 17, 20};
        System.out.println(rodCutting(price, price.length));

        System.out.println(rodCuttingIterative(price, price.length));

    }
}
