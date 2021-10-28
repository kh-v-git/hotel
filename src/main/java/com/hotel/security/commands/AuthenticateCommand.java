package com.hotel.security.commands;

import com.hotel.Command;
import com.hotel.exception.HashPasswordException;
import com.hotel.impl.admin.user.User;
import com.hotel.impl.admin.user.UserRepository;
import com.hotel.impl.admin.user.UserRepositorySQLImpl;
import com.hotel.impl.admin.user.UserService;
import com.hotel.utils.StringPatternValidation;
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
     * @param request http request
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
        request.setAttribute("error", "Wrong username or password");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(request, response);
    }

    private boolean authorizationCheck(HttpServletRequest authRequest) {
        UserRepository userRepository = new UserRepositorySQLImpl();
        UserService userService = new UserService(userRepository);

        String email = dataCheck(authRequest, "userEmail");
        String password = dataCheck(authRequest, "userPassword");

        if (email.isEmpty() || password.isEmpty()) {
            return false;
        }
        if (!StringPatternValidation.checkInputData(email, "email")) {
            return false;
        }

        if (!StringPatternValidation.checkInputData(password, "password")) {
            return false;
        }

        Optional<User> authUserOptional = userService.authUser(email);
        if (!authUserOptional.isPresent()) {
            return false;
        }
        String hashPassword;
        try {
            hashPassword = PasswordImpl.hashPassword(password);
            User authUser = authUserOptional.get();
            if (email.equals(authUser.getEmail()) && hashPassword.equals(authUser.getPassword())) {
                authRequest.getSession().setAttribute("userRole", authUser.getRole());
                authRequest.getSession().setAttribute("userStatus", authUser.getStatus());
                authRequest.getSession().setAttribute("userEmail", authUser.getEmail());
                authRequest.getSession().setAttribute("userId", authUser.getUserID());
                return true;
            }
        } catch (HashPasswordException ex) {
            log.log(Level.ERROR, "Auth user hashing password error", ex);
            return false;
        }
        return false;
    }

    private String dataCheck(HttpServletRequest request, String inputParam) {
        return Optional.ofNullable(request.getParameter(inputParam))
                .map(Object::toString)
                .map(String::trim)
                .orElse("");
    }
}
