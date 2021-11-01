package com.hotel.security.commands;

import com.hotel.Command;
import com.hotel.exception.HashPasswordException;
import com.hotel.impl.admin.user.User;
import com.hotel.impl.admin.user.UserRepository;
import com.hotel.impl.admin.user.UserRepositorySQLImpl;
import com.hotel.impl.admin.user.UserService;
import com.hotel.utils.DataPatternValidation;
import com.hotel.utils.passwordhash.PasswordImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * User authenticate command. Set user attributes to session.
 */
public class AuthenticateCommand implements Command {
    private static final Logger log = LogManager.getLogger(AuthenticateCommand.class);

    /**
     * Redirect auth user to proper page
     *
     * @param request  http request
     * @param response http response
     * @throws Exception method exception
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (authorizationCheck(request)) {
            if (request.getSession().getAttribute("userRole").equals("user")) {
                response.sendRedirect("secured-user.command");
                return;
            }
            if (request.getSession().getAttribute("userRole").equals("manager")) {
                response.sendRedirect("secured-manager.command");
                return;
            }
            if (request.getSession().getAttribute("userRole").equals("admin")) {
                response.sendRedirect("secured-admin.command");
                return;
            }
        }
        request.setAttribute("errorPage", "Wrong username or password");
        request.getSession().setAttribute("pageCommand", "login.command");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.command");
        requestDispatcher.forward(request, response);
    }

    private boolean authorizationCheck(HttpServletRequest authRequest) {
        String userEmail = authRequest.getParameter("user-email".trim());
        String userPass = authRequest.getParameter("user-password".trim());
        UserRepository userRepository = new UserRepositorySQLImpl();
        UserService userService = new UserService(userRepository);

        boolean emailCheck = DataPatternValidation.checkInputData(userEmail, "email");
        boolean passwordCheck = DataPatternValidation.checkInputData(userPass, "password");

        if (emailCheck && passwordCheck) {
            Optional<User> authUserOptional = userService.authUser(userEmail);
            if (!authUserOptional.isPresent()) {
                return false;
            }
            User authUser = authUserOptional.get();
            if ("active".equals(authUser.getStatus())) {
                String hashPassword;
                try {
                    hashPassword = PasswordImpl.hashPassword(userPass);
                    String test = hashPassword;
                    if (userEmail.equals(authUser.getEmail()) && hashPassword.equals(authUser.getPassword())) {
                        authRequest.getSession().setAttribute("userRole", authUser.getRole());
                        authRequest.getSession().setAttribute("userStatus", authUser.getStatus());
                        authRequest.getSession().setAttribute("userEmail", authUser.getEmail());
                        authRequest.getSession().setAttribute("userId", authUser.getUserID());
                        return true;
                    }
                } catch (HashPasswordException ex) {
                    log.log(Level.ERROR, "Auth user hashing password error", ex);
                    authRequest.setAttribute("errorCommand", "System internal error");
                }
            } else {
                authRequest.setAttribute("errorCommand", "User is banned");
            }
        }
        return false;
    }
}
