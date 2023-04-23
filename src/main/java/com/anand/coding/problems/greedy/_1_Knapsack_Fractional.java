package com.anand.coding.problems.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value.
 * Fractional value supported.
 *
 */
public class _1_Knapsack_Fractional {

    /**
     *
     * @param wt
     * @param val
     * @param n
     * @param weight
     * @return
     */
    public static List<Item> knapsack(int[] wt, int[] val, int n, int weight) {

        List<Item> items = new ArrayList<>();
        for(int i=0; i<n; i++){
            items.add(new Item(val[i], wt[i]));
        }

        items.sort(Comparator.comparing(i -> i.value/i.weight, Comparator.reverseOrder()));

        int resultedWeight = 0;
        List<Item> resultedItems = new ArrayList<>();

        for(Item item: items){

            if(resultedWeight + item.weight > weight) {
                if(resultedWeight < weight) { //Fractional
                    resultedItems.add(new Item(item.value, weight - resultedWeight));
                    resultedWeight = weight;
                }
                break;
            }
            resultedItems.add(item);
            resultedWeight += item.weight;
        }

        return resultedItems;
    }


    /**
     *
     * @param args
     */
    public static void main(String [] args){

        int []val = {10, 20, 30};
        int []wt =  { 2,  5,  5};

        System.out.println(knapsack(wt, val,3, 9));

    }

    /**
     *
     */
    public static class Item  {
        int value,weight;

        public Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + value + "," + weight + ")";
        }
    }
}
