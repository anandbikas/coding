package com.anand.coding.java.basics;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * 
 */
class Programmer extends Employee {

    private double incentive;
    private Date tdate;

    public Programmer() {
        super();
    }

    public Programmer(final String name, final double salary,
                      final int year, final int month, int day,
                      final int year1, final int month1, int day1) {
        super(name,salary,year,month,day);
        tdate = new GregorianCalendar(year1,month1-1,day1).getTime();
    }

    public void setIncentive(double rate, int hour) {
        this.incentive=rate*hour;
    }

    public double getIncentive() {
        return incentive;
    }

    @Override
    public double getSalary() {
        return super.getSalary() + incentive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Programmer that = (Programmer) o;
        return Double.compare(that.incentive, incentive) == 0 &&
                Objects.equals(tdate, that.tdate);
    }

    @Override
    public String toString() {
        return super.toString() + "\b" +
                ", Incentive = " + incentive +
                ", TDate = " + tdate + "]";
    }
}