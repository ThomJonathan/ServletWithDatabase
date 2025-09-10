package com.org.jaytech.servletwithdatabase.servlets;

import com.org.jaytech.servletwithdatabase.User;
import com.org.jaytech.servletwithdatabase.dao.OrderDAO;
import com.org.jaytech.servletwithdatabase.model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/orders")
public class OrdersServlet extends HttpServlet {
    private final OrderDAO orderDAO = new OrderDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("user") == null){
            resp.sendRedirect("Login.jsp");
            return;
        }
        User user = (User) session.getAttribute("user");
        List<Order> orders;
        if("ADMIN".equalsIgnoreCase(user.getRole())){
            orders = orderDAO.listAll();
        } else {
            orders = orderDAO.listByUser(user);
        }
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/orders.jsp").forward(req, resp);
    }
}
