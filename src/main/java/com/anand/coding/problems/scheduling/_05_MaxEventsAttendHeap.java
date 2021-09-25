package com.anand.coding.problems.scheduling;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Find maximum events attended from events[](start,end) where an event can be attended for at least one day.
 * Only one event can be attended at a time.
 *
 */
public class _05_MaxEventsAttendHeap {

    public static int maxEvents(int[][] events) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        //Sort events on start date.
        Arrays.sort(events, Comparator.comparingInt(event -> event[0]));
        int maxEndDate = Arrays.stream(events).mapToInt(event -> event[1]).max().orElse(0);

        int maxEventsAttended = 0; int eventIndex = 0;
        for (int day = 1; day <= maxEndDate; day++) {

            // Add all the events that can be attended today into heap
            for (; eventIndex < events.length && events[eventIndex][0] == day; eventIndex++) {
                pq.add(events[eventIndex][1]);
            }

            // Remove expired events
            while (!pq.isEmpty() && pq.peek() < day) {
                pq.remove();
            }

            // Attend one event today if any.
            if (!pq.isEmpty()) {
                maxEventsAttended++;
                pq.remove();
            }
        }
        return maxEventsAttended;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        int [][]A = {{1,2},{2,3},{3,4}};
        System.out.println(maxEvents(A));

        int [][]B = {{1,2},{2,3},{3,4},{1,2}};
        System.out.println(maxEvents(B));

        int [][]C = {{1,3},{1,3},{1,3},{3,4}};
        System.out.println(maxEvents(C));

        int [][]D = {{1,4},{4,4},{2,2},{3,4},{1,1}};
        System.out.println(maxEvents(D) + "= 4");

        int [][]E = {{1,5},{1,5},{1,5},{2,3},{2,3}};
        System.out.println(maxEvents(E) + "= 5");

        int [][]F = {{1,2},{2,3},{3,4},{2,2}};
        System.out.println(maxEvents(F) + "= 4");
    }
}
