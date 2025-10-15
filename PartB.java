import java.sql.*;
import java.util.Scanner;
public class PartB {
    static String url = "jdbc:mysql://bytexldb.com:5051/db_43zqcp639";
    static String username = "user_43zqcp639";
    static String password = "p43zqcp639";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database successfully");
            int choice;
            do {
                System.out.println("\nPRODUCT MANAGEMENT");
                System.out.println("1. Insert Product");
                System.out.println("2. Display All Products");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        insertProduct(conn, sc);
                        break;
                    case 2:
                        displayProducts(conn);
                        break;
                    case 3:
                        updateProduct(conn, sc);
                        break;
                    case 4:
                        deleteProduct(conn, sc);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } while (choice != 5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            sc.close();
        }
    }
    private static void insertProduct(Connection conn, Scanner sc) {
        try {
            System.out.print("Enter Product ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Product Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Price: ");
            double price = sc.nextDouble();
            System.out.print("Enter Quantity: ");
            int qty = sc.nextInt();
            String sql = "INSERT INTO Product (ProductID, ProductName, Price, Quantity) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, qty);
            int rows = pstmt.executeUpdate();
            if (rows > 0)
                System.out.println("Product inserted successfully");
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void displayProducts(Connection conn) {
        try {
            String sql = "SELECT * FROM Product";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.printf("%-10s %-20s %-10s %-10s%n", "ID", "Name", "Price", "Qty");
            while (rs.next()) {
                System.out.printf("%-10d %-20s %-10.2f %-10d%n",
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("Price"),
                        rs.getInt("Quantity"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void updateProduct(Connection conn, Scanner sc) {
        try {
            conn.setAutoCommit(false);
            System.out.print("Enter Product ID to update: ");
            int id = sc.nextInt();
            System.out.print("Enter new Price: ");
            double newPrice = sc.nextDouble();
            System.out.print("Enter new Quantity: ");
            int newQty = sc.nextInt();
            String sql = "UPDATE Product SET Price = ?, Quantity = ? WHERE ProductID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, newPrice);
            pstmt.setInt(2, newQty);
            pstmt.setInt(3, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                conn.commit();
                System.out.println("Product updated successfully");
            } else {
                conn.rollback();
                System.out.println("Product not found");
            }
            pstmt.close();

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private static void deleteProduct(Connection conn, Scanner sc) {
        try {
            conn.setAutoCommit(false);
            System.out.print("Enter Product ID to delete: ");
            int id = sc.nextInt();
            String sql = "DELETE FROM Product WHERE ProductID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                conn.commit();
                System.out.println("Product deleted successfully");
            } else {
                conn.rollback();
                System.out.println("Product not found");
            }
            pstmt.close();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
