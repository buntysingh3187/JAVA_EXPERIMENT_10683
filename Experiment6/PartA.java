import java.util.*;
public class PartA {
    static class Employee {
        int id;
        String name;
        double salary;
        Employee(int id, String name, double salary) {
            this.id = id;
            this.name = name;
            this.salary = salary;
        }
        @Override
        public String toString() {
            return id + " - " + name + " - " + salary;
        }
    }
    public static void sortEmployees() {
        List<Employee> employees = Arrays.asList(
                new Employee(101, "Aman", 45000),
                new Employee(103, "Riya", 52000),
                new Employee(102, "Karan", 48000)
        );
        System.out.println("=== Part A: Sorting Employees ===");
        System.out.println("Before Sorting:");
        employees.forEach(System.out::println);
        employees.sort(Comparator.comparingDouble(emp -> emp.salary));
        System.out.println("\nAfter Sorting by Salary:");
        employees.forEach(System.out::println);
        System.out.println();
    }
    public static void main(String[] args) {
        sortEmployees();
    }
}