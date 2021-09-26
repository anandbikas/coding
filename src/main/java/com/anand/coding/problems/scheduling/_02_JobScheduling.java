package com.anand.coding.problems.scheduling;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

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
        for(int i=0; i<startTime.length; i++){
            schedules[i][0]=startTime[i];
            schedules[i][1]=endTime[i];
            schedules[i][2]=profit[i];
        }
        return getMaxEarning(schedules);
    }
    public static long getMaxEarning(int[][] schedules) {

        //sort in ascending order on end time
        Arrays.sort(schedules,Comparator.comparingInt(interval -> interval[1]));

        //Find maximum earning using DP
        long [] DP = new long [schedules.length+1];

        TreeMap<Integer, Integer> treeMap = new TreeMap<Integer,Integer>(){{put(-1,-1);}};
        for (int j=1; j<=schedules.length; j++) {
            int [] schedule = schedules[j-1];

            //Either use treemap or binary floorLastOccurrence method.
            int recentCompatibleEventIndex = treeMap.floorEntry(schedule[0]).getValue(); //floorLastOccurrence(schedules, 0,j-2, schedule[0]);
            DP[j] = Math.max(DP[j-1], DP[recentCompatibleEventIndex+1] + schedule[2]);
            treeMap.put(schedule[1],j);
        }

        return DP[schedules.length];
    }

    public static int floorLastOccurrence(int [][]A, int left, int right, int key) {
        if (key < A[left][1]) return -1;
        if (key >= A[right][1]) return right;

        while (left < right) {
            int mid = left + (right-left)/2 +(right-left)%2; //Ceil
            if (key >= A[mid][1]) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return (A[left][1] == key) ? left : right;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        int [] startTime = {1,2,3,3}, endTime = {3,4,5,6}, profit = {50,10,40,70};
        System.out.println(jobScheduling(startTime, endTime, profit));

    }
}
