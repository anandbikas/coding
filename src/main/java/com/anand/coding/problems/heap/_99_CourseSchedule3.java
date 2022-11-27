package com.anand.coding.problems.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Find maximum number of courses taken from courses[](duration_days,lastDay)
 * Only one course can be taken at a time.
 */
public class _99_CourseSchedule3 {

    public static int scheduleCourse(int[][] courses) {

        // Greedy, sort the courses by deadlines.
        Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));

        // Max PriorityQueue to store course duration, the highest duration is the lowest priority.
        PriorityQueue<Integer> pq= new PriorityQueue<>((a,b) -> b-a);

        int days=0;
        for (int[] c : courses) {

            // Add current course to the priority queue
            pq.add(c[0]);
            days += c[0];

            // If time exceeds, remove previous course which costs the most time.
            if (days > c[1]) {
                days -= pq.remove();
            }
        }
        return pq.size();
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        int [][]A = {{5,5},{4,6},{2,6}};
        System.out.println(scheduleCourse(A));
    }
}
