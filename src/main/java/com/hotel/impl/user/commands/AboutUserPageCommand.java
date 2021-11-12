package com.hotel.impl.user.commands;

import com.hotel.Command;
import com.hotel.impl.user.User;
import com.hotel.impl.user.UserRepository;
import com.hotel.impl.user.UserRepositorySQLImpl;
import com.hotel.impl.user.UserService;
import com.hotel.security.RequiresRole;
import com.hotel.utils.DataPatternValidation;
import com.hotel.utils.enums.UserRolesEnum;
import com.hotel.utils.enums.UserStatusEnum;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * command to view user_about.jsp
 */
@RequiresRole(UserRolesEnum.USER)
public class AboutUserPageCommand implements Command {
    private static final Logger log = LogManager.getLogger(AboutUserPageCommand.class);

    /**
     * Collect data for user_edit_admin.jsp
     *
     * @param request  http request
     * @param response http response
     * @throws Exception maintenance exception
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getSession().getAttribute("userId").toString();
        if (DataPatternValidation.intCheck(userId)) {
            Optional<User> maybeUser = getUser(Integer.parseInt(userId));
            if (maybeUser.isPresent()) {
                request.setAttribute("user", maybeUser.get());
                request.setAttribute("userRoleList", UserRolesEnum.getUserRoles());
                request.setAttribute("userStatusList", UserStatusEnum.getUserStatuses());
                pageLocale(request, "about-user-page.command");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/user/user_about.jsp");
                requestDispatcher.forward(request, response);

            } else {
                log.log(Level.DEBUG, "User by ID not found");
                request.setAttribute("errorPage", "User by ID not found");
                pageLocale(request, "secured-user.command");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("secured-user.command");
                requestDispatcher.forward(request, response);
            }

        } else {
            log.log(Level.DEBUG, "User data validation failed");
            request.setAttribute("errorPage", "Data validation failed");
            pageLocale(request, "secured-user.command");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("secured-user.command");
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
            servletCommand = "secured-user.command";
        }
        localeRequest.getSession().setAttribute("pageQuery", queryString);
        localeRequest.getSession().setAttribute("pageCommand", servletCommand);
    }

}
