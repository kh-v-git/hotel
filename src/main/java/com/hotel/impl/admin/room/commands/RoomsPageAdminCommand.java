package com.hotel.impl.admin.room.commands;

import com.hotel.Command;
import com.hotel.impl.room.Room;
import com.hotel.impl.room.RoomRepository;
import com.hotel.impl.room.RoomRepositorySQLImpl;
import com.hotel.impl.room.RoomService;
import com.hotel.security.RequiresRole;
import com.hotel.utils.enums.UserRolesEnum;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * command to view rooms on index_admin.jsp
 */
@RequiresRole(UserRolesEnum.ADMIN)
public class RoomsPageAdminCommand implements Command {
    /**
     * Collect data rooms list for index_admin.jsp
     *
     * @param request  http request
     * @param response http response
     * @throws Exception maintenance exception
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String searchText = Optional.ofNullable(request.getParameter("search-number"))
                .map(Object::toString)
                .map(String::trim)
                .orElse("");
        request.setAttribute("searchNumber", searchText);

        int currentPage = Optional.ofNullable(request.getParameter("page"))
                .map(Object::toString)
                .map(Integer::parseInt)
                .orElse(1);

        int recordsPerPage = 5;
        RoomRepository roomRepository = new RoomRepositorySQLImpl();
        RoomService roomService = new RoomService(roomRepository);
        List<Room> roomList = roomService.getRoomList(searchText, Arrays.asList("number"), (currentPage - 1) * recordsPerPage, recordsPerPage);
        int numOfRooms = roomService.getRoomAmount(searchText, Arrays.asList("number"));

        int numOfPages = numOfRooms / recordsPerPage;
        if (numOfRooms % recordsPerPage > 0) {
            numOfPages++;
        }

        request.setAttribute("numOfPages", numOfPages);
        request.setAttribute("currentPage", currentPage);

        if (!roomList.isEmpty()) {
            request.setAttribute("roomList", roomList);
        } else {
            request.setAttribute("statusCommand", "No rooms found");
        }


        pageLocale(request, "secured-admin-rooms.command");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/admin/rooms_admin.jsp");
        requestDispatcher.forward(request, response);
    }

    private List<Room> getRoomList(String searchText, List<String> paramList, int start, int end) {
        RoomRepository roomRepository = new RoomRepositorySQLImpl();
        RoomService roomService = new RoomService(roomRepository);
        return roomService.getRoomList(searchText, paramList, start, end);
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
