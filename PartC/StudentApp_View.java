package PartC;
import java.util.*;
public class StudentApp_View {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentDAO_Controller dao = new StudentDAO_Controller();
        while (true) {
            System.out.println("\nSTUDENT MANAGEMENT");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            if (ch == 1) {
                System.out.print("Enter ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Department: ");
                String dept = sc.nextLine();
                System.out.print("Enter Marks: ");
                double marks = sc.nextDouble();
                Student_Model s = new Student_Model(id, name, dept, marks);
                if (dao.addStudent(s))
                    System.out.println("Student added");
                else
                    System.out.println("Failed to add");
            } else if (ch == 2) {
                List<Student_Model> list = dao.getAllStudents();
                System.out.printf("%-10s %-20s %-15s %-10s%n", "ID", "Name", "Department", "Marks");
                for (Student_Model s : list)
                    System.out.printf("%-10d %-20s %-15s %-10.2f%n", s.getStudentID(), s.getName(), s.getDepartment(), s.getMarks());
            } else if (ch == 3) {
                System.out.print("Enter ID to update: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter New Name: ");
                String name = sc.nextLine();
                System.out.print("Enter New Department: ");
                String dept = sc.nextLine();
                System.out.print("Enter New Marks: ");
                double marks = sc.nextDouble();
                Student_Model s = new Student_Model(id, name, dept, marks);
                if (dao.updateStudent(s))
                    System.out.println("Student updated");
                else
                    System.out.println("Update failed");
            } else if (ch == 4) {
                System.out.print("Enter ID to delete: ");
                int id = sc.nextInt();
                if (dao.deleteStudent(id))
                    System.out.println("Student deleted");
                else
                    System.out.println("Delete failed");
            } else if (ch == 5) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
        sc.close();
    }
}
