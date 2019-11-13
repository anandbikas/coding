package com.anand.coding.dsalgo.problems;

import com.anand.coding.dsalgo.array.Array;

/**
 * Given list of temperatures (range 30-100) for consecutive days.
 * For each day, find number of days ahead for a hotter day.
 * If not found for a particular day, it will be 0.
 *
 */
public class DailyTemperature {

    /**
     *
     * O(n) time, O(1) space
     * @param T
     * @return
     */
    public static int[] dailyTemperatures(int[] T) {

        // temperatureIndexMap[temperature] = closest index with greater temperature
        int[] temperatureIndexMap = new int[101];

        int[] res = new int[T.length];

        for (int i = T.length - 1; i >= 0; i--) {
            int curTemp = T[i];
            for (int t = curTemp-1; t >= 30; t--) {
                temperatureIndexMap[t] = i;
            }
            if (temperatureIndexMap[curTemp] > i) {
                res[i] = temperatureIndexMap[curTemp] - i;
            }
        }
        return res;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        int []T = {73, 74, 75, 71, 69, 72, 76, 73};

        Array.display(dailyTemperatures(T));
    }
}
