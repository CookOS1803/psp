package com.cookos.servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import com.cookos.dao.UserDao;
import com.cookos.util.HashPassword;

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

        try (var userDao = new UserDao()) {
            if (userDao.isValidUser(name, HashPassword.getHash(password))) {

                request.getSession().setAttribute("name", name);

                var cookies = request.getCookies();

                if (cookies != null) {
                    for (var cookie : cookies) {
                        System.out.println(cookie.getName() + cookie.getValue());

                        if (name.equals(cookie.getName())) {
                            request.getSession().setAttribute("lastdate", cookie.getValue());
                        }
                    }
                }

                var userCookie = new Cookie(name, LocalDateTime.now().toString());
                userCookie.setMaxAge(60 * 60 * 24 * 365);
                response.addCookie(userCookie);

                response.sendRedirect(request.getContextPath() + "/GroupListServlet");

            } else {
                request.setAttribute("errorMessage", "Invalid Login and password!!");
                request.getRequestDispatcher(LOGIN_JSP)
                       .forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(LOGIN_JSP)
               .forward(request, response);
    }

}
