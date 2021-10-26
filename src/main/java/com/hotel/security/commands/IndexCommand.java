package com.hotel.security.commands;

import com.hotel.Command;
import com.hotel.impl.admin.room.Room;
import com.hotel.impl.admin.room.RoomRepository;
import com.hotel.impl.admin.room.RoomRepositorySQLImpl;
import com.hotel.impl.admin.room.RoomService;
import model.RoomView;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class IndexCommand implements Command {
    private static final Logger log = LogManager.getLogger(IndexCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }

    private boolean mainActivityProcess(HttpServletRequest processRequest) {
        RoomRepository roomRepository = new RoomRepositorySQLImpl();
        RoomService roomService = new RoomService(roomRepository);
        List<RoomView> roomViewList = new ArrayList<>();

        List<Room> roomList = roomService.getRoomList();
        return false;
    }
}
