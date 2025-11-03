package com.example.util;

import jakarta.servlet.ServletContext;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    private static final String EMP_SQL = "CREATE TABLE IF NOT EXISTS Employee (EmpID INTEGER PRIMARY KEY, Name TEXT NOT NULL, Salary REAL)";
    private static final String ATT_SQL = "CREATE TABLE IF NOT EXISTS Attendance (id INTEGER PRIMARY KEY AUTOINCREMENT, StudentID TEXT NOT NULL, Date TEXT NOT NULL, Status TEXT NOT NULL)";

    public static Connection getConnection(ServletContext ctx) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQLite JDBC driver not found", e);
        }
        String dbDirPath = ctx.getRealPath("/WEB-INF/db");
        File dbDir = new File(dbDirPath);
        if (!dbDir.exists()) dbDir.mkdirs();
        String dbFile = new File(dbDir, "app.db").getAbsolutePath();
        String url = "jdbc:sqlite:" + dbFile;
        Connection conn = DriverManager.getConnection(url);
        initializeDatabase(conn);
        return conn;
    }

    private static void initializeDatabase(Connection conn) throws SQLException {
        try (Statement st = conn.createStatement()) {
            st.execute(EMP_SQL);
            st.execute(ATT_SQL);
            // insert some sample employees if table empty
            st.execute("INSERT OR IGNORE INTO Employee(EmpID, Name, Salary) VALUES (1,'Gourav Sharma',600000)");
            st.execute("INSERT OR IGNORE INTO Employee(EmpID, Name, Salary) VALUES (2,'Bunty Singh ',550000)");
            st.execute("INSERT OR IGNORE INTO Employee(EmpID, Name, Salary) VALUES (3,'Happy Sharma',55000)");
        }
    }
}
