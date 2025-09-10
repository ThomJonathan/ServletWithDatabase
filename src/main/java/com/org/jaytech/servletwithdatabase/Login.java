package com.org.jaytech.servletwithdatabase;

import com.org.jaytech.servletwithdatabase.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserDAO dao = new UserDAO();
        User user = dao.authenticate(username, password);
        if(user != null){
            HttpSession session = req.getSession(true);
            session.setAttribute("user", user);
            if("ADMIN".equalsIgnoreCase(user.getRole())){
                resp.sendRedirect("admin/books");
            } else {
                resp.sendRedirect("index.jsp?message=Welcome, " + user.getName() + " !");
            }
        } else {
            resp.sendRedirect("Login.jsp?error=invalid");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // handle logout if path is /logout
        if(req.getServletPath().equals("/logout")){
            HttpSession session = req.getSession(false);
            if(session != null) session.invalidate();
            resp.sendRedirect("Login.jsp");
        } else {
            resp.sendRedirect("Login.jsp");
        }
    }
}
