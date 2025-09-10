<%@ page import="java.util.List" %>
<%@ page import="com.org.jaytech.servletwithdatabase.model.Order" %>
<%@ page import="com.org.jaytech.servletwithdatabase.model.OrderItem" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Orders</title></head>
<body>
<h1>Orders</h1>
<a href="books">Books</a> | <a href="cart">Cart</a> | <a href="logout">Logout</a>
<hr>
<%
    List<Order> orders = (List<Order>) request.getAttribute("orders");
    if(orders == null || orders.isEmpty()){
%>
<p>No orders yet.</p>
<% } else {
    for(Order o : orders){ %>
        <div style="border:1px solid #999; padding:8px; margin:6px;">
            <b>Order #<%= o.getId() %></b> - Date: <%= o.getOrderDate() %> - Total: MK<%= o.getTotalAmount() %>
            <details>
                <summary>Items</summary>
                <ul>
                <% for(OrderItem item : o.getItems()){ %>
                    <li><%= item.getBook().getTitle() %> x <%= item.getQuantity() %> @ MK<%= item.getUnitPrice() %></li>
                <% } %>
                </ul>
            </details>
        </div>
<%  } } %>
</body>
</html>
