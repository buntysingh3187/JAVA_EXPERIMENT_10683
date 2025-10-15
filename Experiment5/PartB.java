import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
public class PartB {
    public static void main(String[] args){
        String filename = "byteData.txt";
        Student s1 = new Student(10, "Bunty Singh");
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(s1);
            System.out.println("Data added...");
            oos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            Student s2 = (Student) ois.readObject();
            System.out.println(s2.id);
            ois.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
class Student implements Serializable {
    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    void display() {
        System.out.println("ID: " + id + " | Name: " + name);
    }
}