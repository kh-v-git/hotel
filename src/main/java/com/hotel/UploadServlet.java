package com.hotel;

import com.hotel.impl.room.RoomRepository;
import com.hotel.impl.room.RoomRepositorySQLImpl;
import com.hotel.impl.room.RoomService;
import com.hotel.model.RoomImage;
import com.hotel.security.RequiresRole;
import com.hotel.utils.DataPatternValidation;
import com.hotel.utils.enums.UserRolesEnum;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@WebServlet({"/uploadImage"})
@RequiresRole(UserRolesEnum.ADMIN)
@MultipartConfig(maxFileSize = 16177215)
public class UploadServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(UploadServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (roomDataValidation(request)) {
            if (roomUploadToDB(request)) {
                request.setAttribute("statusCommand", "Image uploaded!");
            } else {
                request.setAttribute("statusCommand", "Upload failed!");
            }
        } else {
            log.log(Level.DEBUG, "Room image data validation error");
            request.setAttribute("errorPage", "Data validation failed");
        }
        int roomId = Integer.parseInt(request.getParameter("room-id"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("room-edit-page-admin.command?room-id=" + roomId);
        requestDispatcher.forward(request, response);
    }

    private boolean roomDataValidation(HttpServletRequest req) {
        String roomNumber = req.getParameter("room-id".trim());
        if (!DataPatternValidation.intCheck(roomNumber)) {
            req.setAttribute("errorCommand", "Room number not valid");
            log.log(Level.DEBUG, "Room number not valid");
            return false;
        }
        String imageCaption = req.getParameter("image-caption".trim());
        if (!DataPatternValidation.stringCheck(imageCaption)) {
            req.setAttribute("errorCommand", "Image Caption not valid");
            log.log(Level.DEBUG, "Image Caption not valid");
            return false;
        }
        return true;
    }

    private boolean roomUploadToDB(HttpServletRequest req) {
        RoomRepository roomRepository = new RoomRepositorySQLImpl();
        RoomService roomService = new RoomService(roomRepository);
        int roomId = Integer.parseInt(req.getParameter("room-id"));
        String fileCaption = req.getParameter("image-caption");
        try {
            Part filePart = req.getPart("file"); // Retrieves <input type="file" name="file">
            if (filePart != null) {
                InputStream inputStream = filePart.getInputStream();
                String mimeType = filePart.getContentType(); //get file type
                Optional<RoomImage> maybeImage = roomService.getRoomImageByCaption(roomId, fileCaption);

                if (maybeImage.isPresent()) {
                    RoomImage roomImage = maybeImage.get();
                    return roomService.uploadRoomImage(roomImage.getRoomImageID(), roomId, fileCaption, mimeType, inputStream);
                } else {
                    return roomService.uploadNewRoomImage(roomId, fileCaption, mimeType, inputStream);
                }
            } else {
                return false;
            }
        } catch (IOException | ServletException ex) {
            log.log(Level.ERROR, "Room Image download error");
            return false;
        }
    }
}

