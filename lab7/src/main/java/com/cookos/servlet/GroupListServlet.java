package com.cookos.servlet;

import java.io.IOException;

import com.cookos.dao.PersonDao;
import com.cookos.model.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "GroupListServlet", urlPatterns = "/GroupListServlet")
public class GroupListServlet extends HttpServlet {

    private static final String WELCOME_JSP = "/WEB-INF/views/welcome.jsp";
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try (var daoPerson = new PersonDao()) {
            var nname = request.getParameter("nname");
            var nphone = request.getParameter("nphone");
            var nemail = request.getParameter("nemail");

            if (nname.isEmpty() || nphone.isEmpty() || nemail.isEmpty()) {
                request.setAttribute("errorMessage", "Заполните все поля");
            } else {
                daoPerson.insertPerson(new Person(nname, nphone, nemail));
            }

            request.setAttribute("group", daoPerson.getPersons());
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher(WELCOME_JSP).forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try (var daoPerson = new PersonDao()) {
            request.setAttribute("group", daoPerson.getPersons());
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher(WELCOME_JSP).forward(request, response);
    }
}
