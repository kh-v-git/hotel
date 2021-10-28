package com.hotel.security.commands;

import com.hotel.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginPageCommand implements Command {
    private static final Logger log = LogManager.getLogger(LoginPageCommand.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/login.jsp");
        requestDispatcher.forward(request, response);
    }
}
