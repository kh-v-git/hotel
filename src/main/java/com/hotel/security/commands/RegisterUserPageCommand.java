package com.hotel.security.commands;

import com.hotel.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
/**
 * command to view register page
 */
public class RegisterUserPageCommand implements Command {
    private static final Logger log = LogManager.getLogger(RegisterUserPageCommand.class);

    /**
     * Collect data for register.jsp
     * @param request  http request
     * @param response http response
     * @throws Exception maintenance exception
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String queryString = Optional.ofNullable(request.getQueryString())
                .map(Object::toString)
                .map(String::trim)
                .orElse("");

        request.getSession().setAttribute("pageQuery", queryString);
        request.getSession().setAttribute("pageCommand", "register-user-page.command");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/register.jsp");
        requestDispatcher.forward(request, response);
    }
}
