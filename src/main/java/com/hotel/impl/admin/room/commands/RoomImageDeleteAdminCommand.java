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

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * command to delete room image by ID
 */
@RequiresRole(UserRolesEnum.ADMIN)
public class RoomImageDeleteAdminCommand implements Command {
    private static final Logger log = LogManager.getLogger(RoomImageDeleteAdminCommand.class);
    /**
     * delete room image by ID
     *
     * @param request  http request
     * @param response http response
     * @throws Exception maintenance exception
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String imageId = request.getParameter("image-id");
        String roomId = request.getParameter("room-id");
        if (DataPatternValidation.intCheck(imageId) && DataPatternValidation.intCheck(roomId)) {
            if (deleteImage(Integer.parseInt(imageId))) {
                request.setAttribute("statusCommand", "Image was deleted");
            } else {
                request.setAttribute("errorCommand", "Image delete failed");
                log.log(Level.DEBUG, String.format("Image delete by ID '%s' failed", imageId));
            }
        } else {
            request.setAttribute("errorCommand", "Image data validation failed");
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("room-edit-page-admin.command?room-id=" + roomId);
        requestDispatcher.forward(request, response);
    }
    private boolean deleteImage(int imageId) {
        RoomRepository roomRepository = new RoomRepositorySQLImpl();
        RoomService roomService = new RoomService(roomRepository);
        return roomService.deleteRoomImage(imageId);
    }
}
