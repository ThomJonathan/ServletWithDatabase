package com.org.jaytech.servletwithdatabase.servlets;

import Utils.HibernateUtil;
import com.org.jaytech.servletwithdatabase.User;
import com.org.jaytech.servletwithdatabase.dao.BookDAO;
import com.org.jaytech.servletwithdatabase.dao.OrderDAO;
import com.org.jaytech.servletwithdatabase.model.Book;
import com.org.jaytech.servletwithdatabase.model.Order;
import com.org.jaytech.servletwithdatabase.model.OrderItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private final BookDAO bookDAO = new BookDAO();
    private final OrderDAO orderDAO = new OrderDAO();

    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession httpSession = req.getSession(false);
        if(httpSession == null || httpSession.getAttribute("user") == null){
            resp.sendRedirect("Login.jsp");
            return;
        }
        User user = (User) httpSession.getAttribute("user");
        Map<Integer,Integer> cart = (Map<Integer,Integer>) httpSession.getAttribute("cart");
        if(cart == null || cart.isEmpty()){
            resp.sendRedirect("cart");
            return;
        }

        // Validate stock and build order
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        List<OrderItem> items = new ArrayList<>();
        double total = 0.0;

        Session s = HibernateUtil.getSessionFactory().openSession();
        try{
            s.beginTransaction();
            for(Map.Entry<Integer,Integer> e : cart.entrySet()){
                Book b = s.find(Book.class, e.getKey());
                int qty = e.getValue();
                if(b == null || b.getStock() < qty){
                    // insufficient stock -> simple redirect with error
                    s.getTransaction().rollback();
                    s.close();
                    resp.sendRedirect("cart?error=stock");
                    return;
                }
                b.setStock(b.getStock() - qty);
                s.merge(b);

                OrderItem oi = new OrderItem();
                oi.setOrder(order);
                oi.setBook(b);
                oi.setQuantity(qty);
                oi.setUnitPrice(b.getPrice());
                items.add(oi);
                total += b.getPrice() * qty;
            }
            order.setTotalAmount(total);
            order.getItems().addAll(items);
            s.persist(order);
            s.getTransaction().commit();
        } finally {
            if(s.isOpen()) s.close();
        }

        // Clear cart
        cart.clear();
        resp.sendRedirect("orders");
    }
}
