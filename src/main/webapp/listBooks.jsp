<%@ page import="java.util.List" %>
<%@ page import="com.org.jaytech.servletwithdatabase.model.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Books</title></head>
<body>
<h1>Books</h1>
<form method="get" action="books">
    <input type="text" name="q" placeholder="Search">
    <input type="submit" value="Search">
</form>
<a href="cart">View Cart</a> | <a href="orders">My Orders</a> | <a href="logout">Logout</a>
<hr>
<%
    List<Book> books = (List<Book>) request.getAttribute("books");
    if(books != null){
        for(Book b : books){
%>
<div style="border:1px solid #ccc; padding:8px; margin:6px;">
    <b><%= b.getTitle() %></b> by <%= b.getAuthor() %> - $<%= b.getPrice() %> [Stock: <%= b.getStock() %>]
    <form method="post" action="cart" style="display:inline; margin-left:10px;">
        <input type="hidden" name="action" value="add">
        <input type="hidden" name="bookId" value="<%= b.getId() %>">
        <input type="number" name="qty" value="1" min="1">
        <input type="submit" value="Add to Cart">
    </form>
</div>
<%      }
    }
%>
</body>
</html>
