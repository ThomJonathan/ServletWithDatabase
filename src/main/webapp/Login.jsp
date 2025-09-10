<%--
  Created by IntelliJ IDEA.
  User: HART DOVIKO
  Date: 9/10/2025
  Time: 1:31 PM
  To change this template use File | Settings | File Templates.
--%><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 10/09/2025
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        body { font-family: Arial, sans-serif; background:#0f172a; color:#e5e7eb; display:flex; justify-content:center; align-items:center; height:100vh; margin:0; }
        .login-box { background:#111827; padding:30px; border-radius:12px; box-shadow:0 5px 20px rgba(0,0,0,.3); width:300px; }
        .login-box h2 { text-align:center; margin-bottom:20px; color:#22d3ee; }
        input[type=text], input[type=password] { width:100%; padding:10px; margin:8px 0; border:1px solid #374151; border-radius:8px; background:#1f2937; color:#fff; }
        input[type=submit] { width:100%; background:#22d3ee; color:#001; padding:10px; border:none; border-radius:8px; font-weight:bold; cursor:pointer; }
        input[type=submit]:hover { filter:brightness(0.95); }
    </style>
</head>
<body>
<% if (request.getParameter("error") != null) { %>
<p style="color:red;">Login failed</p>
<% } %>

<div class='login-box'>
<form action="login" method="post" class="log_form">
    <h1>Login</h1>
    <label>Username</label>
    <input type="text" name="username" required> <br>
    <label>Password</label>
    <input type="password" name="password" required>
    <br>
    <input type="submit" value="Login">
</form>

<p>No account? <a href="Signup.jsp" style="color: #22d3ee">Signup</a></p>
</div>

</body>
</html>