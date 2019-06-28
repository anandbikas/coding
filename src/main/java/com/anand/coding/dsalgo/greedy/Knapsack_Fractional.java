package com.anand.coding.dsalgo.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value.
 * Fractional value supported.
 *
 */
public class Knapsack_Fractional {

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

            if(resultedWeight + item.getWeight() > weight) {
                if(resultedWeight < weight) {
                    resultedItems.add(new Item(item.getWeight(), weight - resultedWeight));
                    resultedWeight = weight;
                }
                break;
            }
            resultedItems.add(item);
            resultedWeight += item.getWeight();
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

        System.out.println(knapsack(Item.getList(wt, val), weight));

    }

    /**
     *
     */
    public static class Item implements Comparable<Item> {
        private float value;
        private float weight;
        private Float valuePerWeight;

        public Item(float value, float weight) {
            this.value = value;
            this.weight = weight;
            valuePerWeight = value/weight;
        }

        /**
         *
         * @return
         */
        public float getValue() {
            return value;
        }

        /**
         *
         * @param value
         */
        public void setValue(float value) {
            this.value = value;
        }

        /**
         *
         * @return
         */
        public float getWeight() {
            return weight;
        }

        /**
         *
         * @param weight
         */
        public void setWeight(float weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "value=" + value +
                    ", weight=" + weight +
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

        /**
         *
         * @param wt
         * @param val
         * @return
         */
        public static List<Item> getList(float [] wt, float [] val){

            List<Item> items = new ArrayList<>();
            for(int i=0; i<wt.length; i++){
                items.add(new Item(val[i], wt[i]));
            }

            return items;
        }
    }
}
