<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 10/09/2025
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Signup</title>
</head>
<body>
  <form action="signup" method="post">
    <h1>Signup</h1>

    <% if (request.getParameter("error") != null) { %>
        <p style="color:red;">Signup error: <%= request.getParameter("error") %></p>
    <% } %>

    <label>Username</label>
    <input type="text" name="username" required> <br>
    <label>Password</label>
    <input type="password" name="password" required> <br>
    <label>Role</label>
    <select name="role">
        <option value="CUSTOMER">Customer</option>
        <option value="ADMIN">Admin</option>
    </select><br>
    <input type="submit" value="Signup">
  </form>

  <p>Already have an account? <a href="Login.jsp">Login</a></p>
</body>
</html>
