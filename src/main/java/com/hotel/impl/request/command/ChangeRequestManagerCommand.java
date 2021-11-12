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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * command to delete user request
 */
@RequiresRole(UserRolesEnum.MANAGER)
public class ChangeRequestManagerCommand implements Command {
    private static final Logger log = LogManager.getLogger(ChangeRequestManagerCommand.class);
    /**
     * Decline user request room by request ID and userID
     *
     * @param request  http request
     * @param response http response
     * @throws Exception maintenance exception
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (userDataValidation(request)) {
            if (changeUserRequestStatus(request)) {
                request.setAttribute("statusPage", "Request status changed");
            } else {
                request.setAttribute("errorPage", "Request status not changed. DB error.");
                log.log(Level.DEBUG, "Request status not changed. DB error.");
            }
        } else {
            request.setAttribute("errorPage", "Request status not changed. Data validation error.");
            log.log(Level.DEBUG, "Request status not changed. Data validation error.");

        }
        response.sendRedirect("secured-manager.command");


    }
    private boolean changeUserRequestStatus(HttpServletRequest req) {
        int userId = Integer.parseInt(req.getParameter("user-id"));
        int requestId = Integer.parseInt(req.getParameter("request-id"));
        String status = req.getParameter("status");
        RequestRepository requestRepository = new RequestRepositorySQLImpl();
        RequestService requestService = new RequestService(requestRepository);
        return requestService.updateRequest(requestId, userId, status);
    }

    private boolean userDataValidation(HttpServletRequest req) {
        String userId = req.getParameter("user-id");
        if (!DataPatternValidation.intCheck(userId)) {
            req.setAttribute("errorCommand", "User id not valid");
            return false;
        }
        String request = req.getParameter("request-id");
        if (!DataPatternValidation.intCheck(request)) {
            req.setAttribute("errorCommand", "Request id not valid");
            return false;
        }
        String status = req.getParameter("status");
        if (!DataPatternValidation.stringCheck(status)) {
            req.setAttribute("errorCommand", "Status not valid");
            return false;
        }
        return true;
    }
}
