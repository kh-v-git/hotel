package com.hotel.impl.admin.user.commands;

import com.hotel.Command;
import com.hotel.security.RequiresRole;
import com.hotel.utils.enums.UserRolesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RequiresRole(UserRolesEnum.ADMIN)
public class IndexAdminPageCommand implements Command {
    private static final Logger log = LogManager.getLogger(IndexAdminPageCommand.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String queryString = Optional.ofNullable(request.getQueryString())
                .map(Object::toString)
                .map(String::trim)
                .orElse("");


        request.getSession().setAttribute("pageQuery", queryString);
        request.getSession().setAttribute("pageCommand", "secured-admin.command");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/admin/index_admin.jsp");
        requestDispatcher.forward(request, response);
    }
}
