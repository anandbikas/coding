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

    public static long getMaxEarning(int n, int[][] rides) {

        //sort in ascending order on end time
        Arrays.sort(rides,Comparator.comparingInt(ride -> ride[1]));

        //Find maximum earning using DP
        long [] DP = new long [rides.length+1];

        for (int j=1; j<=rides.length; j++) {
            int [] ride = rides[j-1];

            int recentCompatibleEventIndex = floorLastOccurrence(rides, 0,j-2, ride[0]);
            DP[j] = Math.max(DP[j-1], DP[recentCompatibleEventIndex+1] + ride[1]-ride[0]+ride[2]);
        }

        return DP[rides.length];
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

        int [][]A = {{2,5,4},{1,5,1}};
        System.out.println(getMaxEarning(5, A));

        int [][]B = {{2,3,6},{8,9,8},{5,9,7},{8,9,1},{2,9,2},{9,10,6},{7,10,10},{6,7,9},{4,9,7},{2,3,1}};
        System.out.println(getMaxEarning(10, B));

        int [][]C = {{1,6,1},{3,10,2},{10,12,3},{11,12,2},{12,15,2},{13,18,1}};
        System.out.println(getMaxEarning(20, C));

        int [][]D = {{7,9,10},{3,7,7},{3,4,4},{5,8,10},{5,7,6}};
        System.out.println(getMaxEarning(10, D));

        int [][]E = {{5,7,2},{7,9,10},{3,7,7},{3,4,4},{4,5,2},{5,8,10},{2,6,2},{5,7,6}};
        System.out.println(getMaxEarning(10, E));
    }
}
