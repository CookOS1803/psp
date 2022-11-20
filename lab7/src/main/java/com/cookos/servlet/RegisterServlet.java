package com.cookos.servlet;

import java.io.IOException;

import com.cookos.dao.UserDao;
import com.cookos.model.User;
import com.cookos.util.HashPassword;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "RegisterServlet", urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    public static final String REGISTER_JSP = "/WEB-INF/views/register.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var name = request.getParameter("newLoginName");
        var password = request.getParameter("newPassword");

        try (var daoUser = new UserDao()) {
            var user = new User(name, HashPassword.getHash(password));
            
            if (daoUser.insertUser(user)) {
                request.getRequestDispatcher(LoginServlet.LOGIN_JSP).forward(request, response);
            } else {
                request.setAttribute("errorRegister", "Выберите другое имя, такой пользователь существет");
                request.getRequestDispatcher(REGISTER_JSP)
                       .forward(request, response);
            }
        } catch (ServletException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(REGISTER_JSP)
               .forward(request, response);

    }
}
