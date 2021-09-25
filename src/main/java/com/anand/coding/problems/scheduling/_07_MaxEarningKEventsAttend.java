package com.anand.coding.problems.scheduling;


import java.util.Arrays;
import java.util.Comparator;

/**
 * Given n events (start,end,earning) find maximum earnings by attending at most k events.
 * Only one event can be attended at a time.
 *
 */
public class _07_MaxEarningKEventsAttend {

    public static int maxEarningKEvents(int[][] events, int k){

        //sort in ascending order on end time
        Arrays.sort(events,Comparator.comparingInt(event -> event[1]));

        //Find maximum earning using DP
        int [][] DP = new int [k+1][events.length+1]; // [max_event .. upto_event_index]

        for(int j=1; j<=events.length; j++) {
            DP[1][j]=Math.max(DP[1][j-1],events[j-1][2]);
        }

        for(int i=2; i<=k; i++) {
            for (int j=1; j<=events.length; j++) {
                int [] event = events[j-1];

                int recentCompatibleEventIndex = floorLastOccurrence(events, 0,j-2,event[0]-1);
                DP[i][j] = Math.max(DP[i][j-1],DP[i-1][recentCompatibleEventIndex+1] + event[2]);
            }
        }

        return DP[k][events.length];
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

        int [][]events = {{1,1,1},{2,2,2},{3,3,3},{4,4,4}};
        System.out.println(maxEarningKEvents(events, 3));

        int [][]events1 = {{1,2,4},{3,4,3},{2,3,1}};
        System.out.println(maxEarningKEvents(events1, 2));

        int [][]events2 = {{1,2,4},{3,4,3},{2,3,10}};
        System.out.println(maxEarningKEvents(events2, 2));

        int [][]events3 = {{1,3,4},{2,4,1},{1,1,4},{3,5,1},{2,5,5}};
        System.out.println(maxEarningKEvents(events3, 3));

        int [][]events4 = {{28,51,39},{18,55,21},{16,39,14},{13,31,34},{51,51,97},{35,93,25},{24,41,94},{9,33,73},{52,56,95},{77,81,82},{27,30,50}};
        System.out.println(maxEarningKEvents(events4, 2));

    }
}
