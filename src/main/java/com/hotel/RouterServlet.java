package com.hotel;

import com.hotel.security.RequiresRole;
import com.hotel.utils.enums.UserRolesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.hotel.security.commands.IndexPageCommand;
import com.hotel.security.commands.LoginPageCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

@WebServlet({"*.command"})
public class RouterServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(RouterServlet.class);

    static Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("/login.command", new LoginPageCommand());
        //commands.put("/register.command", new RegisterCommand());
       // commands.put("/register-user-page.command", new RegisterUserPageCommand());
       //commands.put("/authenticate.command", new AuthenticateCommand());
        //commands.put("/secured.command", new SecuredUserPageCommand());
       // commands.put("/logout.command", new LogoutCommand());
        commands.put("/index.command", new IndexPageCommand());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String commandName = request.getRequestURI().substring(request.getContextPath().length());

        if (commandName.equals("/") || commandName.isEmpty()) {
            commandName = "/index.command";
        }

        log.debug("Processing command: {}", commandName);

        if (commands.containsKey(commandName)) {
            Command command = commands.get(commandName);

            //Pre-authorization check: checking user role vs required role for command
            Annotation[] annotations = command.getClass().getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof RequiresRole) {
                    UserRolesEnum requiredRole = ((RequiresRole) annotation).value();
                    String existingRoleString = (String) request.getSession().getAttribute("role");
                    UserRolesEnum existingRole = UserRolesEnum.valueOf(existingRoleString.toUpperCase());

                    if (requiredRole != existingRole) {
                        throw new RuntimeException("403 Forbidden");
                    }
                }
            }

            try {
                command.execute(request, response);
            } catch (Exception e) {
                log.error("Error in com.hotel.RouterServlet commands", e );
                throw new ServletException("Failed to execute command", e);
            }
        } else {
            log.error("com.hotel.Command not found in com.hotel.RouterServlet commands");
            throw new ServletException("com.hotel.Command not found");
        }
    }

}
