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

@RequiresRole(UserRolesEnum.USER)
public class IndexUserPageCommand implements Command {
    private static final Logger log = LogManager.getLogger(IndexUserPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String queryString = Optional.ofNullable(request.getQueryString())
                .map(Object::toString)
                .map(String::trim)
                .orElse("");


        request.getSession().setAttribute("pageQuery", queryString);
        request.getSession().setAttribute("pageCommand", "secured-user.command");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/user/index_user.jsp");
        requestDispatcher.forward(request, response);
    }
}
