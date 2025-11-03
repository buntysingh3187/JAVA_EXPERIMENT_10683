# Experiment8_JAVA - Servlets, JDBC, JSP examples (Parts A,B,C)

This project contains three parts demonstrating Servlet/JSP and JDBC integration.

How to run (requires Java 8+ and Maven):

1. From the project root (where pom.xml is) run:

```bash
mvn clean package jetty:run
```

2. Open a browser to http://localhost:8080/

Parts:
- Part A: User Login using Servlet and HTML form
  - URL: /partA/login.html (form posts to /partA/login)
  - Servlet: com.example.parta.LoginServlet

- Part B: Display Employee Records with JDBC and Servlet
  - URL: /partB/employees (shows all employees or search by id using ?id=)
  - Servlet: com.example.partb.EmployeeServlet

- Part C: Student Attendance Portal using JSP and Servlet
  - URL: /partC/attendance.jsp (form posts to /partC/attendance)
  - Servlet: com.example.partc.AttendanceServlet

Database:
- Uses SQLite file stored under `WEB-INF/db/app.db`. The DB and tables are created automatically on first access.

Notes:
- This is a minimal demo project. For production, use connection pooling, proper input validation and prepared statements with careful error handling.
