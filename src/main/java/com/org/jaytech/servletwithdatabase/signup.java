package com.org.jaytech.servletwithdatabase;

import Utils.HibernateUtil;
import com.org.jaytech.servletwithdatabase.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;

import java.io.IOException;
@WebServlet("/signup")

public class signup extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        if(role == null || role.isEmpty()) role = "CUSTOMER";

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setRole(role);

        // basic duplicate username prevention (best-effort)
        UserDAO userDAO = new UserDAO();
        if(userDAO.findByUsername(name) != null){
            resp.sendRedirect("Signup.jsp?error=exists");
            return;
        }

        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
        session.close();

        if(user.getId()!=0){
            resp.sendRedirect("Login.jsp");
        } else {
            resp.sendRedirect("Signup.jsp?error=failed");
        }
    }

}
