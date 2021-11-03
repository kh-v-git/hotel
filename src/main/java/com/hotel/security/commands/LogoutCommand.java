package com.hotel.security.commands;

import com.hotel.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * command to  logout.
 */
public class LogoutCommand implements Command {
    private static final Logger log = LogManager.getLogger(LogoutCommand.class);

    /**
     * Invalidate http session. Redirect to index.command
     * @param request  http request
     * @param response http response
     * @throws Exception maintenance exception
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().invalidate();
        response.sendRedirect("index.command");
    }
}
