package com.anand.coding.java.basics;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Employee class,
 * overloading of equals() and toString methods of Object Class
 * Comparable Interface
 * Cloneable Interface
 * Copy Constructor
 * Initialization Block
 * Static Initialization Block
 * (o instanceof Employee) operator
 */
public class Employee implements Comparable<Employee>, Cloneable {

    private String name;
    private double salary;
    private Date dateOfJoining = new Date();
    private int id;

    private static int nextId;

    //static initialization blocks
    static {
        Random random = new Random();
        nextId = random.nextInt(100000);
    }

    //initialization blocks
    {
        id = nextId++;
    }

    public Employee() {
    }

    public Employee (final Employee e){
        this(e.name, e.salary, e.dateOfJoining, e.id);
    }

    public Employee(final String name) {
        this.name=name;
    }

    public Employee(final String name, final double salary) {
        this(name);
        this.salary=salary;
    }

    public Employee(final String name, final double salary, final Date dateOfJoining) {
        this(name, salary);
        this.dateOfJoining=dateOfJoining;
    }

    public Employee(final String name, final double salary, final Date dateOfJoining, final int id) {
        this(name, salary, dateOfJoining);
        this.id=id;
    }

    public Employee(final String name, final double salary, final int year, final int month, final int day) {
        this(name, salary);
        dateOfJoining = new GregorianCalendar(year,month-1, day).getTime();
    }

    /**
     *
     * @param name
     */
    public void setName(final String name) {
        this.name=name;
    }

    /**
     *
     * @param s
     */
    public void  setSalary(final double s) {
        salary=s;
    }

    /**
     *
     * @param year
     * @param month
     * @param day
     */
    public void  setDateOfJoining(final int year, final int month, final int day) {
        this.dateOfJoining = new GregorianCalendar(year,month-1,day).getTime();
    }

    /**
     *
     * @param percent
     */
    public void incrementSalary(double percent) {
        salary += salary * percent / 100;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public static int getNextId() {
        return nextId;
    }

    /**
     *
     * @return
     */
    public double getSalary() {
        return salary;
    }

    /**
     *
     * @return
     */
    public Date getDateOfJoining() {
        return dateOfJoining;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        //if (o == null || !(o instanceof Employee)) return false;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return(this.name.equals(employee.name) &&
                this.salary == employee.salary &&
                this.id == employee.id &&
                this.dateOfJoining.equals(employee.dateOfJoining)
        );

    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [Id = " + id +
                ", Name = " + name +
                ", Salary = " + salary +
                ", DateOfJoining = " + dateOfJoining + "]";
    }

    /**
     * CompareTo based on salary
     * @param e
     * @return
     */
    @Override
    public int compareTo(Employee e) {
        return Double.compare(this.salary, e.salary);
    }

    @Override
    public Employee clone() {
        return new Employee(this);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
