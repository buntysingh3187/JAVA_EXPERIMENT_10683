<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head><meta charset="utf-8"/><title>Attendance - Part C</title></head>
<body>
    <h2>Student Attendance (Part C)</h2>
    <form method="post" action="/partC/attendance">
        <label>Student ID: <input type="text" name="studentId" required></label><br/>
        <label>Date: <input type="date" name="date" required></label><br/>
        <label>Status: 
            <select name="status">
                <option value="Present">Present</option>
                <option value="Absent">Absent</option>
                <option value="Late">Late</option>
            </select>
        </label><br/>
        <button type="submit">Submit Attendance</button>
    </form>
</body>
</html>
