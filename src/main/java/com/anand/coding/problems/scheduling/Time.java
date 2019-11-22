package com.anand.coding.problems.scheduling;

/**
 * represents Time of Hour and Minute.
 */
public class Time {
    private final int hour;
    private final int minute;

    /**
     *
     * @param hour
     * @param minute
     */
    public Time(int hour, int minute)
    {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     *
     * @return
     */
    public int getHour() {
        return hour;
    }

    /**
     *
     * @return
     */
    public int getMinute() {
        return minute;
    }

    /**
     *
     **/
    public int getTotalMinute() {
        return hour * 60 + minute;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        return String.format("%02d:%02d", hour, minute);
    }

}