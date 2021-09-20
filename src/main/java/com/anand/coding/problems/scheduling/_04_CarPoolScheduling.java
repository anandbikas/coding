package com.anand.coding.problems.scheduling;


import java.util.Arrays;

/**
 * Ride Scheduling:
 * A car is running uni-directionally with a certain capacity. There are passengers on the way with (count,start,end)
 * Is it possible to pick up and drop off all passengers for all the given trips.
 *
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 5
 * Output: true
 *
 */
public class _04_CarPoolScheduling {

    public static boolean carPooling(int capacity, int[][] trips) {

        int maxEnd = Arrays.stream(trips).mapToInt(trip -> trip[2]).max().orElse(0);

        //Create dist chart for distance maxEnd
        int [] distChart = new int[maxEnd+1];

        for(int [] trip: trips) {
            for(int dist=trip[1]; dist<trip[2]; dist++) {
                distChart[dist]+=trip[0];
            }
        }
        return Arrays.stream(distChart).max().orElse(0)<=capacity;
    }


    /**
     *
     * @param args
     */
    public static void main(String [] args){

        int [][]A = {{2,1,5},{3,3,7}};
        System.out.println(carPooling(5, A));
    }
}
