<%--<%@ page import="java.util.List" %>--%>
<%--<%@ page import="com.org.jaytech.servletwithdatabase.model.Book" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head><title>Admin - Books</title>--%>
<%--<style>--%>



<%--    body{--%>
<%--        font-family: sans-serif;--%>
<%--        font-size: 14px;--%>
<%--    }--%>
<%--    a{--%>
<%--        text-decoration: none;--%>
<%--        /*lets make it have some padding and borders.. its should look like a button*/--%>
<%--        padding: 5px;--%>
<%--        border: 1px solid #ccc;--%>
<%--        border-radius: 5px;--%>
<%--        color: #ae4646;--%>
<%--        background-color: #27f467;--%>
<%--    }--%>
<%--    a:hover{--%>
<%--        text-decoration: underline;--%>
<%--    }--%>
<%--    details{--%>
<%--        margin-left: 20px;--%>
<%--    }--%>
<%--    details summary{--%>
<%--        cursor: pointer;--%>
<%--    }--%>
<%--    details[open] summary{--%>
<%--        text-decoration: underline;--%>
<%--    }--%>
<%--    /*lets style the add-book form to look nice*/--%>
<%--    .add-book{--%>
<%--        margin: 10px 0;--%>
<%--        padding: 10px;--%>
<%--        border: 1px solid #ccc;--%>
<%--    }--%>
<%--    .add-book input{--%>
<%--        margin: 5px 0;--%>
<%--    /*    lets change the color of the input too*/--%>

<%--    }--%>

<%--</style>--%>



<%--</head>--%>
<%--<body>--%>
<%--<h1>Admin - Manage Books</h1>--%>
<%--<a href="<%=request.getContextPath()%>/books">Public Books</a> | <a href="<%=request.getContextPath()%>/orders">Orders</a> | <a href="<%=request.getContextPath()%>/logout">Logout</a>--%>
<%--<hr>--%>
<%--<h3>Add Book</h3>--%>
<%--<form method="post" action="<%=request.getContextPath()%>/admin/books" class="add-book">--%>
<%--    <input type="hidden" name="action" value="add">--%>
<%--    Title: <input name="title" required>--%>
<%--    Author: <input name="author" required>--%>
<%--    Price: <input name="price" type="number" step="0.01" required>--%>
<%--    Stock: <input name="stock" type="number" required>--%>
<%--    <input type="submit" value="Add">--%>
<%--</form>--%>
<%--<hr>--%>
<%--<h3>Existing Books</h3>--%>
<%--<%--%>
<%--    List<Book> books = (List<Book>) request.getAttribute("books");--%>
<%--    if(books != null){--%>
<%--        for(Book b : books){--%>
<%--%>--%>
<%--<div style="border:1px solid #ccc; padding:8px; margin:6px;">--%>
<%--    ID <%= b.getId() %> - <b><%= b.getTitle() %></b> by <%= b.getAuthor() %> - $<%= b.getPrice() %> [Stock: <%= b.getStock() %>]--%>
<%--    <form method="post" action="<%=request.getContextPath()%>/admin/books" style="display:inline;">--%>
<%--        <input type="hidden" name="action" value="delete">--%>
<%--        <input type="hidden" name="id" value="<%= b.getId() %>">--%>
<%--        <input type="submit" value="Delete">--%>
<%--    </form>--%>
<%--    <details>--%>
<%--        <summary>Edit</summary>--%>
<%--        <form method="post" action="<%=request.getContextPath()%>/admin/books">--%>
<%--            <input type="hidden" name="action" value="update">--%>
<%--            <input type="hidden" name="id" value="<%= b.getId() %>">--%>
<%--            Title: <input name="title" value="<%= b.getTitle() %>">--%>
<%--            Author: <input name="author" value="<%= b.getAuthor() %>">--%>
<%--            Price: <input name="price" type="number" step="0.01" value="<%= b.getPrice() %>">--%>
<%--            Stock: <input name="stock" type="number" value="<%= b.getStock() %>">--%>
<%--            <input type="submit" value="Update">--%>
<%--        </form>--%>
<%--    </details>--%>
<%--</div>--%>
<%--<%      }--%>
<%--    }--%>
<%--%>--%>
<%--</body>--%>
<%--</html>--%>


