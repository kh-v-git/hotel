package com.hotel.impl.request.command;

import com.hotel.Command;
import com.hotel.impl.request.RequestRepository;
import com.hotel.impl.request.RequestRepositorySQLImpl;
import com.hotel.impl.request.RequestService;
import com.hotel.security.RequiresRole;
import com.hotel.utils.DataPatternValidation;
import com.hotel.utils.enums.UserRolesEnum;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * command to delete user request
 */
@RequiresRole(UserRolesEnum.USER)
public class RequestRoomDeleteUserCommand implements Command {
    private static final Logger log = LogManager.getLogger(RequestRoomDeleteUserCommand.class);

    /**
     * Delete user request room by request ID and userID
     *
     * @param request  http request
     * @param response http response
     * @throws Exception maintenance exception
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (userDataValidation(request)) {
            if (deleteUserRequestRoom(request)) {
                request.setAttribute("statusPage", "Request remove success");
            } else {
                request.setAttribute("errorPage", "Request remove failed. DB error.");
                log.log(Level.DEBUG, "Request remove failed. DB error.");
            }
        } else {
            request.setAttribute("errorPage", "Request data validation error");
            log.log(Level.DEBUG, "Request remove failed. DB error.");

        }
        response.sendRedirect("secured-user.command");
    }

    private boolean userDataValidation(HttpServletRequest req) {
        String userId = req.getSession().getAttribute("userId").toString();
        if (!DataPatternValidation.intCheck(userId)) {
            req.setAttribute("errorCommand", "User id not valid");
            return false;
        }
        String request = req.getParameter("request-id");
        if (!DataPatternValidation.intCheck(request)) {
            req.setAttribute("errorCommand", "User id not valid");
            return false;
        }
        return true;
    }

    private boolean deleteUserRequestRoom(HttpServletRequest req) {
        int userId = (Integer) req.getSession().getAttribute("userId");
        int requestId = Integer.parseInt(req.getParameter("request-id"));
        RequestRepository requestRepository = new RequestRepositorySQLImpl();
        RequestService requestService = new RequestService(requestRepository);
        return requestService.deleteRequest(requestId, userId);
    }
}
