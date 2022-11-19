package com.cookos.servlet;

import java.io.IOException;

import com.cookos.dao.UserDao;
import com.cookos.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "RegisterServlet", urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    public static final String REGISTER_JSP = "/WEB-INF/views/register.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var name = request.getParameter("newLoginName");
        var password = request.getParameter("newPassword");

        var daoUser = new UserDao();
        var user = new User(name, password);
        
        if (daoUser.insertUser(user)) {
            request.getRequestDispatcher(LoginServlet.LOGIN_JSP).forward(request, response);
        } else {
            request.setAttribute("errorRegister", "Выберите другое имя, такой пользователь существет");
            request.getRequestDispatcher(REGISTER_JSP)
                   .forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(REGISTER_JSP)
               .forward(request, response);

    }
}
