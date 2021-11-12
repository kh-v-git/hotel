package com.hotel.impl.user.commands;

import com.hotel.Command;
import com.hotel.impl.user.User;
import com.hotel.impl.user.UserRepository;
import com.hotel.impl.user.UserRepositorySQLImpl;
import com.hotel.impl.user.UserService;
import com.hotel.security.RequiresRole;
import com.hotel.utils.DataPatternValidation;
import com.hotel.utils.enums.UserRolesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * command to update user by ID
 * The password does not change
 */
@RequiresRole(UserRolesEnum.USER)
public class UserUpdateUserCommand implements Command {
    private static final Logger log = LogManager.getLogger(UserUpdateUserCommand.class);
    /**
     * Update user info by ID
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
        response.sendRedirect("about-user-page.command");
    }

    private boolean userDataValidation(HttpServletRequest req) {
        String userId = req.getSession().getAttribute("userId").toString();
        if (!DataPatternValidation.intCheck(userId)) {
            req.setAttribute("errorCommand", "User id not valid");
            return false;
        }

        String userFirstName = req.getParameter("user-first-name".trim());
        if (!DataPatternValidation.stringCheck(userFirstName)) {
            req.setAttribute("errorCommand", "First Name not valid");
            return false;
        }
        String userLastName = req.getParameter("user-last-name".trim());
        if (!DataPatternValidation.stringCheck(userLastName)) {
            req.setAttribute("errorCommand", "Last Name not valid");
            return false;
        }
        String userPhone = req.getParameter("user-phone".trim());
        if (!DataPatternValidation.phoneCheck(userPhone)) {
            req.setAttribute("errorCommand", "Phone number not valid");
            return false;
        }
        String userEmail = req.getParameter("user-email".trim());
        if (!DataPatternValidation.emailCheck(userEmail)) {
            req.setAttribute("errorCommand", "Email not valid");
            return false;
        }

        String userAbout = req.getParameter("user-about".trim());
        if (!DataPatternValidation.stringCheck(userAbout)) {
            req.setAttribute("errorCommand", "About not valid");
            return false;
        }
        return true;
    }

    private boolean userUpdate(HttpServletRequest req) {
        UserRepository userRepository = new UserRepositorySQLImpl();
        UserService userService = new UserService(userRepository);
        User newUser = new User();
        newUser.setUserID( (Integer) (req.getSession().getAttribute("userId")));
        newUser.setEmail(req.getParameter("user-email".trim()));
        newUser.setFirstName(req.getParameter("user-first-name".trim()));
        newUser.setLastName(req.getParameter("user-last-name".trim()));
        newUser.setPhone(req.getParameter("user-phone".trim()));
        newUser.setRole("user");
        newUser.setStatus("active");
        newUser.setAbout(req.getParameter("user-about".trim()));
        return userService.updateUser(newUser);
    }
}
