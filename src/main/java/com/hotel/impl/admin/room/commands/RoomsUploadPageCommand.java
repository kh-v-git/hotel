package com.hotel.impl.admin.room.commands;

import com.hotel.Command;
import com.hotel.security.RequiresRole;
import com.hotel.utils.enums.UserRolesEnum;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * command to view rooms on index_admin.jsp
 */
@RequiresRole(UserRolesEnum.ADMIN)
public class RoomsUploadPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/admin/rooms_upload.jsp");
        requestDispatcher.forward(request, response);
    }
}
