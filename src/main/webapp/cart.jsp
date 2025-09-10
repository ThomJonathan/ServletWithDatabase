<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="com.org.jaytech.servletwithdatabase.dao.BookDAO" %>
<%@ page import="com.org.jaytech.servletwithdatabase.model.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Cart</title></head>
<body>
<h1>Your Cart</h1>
<a href="books">Continue Shopping</a> | <a href="orders">My Orders</a> | <a href="logout">Logout</a>
<% if (request.getParameter("error") != null) { %>
    <p style="color:red;">Checkout failed due to stock availability.</p>
<% } %>
<hr>
<%
    Map<Integer,Integer> cart = (Map<Integer,Integer>) session.getAttribute("cart");
    if(cart == null || cart.isEmpty()){
%>
<p>Cart is empty.</p>
<%
    } else {
        BookDAO bookDAO = new BookDAO();
        double total = 0.0;
%>
<table border="1" cellpadding="5" cellspacing="0">
<tr><th>Title</th><th>Price</th><th>Qty</th><th>Subtotal</th><th>Action</th></tr>
<%
        for(Map.Entry<Integer,Integer> e : cart.entrySet()){
            Book b = bookDAO.find(e.getKey());
            int qty = e.getValue();
            double sub = b.getPrice() * qty;
            total += sub;
%>
<tr>
    <td><%= b.getTitle() %></td>
    <td>MK<%= b.getPrice() %></td>
    <td><%= qty %></td>
    <td>MK<%= sub %></td>
    <td>
        <form method="post" action="cart">
            <input type="hidden" name="action" value="remove">
            <input type="hidden" name="bookId" value="<%= b.getId() %>">
            <input type="submit" value="Remove">
        </form>
    </td>
</tr>
<%      }
%>
<tr><td colspan="3" align="right"><b>Total</b></td><td colspan="2"><b>MK<%= total %></b></td></tr>
</table>
<form method="post" action="checkout">
    <input type="submit" value="Checkout">
</form>
<form method="post" action="cart" style="margin-top:10px;">
    <input type="hidden" name="action" value="clear">
    <input type="submit" value="Clear Cart">
</form>
<% } %>
</body>
</html>
