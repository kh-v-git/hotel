package com.hotel.impl.admin.room.commands;

import com.hotel.Command;
import com.hotel.security.RequiresRole;
import com.hotel.utils.enums.RoomBedSizeEnum;
import com.hotel.utils.enums.UserRolesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * command to add new room
 */
@RequiresRole(UserRolesEnum.ADMIN)
public class RoomAddNewAdminPageCommand implements Command {
    private static final Logger log = LogManager.getLogger(RoomAddNewAdminPageCommand.class);
    /**
     * Add new room to DataBase
     *
     * @param request  http request
     * @param response http response
     * @throws Exception maintenance exception
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("bedSizeList", RoomBedSizeEnum.getRoomBedSizeList());
        pageLocale(request, "room-add-new-page-admin.command");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/admin/room_add_new_admin.jsp");
        requestDispatcher.forward(request, response);
    }

    private void pageLocale(HttpServletRequest localeRequest, String servletCommand) {
        String queryString = Optional.ofNullable(localeRequest.getQueryString())
                .map(Object::toString)
                .map(String::trim)
                .orElse("");
        if (servletCommand.isEmpty()) {
            servletCommand = "secured-admin.command";
        }
        localeRequest.getSession().setAttribute("pageQuery", queryString);
        localeRequest.getSession().setAttribute("pageCommand", servletCommand);
    }
}
