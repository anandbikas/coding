package com.anand.coding.dsalgo.dp;

/**
 * There is a rod of n unit and price list per unit of the rod.
 *
 * 1. Find the maximum profit by cutting and selling the rod.
 */
public class RodCutting {


    /**
     *
     * @param price
     * @return
     */
    public static int rodCutting(int price [], int n){

        int [] DP = new int[n+1];

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

        int result=0;
        for(int i=1; i<=n; i++){
            result = Math.max(result, price[i-1] + rodCutting(price, n-i, DP));
        }
        return DP[n] = result;
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

        int [] DP = new int[n+1];

        for(int k=1; k<=n; k++) {

            int result = 0;
            for (int i = 1; i <= k; i++) {
                result = Math.max(result, price[i - 1] + DP[k-i]);
            }
            DP[k] = result;
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
