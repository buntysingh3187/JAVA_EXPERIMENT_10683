package com.example.parta;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", urlPatterns = {"/partA/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (isValid(username, password)) {
                out.println("<html><head><title>Welcome</title></head><body>");
                out.println("<h2>Welcome, " + escapeHtml(username) + "!</h2>");
                out.println("<p>You have successfully logged in.</p>");
                out.println("<p><a href=\"/partA/login.html\">Log in as different user</a></p>");
                out.println("</body></html>");
            } else {
                out.println("<html><head><title>Login Failed</title></head><body>");
                out.println("<h3>Invalid username or password.</h3>");
                out.println("<p><a href=\"/partA/login.html\">Try again</a></p>");
                out.println("</body></html>");
            }
        }
    }

    private boolean isValid(String username, String password) {
        // Simple hardcoded validation for demo purposes
        if (username == null || password == null) return false;
        return (username.equals("admin") && password.equals("password")) || (username.equals("user") && password.equals("1234"));
    }

    private String escapeHtml(String s) {
        if (s == null) return "";
        return s.replaceAll("&","&amp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\"","&quot;");
    }
}
