package com.anand.coding.java.basics;

import java.util.Date;

/**
 *
 */
public class Manager extends Employee {

    private double bonus = 500;

    public Manager() {
        super();
    }

    public Manager (final Manager m){
        this(m.getName(), m.getSalary(), m.getDateOfJoining(), m.getId(), m.bonus);
    }

    public Manager(final String name) {
        super(name);
    }

    public Manager(final String name, final double salary) {
        super(name, salary);
    }

    public Manager(final String name, final double salary, final Date dateOfJoining) {
        super(name, salary, dateOfJoining);
    }

    public Manager(final String name, final double salary, final Date dateOfJoining, final int id) {
        super(name, salary, dateOfJoining, id);
    }

    public Manager(final String name, final double salary, final Date dateOfJoining, final int id, final double bonus) {
        super(name, salary, dateOfJoining, id);
        this.bonus = bonus;
    }

    public Manager(final String name, final double salary, final int year, final int month, int day, int bonus) {
        super(name,salary,year,month,day);
        this.bonus = bonus;
    }

    public void setBonus(double b) {
        bonus=b;
    }

    public double getBonus() {
        return bonus;
    }

    @Override
    public double getSalary() {
        return (super.getSalary() + bonus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        if (!super.equals(o)) return false;
        Manager manager = (Manager) o;
        return Double.compare(manager.bonus, bonus) == 0;
    }

    @Override
    public String toString() {
        return super.toString() + "\b" +
                ", Bonus = " + bonus + "]";
    }

    @Override
    public Manager clone() {
        return new Manager(this);
    }
}