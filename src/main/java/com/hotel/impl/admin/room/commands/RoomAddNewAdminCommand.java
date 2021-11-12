package com.hotel.impl.admin.room.commands;

import com.hotel.Command;
import com.hotel.impl.room.Room;
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
 * command to view room_new_admin.jsp
 */
@RequiresRole(UserRolesEnum.ADMIN)
public class RoomAddNewAdminCommand implements Command {
    private static final Logger log = LogManager.getLogger(RoomEditAdminPageCommand.class);
    /**
     * Collect data for room_new_admin.jsp
     *
     * @param request  http request
     * @param response http response
     * @throws Exception maintenance exception
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (roomDataValidation(request)) {
            if (roomAdd(request)) {
                request.setAttribute("statusPage", "Room add success");
            } else {
                log.log(Level.DEBUG, "Room add error");
                request.setAttribute("errorPage", "Room add failed. DB error.");
            }
        } else {
            log.log(Level.DEBUG, "Room add error");
            request.setAttribute("errorPage", "Room add failed. Data error.");
        }
        response.sendRedirect("secured-admin-rooms.command");
        /*
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("secured-admin-rooms.command");
        requestDispatcher.forward(request, response);

         */
    }

    private boolean roomDataValidation(HttpServletRequest req) {
        String roomNumber = req.getParameter("room-number".trim());
        if (!DataPatternValidation.intCheck(roomNumber)) {
            req.setAttribute("errorCommand", "Room number not valid");
            return false;
        }
        String roomAdultCapacity = req.getParameter("room-adult-capacity".trim());
        if (!DataPatternValidation.intCheck(roomAdultCapacity)) {
            req.setAttribute("errorCommand", "Adult Capacity not valid");
            return false;
        }
        String roomChildrenCapacity = req.getParameter("room-children-capacity".trim());
        if (!DataPatternValidation.intCheck(roomChildrenCapacity)) {
            req.setAttribute("errorCommand", "Children Capacity number not valid");
            return false;
        }
        String roomPrice = req.getParameter("room-price".trim());
        if (!DataPatternValidation.decCheck(roomPrice)) {
            req.setAttribute("errorCommand", "Room price not valid");
            return false;
        }

        String roomBedSize = req.getParameter("room-bed-size".trim());
        if (!DataPatternValidation.stringCheck(roomBedSize)) {
            req.setAttribute("errorCommand", "Room Bed Size not valid");
            return false;
        }

        String roomAbout = req.getParameter("room-about".trim());
        if (!DataPatternValidation.stringCheck(roomAbout)) {
            req.setAttribute("errorCommand", "Room About not valid");
            return false;
        }
        return true;
    }

    private boolean roomAdd(HttpServletRequest req) {
        RoomRepository roomRepository = new RoomRepositorySQLImpl();
        RoomService roomService = new RoomService(roomRepository);
        Room newRoom = new Room();
        newRoom.setNumber(Integer.parseInt(req.getParameter("room-number")));
        newRoom.setAdultCapacity(Integer.parseInt(req.getParameter("room-adult-capacity")));
        newRoom.setChildrenCapacity(Integer.parseInt(req.getParameter("room-children-capacity")));
        newRoom.setPrice(Double.parseDouble(req.getParameter("room-price".trim())));
        newRoom.setBedSize(req.getParameter("room-bed-size".trim()));
        newRoom.setAbout(req.getParameter("room-about".trim()));
        return roomService.setRoom(newRoom);
    }
}
