package com.hotel.impl.admin.user.commands;

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
 *
 */
@RequiresRole(UserRolesEnum.ADMIN)
public class UserDeleteAdminCommand implements Command {
    private static final Logger log = LogManager.getLogger(UserDeleteAdminCommand.class);
    /**
     * delete user by ID
     *
     * @param request  http request
     * @param response http response
     * @throws Exception maintenance exception
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("user-id");
        if (DataPatternValidation.intCheck(userId)) {
            if (deleteUser(Integer.parseInt(userId))) {
                request.setAttribute("statusCommand", "User was deleted");
                log.log(Level.DEBUG, String.format("User  with id '%d' was deleted by admin", userId));
            } else {
                request.setAttribute("errorCommand", "User delete failed");
            }
        } else {
            request.setAttribute("errorCommand", "Data validation failed");
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("secured-admin.command");
        requestDispatcher.forward(request, response);
    }
    private boolean deleteUser(int userId) {
        UserRepository userRepository = new UserRepositorySQLImpl();
        UserService userService = new UserService(userRepository);
        return userService.deleteUser(userId);
    }
}
