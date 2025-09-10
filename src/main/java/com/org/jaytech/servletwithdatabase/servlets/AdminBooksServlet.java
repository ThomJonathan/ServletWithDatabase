package com.org.jaytech.servletwithdatabase.servlets;

import com.org.jaytech.servletwithdatabase.dao.BookDAO;
import com.org.jaytech.servletwithdatabase.model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/books")
public class AdminBooksServlet extends HttpServlet {
    private final BookDAO bookDAO = new BookDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookDAO.list();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/adminBooks.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        if("add".equals(action)){
            Book b = new Book();
            b.setTitle(req.getParameter("title"));
            b.setAuthor(req.getParameter("author"));
            b.setPrice(Double.parseDouble(req.getParameter("price")));
            b.setStock(Integer.parseInt(req.getParameter("stock")));
            bookDAO.save(b);
        } else if("update".equals(action)){
            int id = Integer.parseInt(req.getParameter("id"));
            Book b = bookDAO.find(id);
            if(b != null){
                b.setTitle(req.getParameter("title"));
                b.setAuthor(req.getParameter("author"));
                b.setPrice(Double.parseDouble(req.getParameter("price")));
                b.setStock(Integer.parseInt(req.getParameter("stock")));
                bookDAO.update(b);
            }
        } else if("delete".equals(action)){
            int id = Integer.parseInt(req.getParameter("id"));
            bookDAO.delete(id);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/books");
    }
}