<%--
  Created by IntelliJ IDEA.
  User: HART DOVIKO
  Date: 9/10/2025
  Time: 12:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: HART DOVIKO
  Date: 9/10/2025
  Time: 12:12 PM
  To change this template use File | Settings | File Templates.
--%>

<%--
  Created by IntelliJ IDEA.
  User: HART DOVIKO
  Date: 9/10/2025
  Time: 12:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.org.jaytech.servletwithdatabase.model.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Admin - Books</title></head>
<body>
<h1>Admin - Manage Books</h1>
<a href="<%=request.getContextPath()%>/books">Public Books</a> | <a href="<%=request.getContextPath()%>/orders">Orders</a> | <a href="<%=request.getContextPath()%>/logout">Logout</a>
<hr>
<h3 style="text-align: center; font-size: x-large;">Add Book</h3>
<form method="post" action="<%=request.getContextPath()%>/admin/books">
    <input type="hidden" name="action" value="add">
    Title: <input name="title" required>
    Author: <input name="author" required>
    Price: <input name="price" type="number" step="0.01" required>
    Stock: <input name="stock" type="number" required>
    <input type="submit" value="Add" style="background-color: green; color: white;">
</form>
<hr>
<h3>Existing Books</h3>
<%
    List<Book> books = (List<Book>) request.getAttribute("books");
    if(books != null){
        for(Book b : books){
%>
<div style="border:1px solid #ccc; padding:8px; margin:6px;">
    <div style="border: 1px solid #ccc; padding: 10px; margin-bottom: 15px; background-color: #f9f9f9;">

        <!-- Book Info Row -->
        <div style="display: flex; justify-content: space-between; align-items: center;">
            <div style="gap: 10px;justify-content: space-between;">
                <b>ID <%= b.getId() %></b>
                <b><%= b.getTitle() %></b> by <%= b.getAuthor() %> -
                MKW<%= b.getPrice() %> [Stock: <%= b.getStock() %>]
            </div>

            <!-- Delete Button -->
            <form method="post" action="<%=request.getContextPath()%>/admin/books" style="display:inline;">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value="<%= b.getId() %>">
                <input type="submit" value="Delete"
                       style="border: none; background-color: red; color: white; padding: 6px 12px; font-weight: bold; cursor: pointer; border-radius: 4px;">
            </form>
        </div>

        <!-- Expandable Edit Form -->
        <details style="margin-top: 10px;">
            <summary style="cursor: pointer; font-weight: bold; color: blue;">Edit</summary>
            <form method="post" action="<%=request.getContextPath()%>/admin/books">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="<%= b.getId() %>">

                <table border="1" cellpadding="8" cellspacing="0"
                       style="border-collapse: collapse; border: 1px solid blue; margin-top: 10px; width: 100%;">
                    <tr>
                        <td>Title:</td>
                        <td><input name="title" value="<%= b.getTitle() %>" placeholder="Title" style="border: none; outline: none; width: 100%;"></td>
                    </tr>
                    <tr>
                        <td>Author:</td>
                        <td><input name="author" value="<%= b.getAuthor() %>" placeholder="Author" style="border: none; outline: none; width: 100%;"></td>
                    </tr>
                    <tr>
                        <td>Price:</td>
                        <td><input name="price" type="number" step="0.01" value="<%= b.getPrice() %>" placeholder="Price" style="border: none; outline: none; width: 100%;"></td>
                    </tr>
                    <tr>
                        <td>Stock:</td>
                        <td><input name="stock" type="number" value="<%= b.getStock() %>" placeholder="Stock" style="border: none; outline: none; width: 100%;"></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center;">
                            <input type="submit" value="Update"
                                   style="border: none; background-color: orange; color: white; padding: 8px 20px; font-weight: bold; cursor: pointer; border-radius: 5px;">
                        </td>
                    </tr>
                </table>
            </form>
        </details>
    </div>

</div>
<%      }
}
%>
</body>
</html>