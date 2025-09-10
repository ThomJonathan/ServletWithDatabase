package com.org.jaytech.servletwithdatabase.filters;

import com.org.jaytech.servletwithdatabase.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = "/admin/*")
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        User user = (session!=null)? (User) session.getAttribute("user") : null;
        if(user == null){
            resp.sendRedirect(req.getContextPath() + "/Login.jsp");
            return;
        }
        if(!"ADMIN".equalsIgnoreCase(user.getRole())){
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        chain.doFilter(request, response);
    }
}
