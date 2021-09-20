package com.anand.coding.problems.scheduling.interval;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * represents Time of Hour and Minute.
 */
public class Time {
    int hour;
    int minute;
    int totalMinutes;

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
        this.totalMinutes = hour * 60 + minute;
    }

    public Time(String timeString) {
        Pattern p = Pattern.compile("([0-1]?\\d||[2][0-4]):([0-5]\\d)");  //"19:59"
        Matcher m = p.matcher(timeString);
        if (!m.find()) {
            throw new IllegalArgumentException("invalid time format.");
        }

        this.hour = Integer.parseInt(m.group(1));
        this.minute = Integer.parseInt(m.group(2));
        this.totalMinutes = hour * 60 + minute;
    }

    public static void main(String[] args) {
        System.out.println(new Time("19:59"));
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, minute);
    }

}