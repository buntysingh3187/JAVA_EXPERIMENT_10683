import java.io.*;
import java.util.Scanner;
public class PartC{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("\n--- Employee Management Menu ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    addEmployee();
                    break;
                case 2:
                    display();
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    static void addEmployee(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter the Name: ");
        String name = sc.nextLine();
        System.out.print("Enter the Designation: ");
        String designation = sc.nextLine();
        System.out.print("Enter the Salary: ");
        double salary = sc.nextDouble();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Employee.txt", true))) {
            writer.write("ID: " + id + ", Name: " + name + ", Designation: " + designation + ", Salary: " + salary);
            writer.newLine();
            System.out.println("Employee record added successfully!");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();
    }
    static void display(){
        try (BufferedReader reader = new BufferedReader(new FileReader("Employee.txt"))) {
            String line;
            System.out.println("\n--- Employee Records ---");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
