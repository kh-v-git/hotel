package com.hotel.impl.user.commands;

import com.hotel.Command;
import com.hotel.impl.room.Room;
import com.hotel.impl.room.RoomRepository;
import com.hotel.impl.room.RoomRepositorySQLImpl;
import com.hotel.impl.room.RoomService;
import com.hotel.impl.request.Request;
import com.hotel.impl.request.RequestRepository;
import com.hotel.impl.request.RequestRepositorySQLImpl;
import com.hotel.impl.request.RequestService;
import com.hotel.model.RoomView;
import com.hotel.security.RequiresRole;
import com.hotel.utils.enums.UserRolesEnum;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * command to view index user page
 */
@RequiresRole(UserRolesEnum.USER)
public class IndexUserPageCommand implements Command {
    private static final Logger log = LogManager.getLogger(IndexUserPageCommand.class);

    /**
     * Collect data for index_user.jsp
     *
     * @param request  http request
     * @param response http response
     * @throws Exception maintenance exception
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<RoomView> roomViewList = getRoomViewList();
        List<String> roomBedSizeList = new ArrayList<>(roomViewList.stream().map(RoomView::getBedSize).collect(Collectors.toList()));
        int maxAdultsCapacity = roomViewList.stream().mapToInt(RoomView::getMaxAdults).max().orElse(0);
        int maxChildrenCapacity = roomViewList.stream().mapToInt(RoomView::getMaxChildren).max().orElse(0);
        if (!roomViewList.isEmpty()) {
            request.getSession().setAttribute("roomBedSizeList", roomBedSizeList);
            request.getSession().setAttribute("maxAdults", maxAdultsCapacity);
            request.getSession().setAttribute("maxChildren", maxChildrenCapacity);
        } else {
            request.setAttribute("errorPage", "Hotel has no rooms");
            log.log(Level.ERROR, "No room found in hotel");
        }

        int userId = (Integer) request.getSession().getAttribute("userId");
        List<Request> userRequestList = getUserRequestList(userId);
        Collections.reverse(userRequestList);
        if (!userRequestList.isEmpty()) {
            request.setAttribute("requestList", userRequestList);
        } else {
            request.setAttribute("statusCommand", "User has no requests");
        }

        pageLocale(request, "secured-user.command");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/user/index_user.jsp");
        requestDispatcher.forward(request, response);
    }

    private List<RoomView> getRoomViewList() {
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

    private List<Request> getUserRequestList(int userId) {
        RequestRepository requestRepository = new RequestRepositorySQLImpl();
        RequestService requestService = new RequestService(requestRepository);
        return requestService.getRequestList(userId);
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
