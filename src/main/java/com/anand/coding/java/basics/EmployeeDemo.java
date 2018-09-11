package com.anand.coding.java.basics;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 */
public class EmployeeDemo {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Employee[] employees = new Employee[]{
                new Employee("Name 1", 35000, 1998, 8, 15),
                new Employee("Name 2", 38000, 1999, 7, 30),
                new Employee("Name 5", 17000, 1995, 8, 15),
                new Employee("Name 6", 17000, 1995, 8, 15)
        };

        Arrays.stream(employees).forEach(System.out::println);

        System.out.println("Increase salary[y/Y]?");
        String option = scanner.nextLine();

        while(option.equalsIgnoreCase("y")){

            System.out.println();
            System.out.println("Enter employee name: ");
            String name = scanner.nextLine();

            Employee employee = Arrays.stream(employees).filter(x -> x.getName().equals(name)).findFirst().orElse(null);
            if(null == employee){
                System.out.println("Employee not found");
            }else {
                System.out.println("Enter percent increment: ");
                double percent = scanner.nextDouble();
                scanner.nextLine();

                employee.incrementSalary(percent);
                System.out.println(employee);
            }

            System.out.println("Increase salary[y/Y]?");
            option = scanner.nextLine();

        }

        System.out.println("Employee records after increment:");

        Arrays.sort(employees);
        Arrays.stream(employees).forEach(System.out::println);
        System.out.println();

        System.out.println(Arrays.toString(employees));
        System.out.println();

        final Employee employee = employees[0].clone();
        System.out.println(employee);
        System.out.println(new Employee(employee));

        final Manager manager = new Manager("Name 0", 50000, 1980, 1, 1, 800);
        System.out.println(manager);
        System.out.println(manager.clone());

        System.out.println(employee.compareTo(manager));

        System.out.println(new Employee("No Name"));

        System.out.println("All employees name in sorted order");
        System.out.println(Arrays.stream(employees).map(Employee::getName).collect(Collectors.toSet()));


        List<Employee> staffs = Arrays.asList(
                new Manager("Manager", 35000, 1998, 8, 15, 4000),
                new Programmer("Programmer 1",38000,1999,7,30,2009,8,28),
                new Programmer("Programmer 2",37000,2000,8,28,2010,9,26),
                new Employee("Employee 1",15000,1988,7,27),
                new Employee("Employee 2",17000,1995,8,15)
        );

        for (Employee staff: staffs) {
            if (staff instanceof Manager) {
                System.out.println("Enter bonus for manager: " + staff.getName());
                ((Manager) staff).setBonus(scanner.nextDouble());

            } else if (staff instanceof Programmer) {
                System.out.println("Enter rate and work hours for programmer: " + staff.getName());
                ((Programmer) staff).setIncentive(scanner.nextInt(), scanner.nextInt());
            }
        }
        staffs.forEach(System.out::println);
    }
}