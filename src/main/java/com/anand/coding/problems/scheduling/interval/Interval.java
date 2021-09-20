package com.anand.coding.problems.scheduling.interval;

/**
 * represents interval
 */
public class Interval {

    Time start;
    Time end;
    int durationMinutes;

    public Interval(String start, String end) {
        this.start = new Time(start);
        this.end = new Time(end);
        durationMinutes = this.end.totalMinutes - this.start.totalMinutes;
    }

    @Override
    public String toString() {
        return String.format("[%s - %s], %sm", start, end, durationMinutes);
    }
}