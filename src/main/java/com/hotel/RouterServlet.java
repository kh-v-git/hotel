package com.hotel;

import com.hotel.impl.admin.room.commands.*;
import com.hotel.impl.admin.user.commands.*;
import com.hotel.impl.common.LocaleCommand;
import com.hotel.impl.manager.commands.IndexManagerPageCommand;
import com.hotel.impl.order.commands.BookingRoomUserPageCommand;
import com.hotel.impl.request.command.ChangeRequestManagerCommand;
import com.hotel.impl.request.command.RequestRoomDeleteUserCommand;
import com.hotel.impl.request.command.RequestRoomUserCommand;
import com.hotel.impl.room.command.RoomGuestPageCommand;
import com.hotel.impl.room.command.RoomCategoryGuestPageCommand;
import com.hotel.impl.user.commands.AboutUserPageCommand;
import com.hotel.impl.user.commands.IndexUserPageCommand;
import com.hotel.impl.user.commands.UserDeleteUserCommand;
import com.hotel.impl.user.commands.UserUpdateUserCommand;
import com.hotel.security.RequiresRole;
import com.hotel.security.commands.*;
import com.hotel.utils.enums.UserRolesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * Router Servlet
 */
@WebServlet({"*.command"})
public class RouterServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(RouterServlet.class);

    static Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("/locale.command", new LocaleCommand());
        commands.put("/index.command", new IndexPageCommand());
        commands.put("/login.command", new LoginPageCommand());
        commands.put("/logout.command", new LogoutCommand());
        commands.put("/register.command", new RegisterCommand());
        commands.put("/register-user-page.command", new RegisterUserPageCommand());
        commands.put("/authenticate.command", new AuthenticateCommand());
        commands.put("/rooms-view.command", new RoomCategoryGuestPageCommand());
        commands.put("/room.command", new RoomGuestPageCommand());
        commands.put("/secured-admin.command", new IndexAdminPageCommand());
        commands.put("/admin-user-edit.command", new UserEditAdminPageCommand());
        commands.put("/user-admin-update.command", new UserUpdateAdminCommand());
        commands.put("/user-admin-delete.command", new UserDeleteAdminCommand());
        commands.put("/secured-admin-rooms.command", new RoomsPageAdminCommand());
        commands.put("/room-edit-page-admin.command", new RoomEditAdminPageCommand());
        commands.put("/room-delete-admin.command", new RoomDeleteAdminCommand());
        commands.put("/room-update-admin.command", new RoomUpdateAdminCommand());
        commands.put("/room-add-new-page-admin.command", new RoomAddNewAdminPageCommand());
        commands.put("/room-add-new-admin.command", new RoomAddNewAdminCommand());
        commands.put("/room-image-edit-page-admin.command", new RoomImageEditPageAdminCommand());
        commands.put("/room-upload-page-admin.command", new RoomsUploadPageCommand());
        commands.put("/room-download-page-admin.command", new RoomsDownloadPageCommand());
        commands.put("/room-image-delete-admin.command", new RoomImageDeleteAdminCommand());
        commands.put("/secured-manager.command", new IndexManagerPageCommand());
        commands.put("/change-request-status-manager.command", new ChangeRequestManagerCommand());


        commands.put("/secured-user.command", new IndexUserPageCommand());
        commands.put("/request-room-user.command", new RequestRoomUserCommand());
        commands.put("/request-room-remove-user.command", new RequestRoomDeleteUserCommand());
        commands.put("/about-user-page.command", new AboutUserPageCommand());
        commands.put("/user-update-user.command", new UserUpdateUserCommand());
        commands.put("/user-delete-user.command", new UserDeleteUserCommand());
        commands.put("/booking-page-user.command", new BookingRoomUserPageCommand());
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
                    String existingRoleString = (String) request.getSession().getAttribute("userRole");
                    UserRolesEnum existingRole = UserRolesEnum.valueOf(existingRoleString.toUpperCase());

                    if (requiredRole != existingRole) {
                        throw new RuntimeException("403 Forbidden");
                    }
                }
            }
            try {
                command.execute(request, response);
            } catch (Exception e) {
                log.error("Error in com.hotel.RouterServlet commands", e);
                throw new ServletException("Failed to execute command", e);
            }
        } else {
            log.error("com.hotel.Command not found in com.hotel.RouterServlet commands");
            throw new ServletException("com.hotel.Command not found");
        }
    }
}
