package com.hotel.security.commands;

import com.hotel.Command;
import com.hotel.exception.HashPasswordException;
import com.hotel.impl.admin.user.User;
import com.hotel.impl.admin.user.UserRepository;
import com.hotel.impl.admin.user.UserRepositorySQLImpl;
import com.hotel.impl.admin.user.UserService;
import com.hotel.utils.DataPatternValidation;
import com.hotel.utils.passwordhash.PasswordHashService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * command to register new user
 */
public class RegisterCommand implements Command {
    private static final Logger log = LogManager.getLogger(RegisterCommand.class);

    /**
     * Create new user. Redirects to login.command
     *
     * @param request  http request
     * @param response http response
     * @throws Exception maintenance exception
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (userDataValidation(request)) {
            if (userExistCheck(request)) {
                request.setAttribute("statusCommand", "User exists. Please login.");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.command");
                requestDispatcher.forward(request, response);
                log.log(Level.DEBUG, "Exist user try to register");
                return;
            }
            if (newUserRegistration(request)) {
                request.setAttribute("statusCommand", "User registered. Please login");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.command");
                requestDispatcher.forward(request, response);
                return;
            }
        } else {
            request.setAttribute("errorCommand", "User not registered. Try again.");
            log.log(Level.DEBUG, "User data validation failed");
        }
        pageLocale(request, "register-user-page.command");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("register-user-page.command");
        requestDispatcher.forward(request, response);
    }

    private boolean userDataValidation(HttpServletRequest req) {
        String userFirstName = req.getParameter("user-first-name".trim());
        if (DataPatternValidation.checkInputData(userFirstName, "string")) {
            req.setAttribute("userFirstName", userFirstName);
        } else {
            req.setAttribute("errorCommand", "First Name not valid");
            return false;
        }

        String userLastName = req.getParameter("user-last-name".trim());
        if (DataPatternValidation.checkInputData(userLastName, "string")) {
            req.setAttribute("userLastName", userLastName);
        } else {
            req.setAttribute("errorCommand", "Last Name not valid");
            return false;
        }
        String userPhone = req.getParameter("user-phone".trim());
        if (DataPatternValidation.checkInputData(userPhone, "phone")) {
            req.setAttribute("userPhone", userLastName);
        } else {
            req.setAttribute("errorCommand", "Phone number not valid");
            return false;
        }
        String userEmail = req.getParameter("user-email".trim());
        if (DataPatternValidation.checkInputData(userEmail, "email")) {
            req.setAttribute("userEmail", userLastName);
        } else {
            req.setAttribute("errorCommand", "Email not valid");
            return false;
        }
        String userPass = req.getParameter("user-password".trim());
        if (!DataPatternValidation.checkInputData(userPass, "password")) {
            req.setAttribute("errorCommand", "Password not valid");
            return false;
        }
        String userPassRepeat = req.getParameter("user-password-repeat".trim());
        if (!DataPatternValidation.checkInputData(userPassRepeat, "password")) {
            req.setAttribute("errorCommand", "Password repeat not valid");
            return false;
        }
        if (!userPass.equals(userPassRepeat)) {
            req.setAttribute("errorCommand", "Password don`t match.");
            return false;
        }
        String userAbout = req.getParameter("user-about".trim());
        if (DataPatternValidation.checkInputData(userAbout, "string")) {
            req.setAttribute("userAbout", userAbout);
        } else {
            req.setAttribute("errorCommand", "About not valid");

            return false;
        }
        return true;
    }

    private boolean userExistCheck(HttpServletRequest authRequest) {
        UserRepository userRepository = new UserRepositorySQLImpl();
        UserService userService = new UserService(userRepository);

        String userEmail = authRequest.getParameter("user-email".trim());
        if (userEmail.isEmpty()) {
            return false;
        }

        Optional<User> authUserOptional = userService.authUser(userEmail);

        if (!authUserOptional.isPresent()) {
            return false;
        }
        User authUser = authUserOptional.get();

        if (userEmail.equals(authUser.getEmail()) && authUser.getUserID() > 0) {
            return true;
        }
        return false;
    }

    private boolean newUserRegistration(HttpServletRequest req) {
        UserRepository userRepository = new UserRepositorySQLImpl();
        UserService userService = new UserService(userRepository);
        User newUser = new User();
        newUser.setFirstName(req.getParameter("user-first-name".trim()));
        newUser.setLastName(req.getParameter("user-last-name".trim()));
        newUser.setPhone(req.getParameter("user-phone".trim()));
        newUser.setEmail(req.getParameter("user-email".trim()));
        String inputPass = req.getParameter("user-password".trim());
        String hasPass;
        try {
            hasPass = PasswordHashService.hashPassword(inputPass);
        } catch (HashPasswordException ex) {
            log.log(Level.ERROR, "New user hashing password error", ex);
            return false;
        }
        newUser.setPassword(hasPass);
        newUser.setStatus("active");
        newUser.setRole("user");
        newUser.setAbout(req.getParameter("user-about".trim()));
        return userService.setUser(newUser);
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
