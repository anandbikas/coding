package com.anand.coding.problems.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value.
 * Fractional value supported.
 *
 */
public class _1_Knapsack_Fractional {

    /**
     *
     * @param items
     * @return
     */
    public static List<Item> knapsack(List<Item> items, float weight){

        //items.sort(Comparator.comparing(Item::getValuePerWeight));
        Collections.sort(items, Collections.reverseOrder());

        float resultedWeight = 0;
        List<Item> resultedItems = new ArrayList<>();

        for(Item item: items){

            if(resultedWeight + item.weight > weight) {
                if(resultedWeight < weight) {
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

        float []val = {10, 20, 30};
        float []wt =  { 2,  5,  6};
        float weight = 9;

        List<Item> items = new ArrayList<>();
        for(int i=0; i<wt.length; i++){
            items.add(new Item(val[i], wt[i]));
        }

        System.out.println(knapsack(items, weight));

    }

    /**
     *
     */
    public static class Item implements Comparable<Item> {
        float value;
        float weight;
        Float valuePerWeight;

        public Item(float value, float weight) {
            this.value = value;
            this.weight = weight;
            valuePerWeight = value/weight;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "value=" + value +
                    ", weight=" + weight +
                    ", valuePerWeight=" + valuePerWeight +
                    '}';
        }

        /**
         *
         * @param that
         * @return
         */
        @Override
        public int compareTo(Item that) {
            return this.valuePerWeight.compareTo(that.valuePerWeight);
        }
    }
}
