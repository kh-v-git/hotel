package com.hotel.impl.admin.room.commands;

import com.hotel.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RoomsDownloadPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/admin/rooms_image_page.jsp");
        requestDispatcher.forward(request, response);
    }
}
