import java.util.Arrays;
import java.util.List;
public class PartC {
    static class Product {
        int id;
        String name;
        double price;
        Product(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }
        @Override
        public String toString() {
            return id + " - " + name + " - " + price;
        }
    }
    public static void productStreamOperations() {
        List<Product> products = Arrays.asList(
                new Product(1, "Laptop", 55000),
                new Product(2, "Mouse", 800),
                new Product(3, "Keyboard", 1500),
                new Product(4, "Monitor", 12000)
        );
        System.out.println("Filtered Products (Price > 1000):");
        products.stream()
                .filter(p -> p.price > 1000)
                .forEach(System.out::println);
        System.out.println("\nProduct Names:");
        products.stream()
                .map(p -> p.name)
                .forEach(System.out::println);
        double avgPrice = products.stream()
                .mapToDouble(p -> p.price)
                .average()
                .orElse(0.0);
        System.out.println("\nAverage Product Price: " + avgPrice);
        System.out.println();
    }
 public static void main(String[] args) {
        productStreamOperations();
    }
}