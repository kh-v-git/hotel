package com.hotel.security;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if (
                path.startsWith("/assets")
                || path.startsWith("/locale.command")
                || path.startsWith("/index.command")
                || path.startsWith("/rooms-view.command")
                || path.startsWith("/room.command")
                || path.startsWith("/login.command")
                || path.startsWith("/authenticate.command")
                || path.startsWith("/register-user-page.command")
                || path.startsWith("/register.command")
        ) {
            chain.doFilter(request, response);
            return;
        }
        String email = (String) httpRequest.getSession().getAttribute("userEmail");
        if (email == null || email.isEmpty()) {
            ((HttpServletResponse) response).sendRedirect("index.command");
            return;
        }
        chain.doFilter(request, response);
    }
}

