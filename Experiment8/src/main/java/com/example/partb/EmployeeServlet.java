package com.example.partb;

import com.example.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "EmployeeServlet", urlPatterns = {"/partB/employees"})
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html><head><title>Employees</title></head><body>");
            out.println("<h2>Employees</h2>");
            out.println("<form method=\"get\" action=\"/partB/employees\">Search by ID: <input name=\"id\"/> <button type=\"submit\">Search</button> <a href=\"/partB/employees\">Show All</a></form>");

            try (Connection conn = DBUtil.getConnection(getServletContext())) {
                if (idParam != null && !idParam.trim().isEmpty()) {
                    int id = Integer.parseInt(idParam.trim());
                    try (PreparedStatement ps = conn.prepareStatement("SELECT EmpID, Name, Salary FROM Employee WHERE EmpID = ?")) {
                        ps.setInt(1, id);
                        try (ResultSet rs = ps.executeQuery()) {
                            if (rs.next()) {
                                out.println("<table border='1'><tr><th>EmpID</th><th>Name</th><th>Salary</th></tr>");
                                out.println("<tr><td>" + rs.getInt("EmpID") + "</td><td>" + rs.getString("Name") + "</td><td>" + rs.getDouble("Salary") + "</td></tr>");
                                out.println("</table>");
                            } else {
                                out.println("<p>No employee found with ID " + id + "</p>");
                            }
                        }
                    }
                } else {
                    try (PreparedStatement ps = conn.prepareStatement("SELECT EmpID, Name, Salary FROM Employee")) {
                        try (ResultSet rs = ps.executeQuery()) {
                            out.println("<table border='1'><tr><th>EmpID</th><th>Name</th><th>Salary</th></tr>");
                            while (rs.next()) {
                                out.println("<tr><td>" + rs.getInt("EmpID") + "</td><td>" + rs.getString("Name") + "</td><td>" + rs.getDouble("Salary") + "</td></tr>");
                            }
                            out.println("</table>");
                        }
                    }
                }
            } catch (SQLException e) {
                out.println("<p>Error: " + e.getMessage() + "</p>");
            }

            out.println("</body></html>");
        }
    }
}
