package com.hotel.impl.common;


import com.hotel.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * command to change locale
 */
public class LocaleCommand implements Command {
    private static final Logger log = LogManager.getLogger(LocaleCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String locale = Optional.ofNullable(request.getParameter("locale"))
                .map(Object::toString)
                .map(String::trim)
                .orElse("en_US");
        request.getSession().setAttribute("locale", locale);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.command");
        requestDispatcher.forward(request, response);
    }
}
