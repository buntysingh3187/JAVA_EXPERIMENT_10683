import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
public class PartB {
    static class Student {
        int id;
        String name;
        double marks;
        Student(int id, String name, double marks) {
            this.id = id;
            this.name = name;
            this.marks = marks;
        }
        @Override
        public String toString() {
            return id + " - " + name + " - " + marks;
        }
    }
    public static void filterAndSortStudents() {
        List<Student> students = Arrays.asList(
                new Student(1, "Aman", 85.5),
                new Student(2, "Riya", 92.0),
                new Student(3, "Karan", 67.5),
                new Student(4, "Simran", 76.3)
        );
        System.out.println("Students with marks > 75 (Sorted by Marks):");
        students.stream()
                .filter(s -> s.marks > 75)
                .sorted(Comparator.comparingDouble(s -> s.marks))
                .forEach(System.out::println);
        System.out.println();
    }
 public static void main(String[] args) {
        filterAndSortStudents();
    }
}
