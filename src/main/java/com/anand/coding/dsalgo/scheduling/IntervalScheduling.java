package com.anand.coding.dsalgo.scheduling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * IntervalScheduling
 */
public class IntervalScheduling {

    List<Interval> intervals;

    /**
     *
     */
    public IntervalScheduling(){
        intervals = new ArrayList<>();
    }

    /**
     *
     * @param intervals
     */
    public IntervalScheduling(List<Interval> intervals){
        this.intervals = intervals;
    }

    /**
     *
     * @param interval
     */
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
        int [] minuteChart = new int[1441];

        for(Interval interval: intervals) {
            for(int minute=interval.getStart().getTotalMinute(); minute<=interval.getEnd().getTotalMinute(); minute++) {
                minuteChart[minute]++;
            }
        }
        int maxOverlaps = minuteChart[0];
        for(int i=1; i<1441; i++) {
            if (minuteChart[i] > maxOverlaps) {
                maxOverlaps = minuteChart[i];
            }
        }
        return maxOverlaps;
    }

    /**
     * List of non-overlapping intervals for which the schedule gives maximum utilization of the resource.
     * The resource can be a teaching room, a meeting hall etc.
     **
     * @return
     */
    public int getMaxWorkingTime() {
        if(intervals==null || intervals.isEmpty()){
            return 0;
        }

        //sort in ascending order on end time
        //intervals.sort((a, b) -> Integer.compare(a.getEnd().getTotalMinute(), b.getEnd().getTotalMinute()));
        intervals.sort(Comparator.comparingInt(interval -> interval.getEnd().getTotalMinute()));

        //Find maximum working
        int [] workTimeArray = new int [intervals.size()];
        workTimeArray[0] = intervals.get(0).getIntervalDuration();

        for(int j=1; j<intervals.size(); j++) {

            workTimeArray[j]=workTimeArray[j-1];

            // Find k = largest index in workTimeArray where index < j
            // such that interval k is compatible (not overlapping) with interval j
            for(int k=j-1; k>=0; k--) {
                if (intervals.get(k).getEnd().getTotalMinute()
                        < intervals.get(j).getStart().getTotalMinute()){
                    workTimeArray[j] = Math.max(intervals.get(j).getIntervalDuration() + workTimeArray[k], workTimeArray[j-1]);
                    break;
                }
            }
        }

        return workTimeArray[intervals.size()-1];
    }

    /**
     *
     * @return
     */
    public int getMaxWorkingTime1()
    {
        if(intervals==null || intervals.isEmpty()) {
            return 0;
        }

        int maxWorkingTime=0;
        for(int i=0; i<intervals.size();i++) {

            int workingTime= intervals.get(i).getIntervalDuration();
            int start=intervals.get(i).getStart().getTotalMinute();
            int end = intervals.get(i).getEnd().getTotalMinute();

            for(int j=0; j<intervals.size(); j++) {

                if(intervals.get(j).getStart().getTotalMinute() > end) {
                    workingTime += intervals.get(j).getIntervalDuration();
                    end = intervals.get(j).getEnd().getTotalMinute();

                } else if(intervals.get(j).getEnd().getTotalMinute() < start) {
                    workingTime += intervals.get(j).getIntervalDuration();
                    start = intervals.get(j).getStart().getTotalMinute();
                }
            }
            if(workingTime>maxWorkingTime)
                maxWorkingTime=workingTime;
        }
        return maxWorkingTime;
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

        assert intervalScheduling.getMaxIntervalOverlapCount() == 2;
        assert intervalScheduling.getMaxWorkingTime()==330;

        intervalScheduling = new IntervalScheduling()
                .add(new Interval("09:00", "12:30"))
                .add(new Interval("06:00", "09:30"))
                .add(new Interval("12:00", "14:30"))
                .add(new Interval("10:00", "10:30"))
                .add(new Interval("11:00", "13:30"));

        assert intervalScheduling.getMaxIntervalOverlapCount()==3;
        assert intervalScheduling.getMaxWorkingTime()==390;

        List<Interval> intervals = Arrays.asList(
                new Interval("06:00", "08:30"),
                new Interval("08:00", "09:00"),
                new Interval("09:00", "11:00"),
                new Interval("09:00", "11:30"),
                new Interval("12:30", "14:00"),
                new Interval("10:30", "14:00"));

        assert new IntervalScheduling(intervals).getMaxIntervalOverlapCount()==3;
        assert new IntervalScheduling(intervals).getMaxWorkingTime() == 390;

        intervalScheduling =
                new IntervalScheduling(
                        Arrays.asList(
                               new Interval("06:00", "08:30"),
                                new Interval("09:00", "09:30"),
                                new Interval("10:00", "11:00")));

        assert intervalScheduling.getMaxIntervalOverlapCount()==1;
        assert intervalScheduling.getMaxWorkingTime()==240;

    }
}
