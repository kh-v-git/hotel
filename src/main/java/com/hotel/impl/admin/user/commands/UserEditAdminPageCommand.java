package com.hotel.impl.admin.user.commands;

import com.hotel.Command;
import com.hotel.impl.admin.user.User;
import com.hotel.impl.admin.user.UserRepository;
import com.hotel.impl.admin.user.UserRepositorySQLImpl;
import com.hotel.impl.admin.user.UserService;
import com.hotel.security.RequiresRole;
import com.hotel.utils.DataPatternValidation;
import com.hotel.utils.enums.UserRolesEnum;
import com.hotel.utils.enums.UserStatusEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * command to view user_edit_admin.jsp
 */
@RequiresRole(UserRolesEnum.ADMIN)
public class UserEditAdminPageCommand implements Command {
    private static final Logger log = LogManager.getLogger(UserEditAdminPageCommand.class);

    /**
     * Collect data for user_edit_admin.jsp
     *
     * @param request  http request
     * @param response http response
     * @throws Exception maintenance exception
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("user-id");
        if (DataPatternValidation.IntCheck(userId)) {
            Optional<User> maybeUser = getUser(Integer.parseInt(userId));

            if (maybeUser.isPresent()) {
                request.setAttribute("user", maybeUser.get());
                request.setAttribute("userRoleList", UserRolesEnum.getUserRoles());
                request.setAttribute("userStatusList", UserStatusEnum.getUserStatuses());
                pageLocale(request, "user-edit-admin-page.command");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/admin/user_edit_admin.jsp");
                requestDispatcher.forward(request, response);

            } else {
                request.setAttribute("errorPage", "User by ID not found");
                pageLocale(request, "user-edit-admin-page.command");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/admin/user_edit_admin.jsp");
                requestDispatcher.forward(request, response);
            }

        } else {
            request.setAttribute("errorPage", "Data validation failed");
            pageLocale(request, "secured-admin.command");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/admin/index_admin.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    private Optional<User> getUser(int id) {
        UserRepository userRepository = new UserRepositorySQLImpl();
        UserService userService = new UserService(userRepository);
        return userService.getUser(id);
    }

    private void pageLocale(HttpServletRequest localeRequest, String servletCommand) {
        String queryString = Optional.ofNullable(localeRequest.getQueryString())
                .map(Object::toString)
                .map(String::trim)
                .orElse("");
        if (servletCommand.isEmpty()) {
            servletCommand = "index.command";
        }
        localeRequest.getSession().setAttribute("pageQuery", queryString);
        localeRequest.getSession().setAttribute("pageCommand", servletCommand);
    }

}
