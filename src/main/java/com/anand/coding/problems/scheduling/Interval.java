package com.anand.coding.problems.scheduling;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * represents interval
 */
public class Interval {

    private final Time start;
    private final Time end;

    /**
     *
     * @param start
     * @param end
     */
    public Interval(String start, String end) {
        this.start = toTime(start);
        this.end = toTime(end);
    }

    /**
     *
     * @param timeFormatString
     * @return
     */
    private Time toTime(String timeFormatString) {
        Pattern p = Pattern.compile("(\\d?\\d):([0-5]\\d)");
        Matcher m = p.matcher(timeFormatString);
        if (!m.find()) {
            throw new IllegalArgumentException("invalid time format.");
        }
        int hour = Integer.parseInt(m.group(1));
        int minute = Integer.parseInt(m.group(2));
        return new Time(hour, minute);
    }

    /**
     *
     * @return
     */
    public Time getStart() {
        return this.start;
    }

    /**
     *
     * @return
     */
    public Time getEnd() {
        return this.end;
    }

    /**
     *
     * @return
     */
    public int getIntervalDuration() {
        return end.getTotalMinute() - start.getTotalMinute();
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("[%s - %s], %sm", start, end, getIntervalDuration());
    }
}