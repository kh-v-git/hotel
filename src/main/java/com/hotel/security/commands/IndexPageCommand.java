package com.hotel.security.commands;

import com.hotel.Command;
import com.hotel.impl.admin.room.Room;
import com.hotel.impl.admin.room.RoomRepository;
import com.hotel.impl.admin.room.RoomRepositorySQLImpl;
import com.hotel.impl.admin.room.RoomService;
import com.hotel.model.RoomView;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * command to view index page
 */
public class IndexPageCommand implements Command {
    private static final Logger log = LogManager.getLogger(IndexPageCommand.class);

    /**
     * Collect data for index.jsp
     *
     * @param request  http request
     * @param response http response
     * @throws Exception servlet exception
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<RoomView> roomViewList = mainActivityProcess();

        if (!roomViewList.isEmpty()) {
            request.setAttribute("roomViewList", roomViewList);
        } else {
            request.setAttribute("errorPage", "Hotel has no rooms");
            log.log(Level.ERROR, "No room found in hotel");
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }

    private List<RoomView> mainActivityProcess() {
        RoomRepository roomRepository = new RoomRepositorySQLImpl();
        RoomService roomService = new RoomService(roomRepository);

        List<Room> roomList = roomService.getRoomList();
        Map<String, RoomView> roomViews = new HashMap<>();

        for (Room room : roomList) {
            String bedSize = room.getBedSize();
            RoomView roomView = roomViews.get(bedSize);
            double price = room.getPrice();
            if (roomView == null) {
                roomViews.put(bedSize, new RoomView(bedSize, price, price));
                continue;
            }
            if (roomView.getMaxPrice() < price) {
                roomView.setMaxPrice(price);
                continue;
            }
            if (roomView.getMinPrice() > price) {
                roomView.setMinPrice(price);
            }
        }
        List<RoomView> roomViewList = new ArrayList<>(roomViews.values());

        return roomViewList;
    }
}
