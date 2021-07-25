package com.anand.coding.problems.dp;

import java.util.ArrayList;
import java.util.List;

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
     * Can be solved using Unbounded Knapsack where
     * wt[] = Item i has weight i.
     * val[] = price [];
     * knapsack weight = n (length)
     *
     * @param price
     * @param n
     * @return
     */
    public static int rodCuttingUnboundedKnapsack(int [] price, int n){

        //int []wt = Item i has weight i.
        int []val = price;
        int weight = n;

        if(n==0 || weight==0){
            return 0;
        }

        int [][] DP = new int[n+1][weight+1];   //Item-Weight

        //Populate DP from 1st element onwards
        for(int i=1; i<=n; i++){

            int itemIndex = i-1;
            int itemWeight = i;
            for(int w=1; w<=weight; w++){

                //Can't take i'th element upto now
                if(itemWeight>w) {
                    DP[i][w] = DP[i-1][w];
                }
                else{
                    DP[i][w] = Math.max( DP[i-1][w], val[itemIndex] + DP[i][w-itemWeight]);
                }
            }
        }

        // Print the selected items.
        List<Integer> itemWeights = new ArrayList<>();
        List<Integer> itemValues = new ArrayList<>();
        int w=weight;
        for(int i=n; i>0;){
            int itemWeight = i;
            if (DP[i][w]!=DP[i-1][w]) {
                itemWeights.add(itemWeight);
                itemValues.add(val[i-1]);
                w-=itemWeight;
            } else {
                i--;
            }

        }
        System.out.println("Piece Lengths : " + itemWeights + "\n" + "Piece Values : " + itemValues);
        return DP[n][weight];
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        int price[] = new int[] {1, 5, 8, 9, 10, 17, 17, 20};
        System.out.println(rodCutting(price, price.length));

        System.out.println(rodCuttingIterative(price, price.length));

        System.out.println(rodCuttingUnboundedKnapsack(price, price.length));

        int price1[] = new int[] {2,5,3,1};
        System.out.println(rodCuttingUnboundedKnapsack(price1, price1.length));
    }

    public static void printDPArray(int [][] DP, int n, int m)
    {
        System.out.println();
        for(int i=0; i<=n; i++){

            for(int j =0; j<=m; j++){
                System.out.print(String.format("%4d", DP[i][j]));
            }
            System.out.println();
        }
    }
}
