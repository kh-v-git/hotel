package com.hotel.security.commands;

import com.hotel.Command;
import com.hotel.impl.room.Room;
import com.hotel.impl.room.RoomRepository;
import com.hotel.impl.room.RoomRepositorySQLImpl;
import com.hotel.impl.room.RoomService;
import com.hotel.model.RoomView;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

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
     * @throws Exception maintenance exception
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String queryString = Optional.ofNullable(request.getQueryString())
                .map(Object::toString)
                .map(String::trim)
                .orElse("");

        List<RoomView> roomViewList = mainActivityProcess();
        List<String> roomBedSizeList = new ArrayList<>(roomViewList.stream().map(RoomView::getBedSize).collect(Collectors.toList()));
        int maxAdultsCapacity = roomViewList.stream().mapToInt(RoomView::getMaxAdults).max().orElse(0);
        int maxChildrenCapacity = roomViewList.stream().mapToInt(RoomView::getMaxChildren).max().orElse(0);
        if (!roomViewList.isEmpty()) {
            request.setAttribute("roomViewList", roomViewList);
            request.getSession().setAttribute("roomBedSizeList", roomBedSizeList);
            request.getSession().setAttribute("maxAdults", maxAdultsCapacity);
            request.getSession().setAttribute("maxChildren", maxChildrenCapacity);
        } else {
            request.setAttribute("errorPage", "Hotel has no rooms");
            log.log(Level.ERROR, "No room found in hotel");
        }

        request.getSession().setAttribute("pageQuery", queryString);
        request.getSession().setAttribute("pageCommand", "index.command");
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
            int adultCapacity = room.getAdultCapacity();
            int childrenCapacity = room.getChildrenCapacity();
            if (roomView == null) {
                roomViews.put(bedSize, new RoomView(bedSize, price, price, 0, 1, 0, 0));
                continue;
            }
            if (roomView.getMaxPrice() < price) {
                roomView.setMaxPrice(price);
            }
            if (roomView.getMinPrice() > price) {
                roomView.setMinPrice(price);
            }
            if (roomView.getMaxAdults() < adultCapacity) {
                roomView.setMaxAdults(adultCapacity);
            }
            if (roomView.getMinAdults() > adultCapacity) {
                roomView.setMinAdults(adultCapacity);
            }
            if (roomView.getMaxChildren() < childrenCapacity) {
                roomView.setMaxChildren(childrenCapacity);
            }
            if (roomView.getMinChildren() > childrenCapacity) {
                roomView.setMinChildren(childrenCapacity);
            }
        }
        List<RoomView> roomViewList = new ArrayList<>(roomViews.values());

        return roomViewList;
    }
}
