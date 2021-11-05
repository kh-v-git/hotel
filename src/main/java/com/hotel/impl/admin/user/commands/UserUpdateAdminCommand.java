package com.hotel.impl.admin.user.commands;

import com.hotel.Command;
import com.hotel.impl.admin.user.User;
import com.hotel.impl.admin.user.UserRepository;
import com.hotel.impl.admin.user.UserRepositorySQLImpl;
import com.hotel.impl.admin.user.UserService;
import com.hotel.security.RequiresRole;
import com.hotel.utils.DataPatternValidation;
import com.hotel.utils.enums.UserRolesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * command to update user by ID
 * The password does not change
 */
@RequiresRole(UserRolesEnum.ADMIN)
public class UserUpdateAdminCommand implements Command {
    private static final Logger log = LogManager.getLogger(UserUpdateAdminCommand.class);

    /**
     * Delete user by ID
     *
     * @param request  http request
     * @param response http response
     * @throws Exception maintenance exception
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (userDataValidation(request)) {
            if (userUpdate(request)) {
                request.setAttribute("statusPage", "User update success");
            } else {
                request.setAttribute("errorPage", "User update failed. DB error.");
            }
        } else {
            request.setAttribute("errorPage", "User update failed. Data error.");
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("secured-admin.command");
        requestDispatcher.forward(request, response);

    }

    private boolean userDataValidation(HttpServletRequest req) {
        String userId = req.getParameter("user-id");
        if (!DataPatternValidation.IntCheck(userId)) {
            req.setAttribute("errorCommand", "User id not valid");
            return false;
        }

        String userFirstName = req.getParameter("user-first-name".trim());
        if (!DataPatternValidation.StringCheck(userFirstName)) {
            req.setAttribute("errorCommand", "First Name not valid");
            return false;
        }
        String userLastName = req.getParameter("user-last-name".trim());
        if (!DataPatternValidation.StringCheck(userLastName)) {
            req.setAttribute("errorCommand", "Last Name not valid");
            return false;
        }
        String userPhone = req.getParameter("user-phone".trim());
        if (!DataPatternValidation.PhoneCheck(userPhone)) {
            req.setAttribute("errorCommand", "Phone number not valid");
            return false;
        }
        String userEmail = req.getParameter("user-email".trim());
        if (!DataPatternValidation.EmailCheck(userEmail)) {
            req.setAttribute("errorCommand", "Email not valid");
            return false;
        }

        String userRole = req.getParameter("user-role".trim());
        if (!DataPatternValidation.StringCheck(userRole)) {
            req.setAttribute("errorCommand", "Role not valid");
            return false;
        }

        String userStatus = req.getParameter("user-status".trim());
        if (!DataPatternValidation.StringCheck(userStatus)) {
            req.setAttribute("errorCommand", "Status not valid");
            return false;
        }

        String userAbout = req.getParameter("user-about".trim());
        if (!DataPatternValidation.StringCheck(userAbout)) {
            req.setAttribute("errorCommand", "About not valid");
            return false;
        }
        return true;
    }

    private boolean userUpdate(HttpServletRequest req) {
        UserRepository userRepository = new UserRepositorySQLImpl();
        UserService userService = new UserService(userRepository);
        User newUser = new User();
        newUser.setUserID(Integer.parseInt(req.getParameter("user-id")));
        newUser.setEmail(req.getParameter("user-email".trim()));
        newUser.setFirstName(req.getParameter("user-first-name".trim()));
        newUser.setLastName(req.getParameter("user-last-name".trim()));
        newUser.setPhone(req.getParameter("user-phone".trim()));
        newUser.setRole(req.getParameter("user-role".trim()));
        newUser.setStatus(req.getParameter("user-status".trim()));
        newUser.setAbout(req.getParameter("user-about".trim()));
        return userService.updateUser(newUser);
    }
}
