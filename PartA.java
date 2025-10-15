import java.sql.*;
public class PartA {
    public static void main(String[] args) {
        String url = "jdbc:mysql://bytexldb.com:5051/db_43zqcp639";
        String username = "user_43zqcp639";
        String password = "p43zqcp639";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected successfully!");
            stmt = conn.createStatement();
            String sql = "SELECT EmpID, Name, Salary FROM Employee";
            rs = stmt.executeQuery(sql);
            System.out.println("\nEmployee Details:");
            System.out.println("----------------------------");
            while (rs.next()) {
                int empId = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");

                System.out.println("EmpID: " + empId + " | Name: " + name + " | Salary: " + salary);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}
