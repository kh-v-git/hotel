package com.hotel.impl.admin.room.commands;

import com.hotel.Command;
import com.hotel.impl.room.Room;
import com.hotel.impl.room.RoomRepository;
import com.hotel.impl.room.RoomRepositorySQLImpl;
import com.hotel.impl.room.RoomService;
import com.hotel.security.RequiresRole;
import com.hotel.utils.DataPatternValidation;
import com.hotel.utils.enums.RoomBedSizeEnum;
import com.hotel.utils.enums.UserRolesEnum;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * command to view room_edit_admin.jsp
 */
@RequiresRole(UserRolesEnum.ADMIN)
public class RoomEditAdminPageCommand implements Command {
    private static final Logger log = LogManager.getLogger(RoomEditAdminPageCommand.class);
    /**
     * Collect data for room_edit_admin.jsp
     *
     * @param request  http request
     * @param response http response
     * @throws Exception maintenance exception
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String roomId = request.getParameter("room-id");
        if (DataPatternValidation.intCheck(roomId)) {
            Optional<Room> maybeRoom = getRoom(Integer.parseInt(roomId));
            if (maybeRoom.isPresent()) {
                request.setAttribute("room", maybeRoom.get());
                request.setAttribute("bedSizeList", RoomBedSizeEnum.getRoomBedSizeList());
                pageLocale(request, "room-edit-admin-page.command");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/admin/room_edit_admin.jsp");
                requestDispatcher.forward(request, response);

            } else {
                log.log(Level.DEBUG, "Room by ID not found");

                request.setAttribute("errorPage", "User by ID not found");
                pageLocale(request, "user-edit-admin-page.command");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("secured-admin-rooms.command");
                requestDispatcher.forward(request, response);
            }

        } else {
            log.log(Level.DEBUG, "Room data validation failed");
            request.setAttribute("errorPage", "Data validation failed");
            pageLocale(request, "secured-admin.command");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("secured-admin-rooms.command");
            requestDispatcher.forward(request, response);
        }
    }

    private Optional<Room> getRoom(int id) {
        RoomRepository roomRepository = new RoomRepositorySQLImpl();
        RoomService roomService = new RoomService(roomRepository);
        return roomService.getRoom(id);
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
