package com.hotel.impl.admin.room.commands;

import com.hotel.Command;
import com.hotel.impl.room.RoomRepository;
import com.hotel.impl.room.RoomRepositorySQLImpl;
import com.hotel.impl.room.RoomService;
import com.hotel.security.RequiresRole;
import com.hotel.utils.DataPatternValidation;
import com.hotel.utils.enums.UserRolesEnum;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * command to delete room by ID
 */
@RequiresRole(UserRolesEnum.ADMIN)
public class RoomDeleteAdminCommand implements Command {
    private static final Logger log = LogManager.getLogger(RoomDeleteAdminCommand.class);
    /**
     * delete room by ID
     *
     * @param request  http request
     * @param response http response
     * @throws Exception maintenance exception
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String roomId = request.getParameter("room-id");
        if (DataPatternValidation.intCheck(roomId)) {
            if (deleteRoom(Integer.parseInt(roomId))) {
                request.setAttribute("statusCommand", "Room was deleted");
                log.log(Level.DEBUG, String.format("Room  with id '%s' was deleted by admin", roomId));
            } else {
                request.setAttribute("errorCommand", "Room delete failed");
            }
        } else {
            request.setAttribute("errorCommand", "Room validation failed");
        }
        response.sendRedirect("secured-admin-rooms.command");
        /*
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("secured-admin-rooms.command");
        requestDispatcher.forward(request, response);

         */
    }
    private boolean deleteRoom(int roomId) {
        RoomRepository roomRepository = new RoomRepositorySQLImpl();
        RoomService roomService = new RoomService(roomRepository);
        return roomService.deleteRoom(roomId);
    }
}
