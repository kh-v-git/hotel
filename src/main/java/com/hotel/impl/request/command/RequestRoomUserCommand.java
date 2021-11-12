package com.hotel.impl.request.command;

import com.hotel.Command;
import com.hotel.impl.request.Request;
import com.hotel.impl.request.RequestRepository;
import com.hotel.impl.request.RequestRepositorySQLImpl;
import com.hotel.impl.request.RequestService;
import com.hotel.security.RequiresRole;
import com.hotel.utils.DataPatternValidation;
import com.hotel.utils.enums.UserRequestStatusEnum;
import com.hotel.utils.enums.UserRolesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * command to submit user request
 */
@RequiresRole(UserRolesEnum.USER)
public class RequestRoomUserCommand implements Command {
    private static final Logger log = LogManager.getLogger(RequestRoomUserCommand.class);

    /**
     * Collect data for user request
     *
     * @param request  http request
     * @param response http response
     * @throws Exception maintenance exception
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (userDataValidation(request)) {
            if (requestLog(request)) {
                request.setAttribute("statusPage", "Order add success");
            } else {
                request.setAttribute("errorPage", "Order add failed. DB error.");
            }
        } else {
            request.setAttribute("errorPage", "Request data validation error");
        }
        response.sendRedirect("secured-user.command");
    }

    private boolean userDataValidation(HttpServletRequest req) {
        String userId = req.getSession().getAttribute("userId").toString();
        if (!DataPatternValidation.intCheck(userId)) {
            req.setAttribute("errorCommand", "User id not valid");
            return false;
        }
        String arrivalDate = req.getParameter("arrival-date");
        if (!DataPatternValidation.dateCheck(arrivalDate)) {
            req.setAttribute("errorCommand", "User id not valid");
            return false;
        }

        String departureDate = req.getParameter("departure-date");
        if (!DataPatternValidation.dateCheck(departureDate)) {
            req.setAttribute("errorCommand", "User id not valid");
            return false;
        }
        LocalDate arrival = LocalDate.parse(arrivalDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate departure = LocalDate.parse(departureDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (departure.isBefore(arrival) || arrival.isBefore(LocalDate.now()) || departure.isEqual(LocalDate.now()) ) {
            req.setAttribute("errorCommand", "Date range is not valid");
            return false;
        }
        String adultsCapacity = req.getParameter("adults-capacity");
        if (!DataPatternValidation.intCheck(adultsCapacity)) {
            req.setAttribute("errorCommand", "User id not valid");
            return false;
        }
        String childrenCapacity = req.getParameter("children-capacity");
        if (!DataPatternValidation.intCheck(childrenCapacity)) {
            req.setAttribute("errorCommand", "User id not valid");
            return false;
        }
        String bedSize = req.getParameter("bed-size");
        if (!DataPatternValidation.stringCheck(bedSize)) {
            req.setAttribute("errorCommand", "User id not valid");
            return false;
        }
        return true;
    }

    private boolean requestLog(HttpServletRequest req) {
        RequestRepository requestRepository = new RequestRepositorySQLImpl();
        RequestService requestService = new RequestService(requestRepository);
        Request newRequest = new Request();
        newRequest.setUserID((Integer) req.getSession().getAttribute("userId"));
        newRequest.setStatus(UserRequestStatusEnum.REQUESTED.toString().toLowerCase());
        newRequest.setBedSize(req.getParameter("bed-size"));
        newRequest.setAdultCapacity(Integer.parseInt(req.getParameter("adults-capacity")));
        newRequest.setChildCapacity(Integer.parseInt(req.getParameter("children-capacity")));
        String arrivalDate = req.getParameter("arrival-date");
        newRequest.setArrivalDay(LocalDate.parse(arrivalDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        String departureDate = req.getParameter("departure-date");
        newRequest.setDepartureDay(LocalDate.parse(departureDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return requestService.setRequest(newRequest);
    }
}
