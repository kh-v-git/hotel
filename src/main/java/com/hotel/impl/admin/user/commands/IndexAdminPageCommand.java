package com.hotel.impl.admin.user.commands;

import com.hotel.Command;
import com.hotel.impl.admin.user.User;
import com.hotel.impl.admin.user.UserRepository;
import com.hotel.impl.admin.user.UserRepositorySQLImpl;
import com.hotel.impl.admin.user.UserService;
import com.hotel.security.RequiresRole;
import com.hotel.utils.enums.UserRolesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * command to view index_admin.jsp
 */
@RequiresRole(UserRolesEnum.ADMIN)
public class IndexAdminPageCommand implements Command {
    private static final Logger log = LogManager.getLogger(IndexAdminPageCommand.class);
    /**
     * Collect data for index_admin.jsp
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

        List<User> userList = getUserList();
        if (!userList.isEmpty()) {
            request.setAttribute("userList", userList);
        } else {
            request.setAttribute("statusCommand", "No users found");
        }
        request.getSession().setAttribute("pageQuery", queryString);
        request.getSession().setAttribute("pageCommand", "secured-admin.command");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/admin/index_admin.jsp");
        requestDispatcher.forward(request, response);
    }

    private List<User> getUserList() {
        UserRepository userRepository = new UserRepositorySQLImpl();
        UserService userService = new UserService(userRepository);
        return userService.getUserList("", Arrays.asList());
    }
}
