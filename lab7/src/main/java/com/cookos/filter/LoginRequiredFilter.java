package com.cookos.filter;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

@WebFilter(filterName = "LoginRequiredFilter", urlPatterns = "/GroupListServlet")
public class LoginRequiredFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        var httpReq = (HttpServletRequest) request;
        var session = httpReq.getSession();

        if (session.getAttribute("name") != null) {
            chain.doFilter(request, response);
        }
        else {
            session.invalidate();
            request.getRequestDispatcher("LoginServlet").forward(request, response);
        }

    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
