package com.hotel.impl.user.commands;

import com.hotel.Command;
import com.hotel.impl.user.UserRepository;
import com.hotel.impl.user.UserRepositorySQLImpl;
import com.hotel.impl.user.UserService;
import com.hotel.security.RequiresRole;
import com.hotel.utils.DataPatternValidation;
import com.hotel.utils.enums.UserRolesEnum;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * command to delete user by ID
 * The password does not change
 */
@RequiresRole(UserRolesEnum.USER)
public class UserDeleteUserCommand implements Command {
    private static final Logger log = LogManager.getLogger(UserDeleteUserCommand.class);

    /**
     * delete user by ID
     *
     * @param request  http request
     * @param response http response
     * @throws Exception maintenance exception
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getSession().getAttribute("userId").toString();
        if (DataPatternValidation.intCheck(userId)) {
            if (deleteUser(Integer.parseInt(userId))) {
                request.setAttribute("statusCommand", "User was deleted");
                log.log(Level.DEBUG, String.format("User  with id '%s' was deleted by user", userId));
            } else {
                request.setAttribute("errorCommand", "User delete failed");
                log.log(Level.DEBUG, String.format("User  with id '%s' delete failed", userId));

            }
        } else {
            request.setAttribute("errorCommand", "Data validation failed");
            log.log(Level.DEBUG, String.format("User  with id '%s' Data validation failed", userId));
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.command");
        requestDispatcher.forward(request, response);
    }

    private boolean deleteUser(int userId) {
        UserRepository userRepository = new UserRepositorySQLImpl();
        UserService userService = new UserService(userRepository);
        return userService.deleteUser(userId);
    }
}
