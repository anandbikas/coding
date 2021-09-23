package com.anand.coding.problems.scheduling.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * IntervalScheduling
 * Assume end_of_previous may equal start_of_next
 *
 */
public class IntervalScheduling {

    private static final int MINUTES_IN_TWENTY_FOUR_HOURS = 1440;

    List<Interval> intervals;

    public IntervalScheduling(){
        intervals = new ArrayList<>();
    }

    public IntervalScheduling(List<Interval> intervals){
        this.intervals = intervals;
    }

    public IntervalScheduling add(Interval interval){
        intervals.add(interval);
        return this;
    }

    /**
     *
     * @return
     */
    public int getMaxIntervalOverlapCount() {

        if(intervals==null || intervals.isEmpty()) {
            return 0;
        }

        //Create minute chart for all the minutes in 24 hours
        int [] minuteChart = new int[MINUTES_IN_TWENTY_FOUR_HOURS+1];

        for(Interval interval: intervals) {
            for(int minute=interval.start.totalMinutes; minute<interval.end.totalMinutes; minute++) {
                minuteChart[minute]++;
            }
        }
        return Arrays.stream(minuteChart).max().orElse(0);
    }

    /**
     * List of non-overlapping intervals for which the schedule gives maximum utilization of the resource.
     * The resource can be a teaching room, a meeting hall etc.
     *
     **
     * @return
     */
    public int getMaxWorkingTime() {
        if(intervals==null || intervals.isEmpty()){
            return 0;
        }

        //sort in ascending order on end time
        intervals.sort(Comparator.comparingInt(interval -> interval.end.totalMinutes));

        //Find maximum working using DP
        int [] DP = new int [intervals.size()+1];

        for (int j=1; j<=intervals.size(); j++) {
            Interval interval = intervals.get(j-1);

            int recentCompatibleIntervalIndex = floorLastOccurrence(intervals, 0,j-2, interval.start.totalMinutes);
            DP[j] = Math.max(DP[j-1], DP[recentCompatibleIntervalIndex+1] + interval.end.totalMinutes-interval.start.totalMinutes );
        }

        return DP[intervals.size()];
    }

    public static int floorLastOccurrence(List<Interval> intervals, int left, int right, int key) {
        if (key < intervals.get(left).end.totalMinutes) return -1;
        if (key >= intervals.get(right).end.totalMinutes) return right;

        while (left < right) {
            int mid = left + (right-left)/2 +(right-left)%2; //Ceil
            if (key >= intervals.get(mid).end.totalMinutes) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return (intervals.get(left).end.totalMinutes == key) ? left : right;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        final Interval interval = new Interval("2:30", "5:45");
        System.out.println(interval);

        IntervalScheduling intervalScheduling =
                new IntervalScheduling(
                        Arrays.asList(
                                new Interval("08:00", "12:00"),
                                new Interval("06:00", "09:00"),
                                new Interval("11:00", "13:30")));
        System.out.println(intervalScheduling.getMaxIntervalOverlapCount() + "= 2");
        System.out.println(intervalScheduling.getMaxWorkingTime() + "= 330");

        intervalScheduling = new IntervalScheduling()
                .add(new Interval("09:00", "12:30"))
                .add(new Interval("06:00", "09:30"))
                .add(new Interval("12:00", "14:30"))
                .add(new Interval("10:00", "10:30"))
                .add(new Interval("11:00", "13:30"));
        System.out.println(intervalScheduling.getMaxIntervalOverlapCount() + " = 3");
        System.out.println(intervalScheduling.getMaxWorkingTime() + "= 390");

        List<Interval> intervals = Arrays.asList(
                new Interval("06:00", "08:30"),
                new Interval("08:00", "09:00"),
                new Interval("09:00", "11:00"),
                new Interval("09:00", "11:30"),
                new Interval("12:30", "14:00"),
                new Interval("10:30", "14:00"));
        System.out.println(new IntervalScheduling(intervals).getMaxIntervalOverlapCount() + " = 3");
        System.out.println(new IntervalScheduling(intervals).getMaxWorkingTime() + " = 390");

        intervalScheduling =
                new IntervalScheduling(
                        Arrays.asList(
                               new Interval("06:00", "08:30"),
                                new Interval("08:30", "09:30"),
                                new Interval("10:00", "11:00")));

        System.out.println(intervalScheduling.getMaxIntervalOverlapCount() + " = 1");
        System.out.println(intervalScheduling.getMaxWorkingTime() + " = 270");

    }
}
