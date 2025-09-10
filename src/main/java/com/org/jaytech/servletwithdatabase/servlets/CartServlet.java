package com.org.jaytech.servletwithdatabase.servlets;

import com.org.jaytech.servletwithdatabase.dao.BookDAO;
import com.org.jaytech.servletwithdatabase.model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private final BookDAO bookDAO = new BookDAO();

    @SuppressWarnings("unchecked")
    private Map<Integer,Integer> getCart(HttpSession session){
        Map<Integer,Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        if(cart == null){
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(true);
        Map<Integer,Integer> cart = getCart(session);
        String action = req.getParameter("action");
        if("add".equals(action)){
            int bookId = Integer.parseInt(req.getParameter("bookId"));
            int qty = Integer.parseInt(req.getParameter("qty"));
            cart.put(bookId, cart.getOrDefault(bookId, 0) + Math.max(1, qty));
        } else if("remove".equals(action)){
            int bookId = Integer.parseInt(req.getParameter("bookId"));
            cart.remove(bookId);
        } else if("clear".equals(action)){
            cart.clear();
        }
        resp.sendRedirect("cart");
    }
}
