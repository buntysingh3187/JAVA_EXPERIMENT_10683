import java.util.Scanner;
import java.util.ArrayList;
public class PartA{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> list= new ArrayList<>();
        for (int i=0; i<3;i++) {
            System.out.println("Please enter the value of index " + i);
            String input =sc.nextLine();
            int num =Integer.parseInt(input); 
            list.add(num);
        }
        int sum =0;
        for (Integer ele : list) {
            sum+= ele;
        }
        System.out.println("Sum: "+ sum);
        sc.close();
    }
}