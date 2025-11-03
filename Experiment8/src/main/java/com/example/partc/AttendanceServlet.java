package com.example.partc;

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
import java.sql.SQLException;

@WebServlet(name = "AttendanceServlet", urlPatterns = {"/partC/attendance"})
public class AttendanceServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentId = request.getParameter("studentId");
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (studentId == null || studentId.trim().isEmpty() || date == null || status == null) {
                out.println("<html><body><h3>Missing fields. <a href=\"/partC/attendance.jsp\">Back</a></h3></body></html>");
                return;
            }

            try (Connection conn = DBUtil.getConnection(getServletContext())) {
                try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Attendance (StudentID, Date, Status) VALUES (?,?,?)")) {
                    ps.setString(1, studentId);
                    ps.setString(2, date);
                    ps.setString(3, status);
                    ps.executeUpdate();
                }
                out.println("<html><head><title>Saved</title></head><body>");
                out.println("<h3>Attendance recorded for " + escapeHtml(studentId) + " on " + escapeHtml(date) + " as " + escapeHtml(status) + "</h3>");
                out.println("<p><a href=\"/partC/attendance.jsp\">Add another</a></p>");
                out.println("</body></html>");
            } catch (SQLException e) {
                out.println("<p>DB Error: " + e.getMessage() + "</p>");
            }
        }
    }

    private String escapeHtml(String s) {
        if (s == null) return "";
        return s.replaceAll("&","&amp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\"","&quot;");
    }
}
