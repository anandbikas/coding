package com.anand.coding.problems.scheduling;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Job Scheduling:
 * Schedule jobs (start,end,profit) to obtain max earning where end_of_previous may equal start_of_next.
 *
 *
 * Input: n = 5, schedules = [[2,5,4],[1,5,1]]
 * Output: 7
 * Explanation: Pick up schedule 0 to earn 4.
 *
 */
public class _02_JobScheduling {

    public static long jobScheduling(int[] startTime, int[] endTime, int[] profit) {

        int [][]schedules = new int [startTime.length][3];
        int maxEndTime=0;
        for(int i=0; i<startTime.length; i++){
            schedules[i][0]=startTime[i];
            schedules[i][1]=endTime[i];
            schedules[i][2]=profit[i];
            maxEndTime = Math.max(maxEndTime, endTime[i]);
        }
        return getMaxEarning(maxEndTime,schedules);
    }
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
                    Math.max(schedule[2] + profitArray[schedule[0]], currentEarning);
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

        int [] startTime = {1,2,3,3}, endTime = {3,4,5,6}, profit = {50,10,40,70};
        System.out.println(jobScheduling(startTime, endTime, profit));

    }
}
