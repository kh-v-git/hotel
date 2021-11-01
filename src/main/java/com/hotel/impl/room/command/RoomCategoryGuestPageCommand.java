package com.hotel.impl.room.command;

import com.hotel.Command;
import com.hotel.impl.admin.room.Room;
import com.hotel.impl.admin.room.RoomRepository;
import com.hotel.impl.admin.room.RoomRepositorySQLImpl;
import com.hotel.impl.admin.room.RoomService;
import com.hotel.utils.DataPatternValidation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * command to view page with rooms sorted by basSize
 */
public class RoomCategoryGuestPageCommand implements Command {
    private static final Logger log = LogManager.getLogger(RoomCategoryGuestPageCommand.class);
    /**
     * Collect data customized by bed-size param for rooms.jsp
     *
     * @param request  http request
     * @param response http response
     * @throws Exception maintenance exception
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String bedSizeParam = request.getParameter("bed-size");
        String queryString = Optional.ofNullable(request.getQueryString())
                .map(Object::toString)
                .map(String::trim)
                .orElse("");

        List<Room> roomList = mainActivityProcess(bedSizeParam);
        int maxAdultsCapacity = roomList.stream().mapToInt(Room::getAdultCapacity).max().orElse(1);
        int maxChildrenCapacity = roomList.stream().mapToInt(Room::getChildrenCapacity).max().orElse(0);
        if (!roomList.isEmpty()) {
            request.setAttribute("roomSortedList", roomList);
            request.setAttribute("bedCategory", bedSizeParam);
            request.setAttribute("maxAdultsCategory", maxAdultsCapacity);
            request.setAttribute("maxChildrenCategory", maxChildrenCapacity);
        } else {
            request.setAttribute("errorPage", "Hotel has no rooms");
            log.log(Level.ERROR, "No room found in hotel");
        }
        request.getSession().setAttribute("pageQuery", queryString);
        request.getSession().setAttribute("pageCommand", "rooms-view.command");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/main/rooms.jsp");
        requestDispatcher.forward(request, response);
    }

    private List<Room> mainActivityProcess(String searchParam) {
        List<Room> roomList = new ArrayList<>();
        if (DataPatternValidation.checkInputData(searchParam, "string")) {
            RoomRepository roomRepository = new RoomRepositorySQLImpl();
            RoomService roomService = new RoomService(roomRepository);
            roomList = roomService.getRoomList(searchParam, new ArrayList<>(Arrays.asList("bed_size")));
        }
        return roomList;
    }
}
