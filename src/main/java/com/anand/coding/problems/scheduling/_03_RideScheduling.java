package com.anand.coding.problems.scheduling;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Ride Scheduling:
 * A car is running uni-directionally for n unit distance and there are passengers on the way with (start,end,tip)
 * which earns end-start+tip.
 * Pick up the passengers to obtain max profit. Onboard one passenger at a time where end_of_previous may equal start_of_next.
 *
 *
 * Input: n = 5, schedules = [[2,5,4],[1,5,1]]
 * Output: 7
 * Explanation: Pick up schedule 0 to earn 5 - 2 + 4 = 7.
 *
 */
public class _03_RideScheduling {

    public static long getMaxEarning(int n, int[][] schedules) {

        //sort in ascending order on end time
        Arrays.sort(schedules,Comparator.comparingInt(interval -> interval[1]));

        //Find maximum earning using DP
        long [] profitArray = new long [n+1];
        long currentEarning = 0;

        for(int [] schedule: schedules){

            for(int j=schedule[1]; j>=0 && profitArray[j]==0; j--){
                profitArray[j]=currentEarning;
            }
            currentEarning = profitArray[schedule[1]] =
                    Math.max(schedule[1]-schedule[0]+schedule[2] + profitArray[schedule[0]], currentEarning);
        }

        for(int j=n; j>=0 && profitArray[j]==0;j--){
            profitArray[j]=currentEarning;
        }

        return profitArray[n];
    }


    /**
     *
     * @param args
     */
    public static void main(String [] args){

        int [][]A = {{2,5,4},{1,5,1}};
        System.out.println(getMaxEarning(5, A));

        int [][]B = {{2,3,6},{8,9,8},{5,9,7},{8,9,1},{2,9,2},{9,10,6},{7,10,10},{6,7,9},{4,9,7},{2,3,1}};
        System.out.println(getMaxEarning(10, B));

        int [][]C = {{1,6,1},{3,10,2},{10,12,3},{11,12,2},{12,15,2},{13,18,1}};
        System.out.println(getMaxEarning(20, C));
    }
}
