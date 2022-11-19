package com.cookos.servlet;

import java.io.IOException;

import com.cookos.dao.UserDao;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    public static final String LOGIN_JSP = "/WEB-INF/views/login.jsp";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var name = request.getParameter("name");
        var password = request.getParameter("password");

        var userDao = new UserDao();

        if (userDao.isValidUser(name, password)) {

            request.getSession().setAttribute("name", name);
            response.sendRedirect(request.getContextPath() + "/GroupListServlet");

        } else {
            request.setAttribute("errorMessage", "Invalid Login and password!!");
            request.getRequestDispatcher(LOGIN_JSP)
                   .forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(LOGIN_JSP)
               .forward(request, response);
    }

}
