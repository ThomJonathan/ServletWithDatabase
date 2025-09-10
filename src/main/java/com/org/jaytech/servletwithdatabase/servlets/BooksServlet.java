package com.org.jaytech.servletwithdatabase.servlets;

import com.org.jaytech.servletwithdatabase.model.Book;
import com.org.jaytech.servletwithdatabase.dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/books")
public class BooksServlet extends HttpServlet {
    private final BookDAO bookDAO = new BookDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String q = req.getParameter("q");
        List<Book> books = (q != null && !q.isEmpty()) ? bookDAO.search(q) : bookDAO.list();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/listBooks.jsp").forward(req, resp);
    }
}
