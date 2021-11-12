package com.hotel.impl.room.command;

import com.hotel.Command;
import com.hotel.exception.InvalidUserDataInput;
import com.hotel.impl.room.Room;
import com.hotel.impl.room.RoomRepository;
import com.hotel.impl.room.RoomRepositorySQLImpl;
import com.hotel.impl.room.RoomService;
import com.hotel.utils.DataPatternValidation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * command to view room page by id
 */
public class RoomGuestPageCommand implements Command {
    private static final Logger log = LogManager.getLogger(RoomGuestPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String roomId = request.getParameter("room-id");
        String queryString = Optional.ofNullable(request.getQueryString())
                .map(Object::toString)
                .map(String::trim)
                .orElse("");
        try {
            Room returnRoom = pageDataProcess(roomId);
            request.setAttribute("pageRoom", returnRoom);
        } catch (InvalidUserDataInput e) {
            log.log(Level.ERROR, "No room by id not found", e);
            request.setAttribute("errorPage", "No such room");
        }
        request.getSession().setAttribute("pageQuery", queryString);
        request.getSession().setAttribute("pageCommand", "room.command");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/main/room.jsp");
        requestDispatcher.forward(request, response);
    }

    private Room pageDataProcess(String searchParam) throws InvalidUserDataInput {
     Room room = new Room();
     if (DataPatternValidation.intCheck(searchParam)) {
         int roomId;
         try {
             roomId = Integer.parseInt(searchParam);
             RoomRepository roomRepository = new RoomRepositorySQLImpl();
             RoomService roomService = new RoomService(roomRepository);
             Optional<Room> maybeRoom = roomService.getRoom(roomId);
             if (maybeRoom.isPresent()) {
                 room = maybeRoom.get();
             } else {
                 throw new InvalidUserDataInput ("Room not found");
             }
         }catch (NumberFormatException ex) {
             log.log(Level.ERROR, "Room id parse error", ex);
             throw new InvalidUserDataInput("Room id parse error", ex);
         }
     } else {
         throw new InvalidUserDataInput("Data input error");
     }
     return room;
    }
}
