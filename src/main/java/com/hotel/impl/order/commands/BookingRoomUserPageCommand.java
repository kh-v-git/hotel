package com.hotel.impl.order.commands;

import com.hotel.Command;
import com.hotel.security.RequiresRole;
import com.hotel.utils.enums.UserRolesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * command to view index_admin.jsp
 */
@RequiresRole(UserRolesEnum.USER)
public class BookingRoomUserPageCommand implements Command {
    private static final Logger log = LogManager.getLogger(BookingRoomUserPageCommand.class);
    /**
     * Collect data for index_admin.jsp
     *
     * @param request  http request
     * @param response http response
     * @throws Exception maintenance exception
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {



        pageLocale(request, "booking-page-user.command");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/user/booking_user.jsp");
        requestDispatcher.forward(request, response);
    }

    private void pageLocale(HttpServletRequest localeRequest, String servletCommand) {
        String queryString = Optional.ofNullable(localeRequest.getQueryString())
                .map(Object::toString)
                .map(String::trim)
                .orElse("");
        if (servletCommand.isEmpty()) {
            servletCommand = "secured-user.command";
        }
        localeRequest.getSession().setAttribute("pageQuery", queryString);
        localeRequest.getSession().setAttribute("pageCommand", servletCommand);
    }

}
