package com.hotel.impl.admin.room.commands;

import com.hotel.Command;
import com.hotel.impl.admin.room.Room;
import com.hotel.impl.admin.room.RoomRepository;
import com.hotel.impl.admin.room.RoomRepositorySQLImpl;
import com.hotel.impl.admin.room.RoomService;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
/**
 * command to view rooms on index_admin.jsp
 */
public class IndexRoomsAdminCommand implements Command {
    /**
     * Collect data rooms list for index_admin.jsp
     *
     * @param request  http request
     * @param response http response
     * @throws Exception maintenance exception
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String queryString = Optional.ofNullable(request.getQueryString())
                .map(Object::toString)
                .map(String::trim)
                .orElse("");
        List<Room> roomList = getRoomList();
        if (!roomList.isEmpty()) {
            request.setAttribute("roomList", roomList);
        } else {
            request.setAttribute("statusCommand", "No rooms found");
        }
        request.getSession().setAttribute("pageQuery", queryString);
        request.getSession().setAttribute("pageCommand", "secured-admin-rooms.command");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/admin/rooms_admin.jsp");
        requestDispatcher.forward(request, response);
    }
    private List<Room> getRoomList() {
        RoomRepository roomRepository = new RoomRepositorySQLImpl();
        RoomService roomService = new RoomService(roomRepository);
        return roomService.getRoomList("", Arrays.asList());
    }
}
