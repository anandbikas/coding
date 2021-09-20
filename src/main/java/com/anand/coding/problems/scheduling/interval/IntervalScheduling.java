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

        int [] durationArray = new int [MINUTES_IN_TWENTY_FOUR_HOURS+1];
        int currentDuration = 0;

        for(Interval interval: intervals) {
            for(int j=interval.end.totalMinutes; j>=0 && durationArray[j]==0; j--){
                durationArray[j]=currentDuration;
            }
            currentDuration = durationArray[interval.end.totalMinutes] =
                    Math.max(interval.end.totalMinutes-interval.start.totalMinutes + durationArray[interval.start.totalMinutes], currentDuration);
        }

        for(int j=MINUTES_IN_TWENTY_FOUR_HOURS; j>=0 && durationArray[j]==0;j--){
            durationArray[j]=currentDuration;
        }

        return durationArray[MINUTES_IN_TWENTY_FOUR_HOURS];
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
