<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.org.jaytech.servletwithdatabase.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bookstore</title>
</head>
<body>
<h1>Welcome to the Bookstore</h1>
<%
    User u = (User) session.getAttribute("user");
    if(u == null){
%>
<p><a href="Login.jsp">Login</a> or <a href="Signup.jsp">Signup</a></p>
<%
    } else if ("ADMIN".equalsIgnoreCase(u.getRole())) {
%>
<p>Hello, <%= u.getName() %> (Admin)</p>
<p><a href="admin/books">Manage Books</a> | <a href="orders">View Orders</a> | <a href="logout">Logout</a></p>
<%
    } else {
%>
<p>Hello, <%= u.getName() %></p>
<p><a href="books">Browse Books</a> | <a href="cart">Cart</a> | <a href="orders">My Orders</a> | <a href="logout">Logout</a></p>
<% } %>
</body>
</html>