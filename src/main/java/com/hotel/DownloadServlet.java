package com.hotel;

import com.hotel.impl.room.RoomRepository;
import com.hotel.impl.room.RoomRepositorySQLImpl;
import com.hotel.impl.room.RoomService;
import com.hotel.model.RoomImage;
import com.hotel.utils.DataPatternValidation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet({"/downloadImage"})
@MultipartConfig
public class DownloadServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(DownloadServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (roomDataValidation(request)) {
            String jpgCaption = request.getParameter("image-caption");
            int roomID = Integer.parseInt(request.getParameter("room-id"));
            Optional<RoomImage> maybeImage = roomImageDownloadByCaption(roomID, jpgCaption);

            if (maybeImage.isPresent()) {
                RoomImage image = maybeImage.get();
                response.setContentType(image.getMime());
                Blob blob = image.getImage();
                try {
                    ServletOutputStream servletOut = response.getOutputStream();
                    int length = (int) blob.length();
                    response.setContentLength(length);
                    InputStream inputStream = blob.getBinaryStream();
                    int bufferSize = 1024;
                    byte[] buffer = new byte[bufferSize];
                    while ((length = inputStream.read(buffer)) != -1) {
                        servletOut.write(buffer, 0, length);
                    }
                    inputStream.close();
                    servletOut.flush();
                    request.setAttribute("roomImageId", image.getRoomImageID());
                } catch (SQLException | IOException | NumberFormatException ex) {
                    log.log(Level.ERROR, "Image read from DB error", ex);
                    request.setAttribute("errorPage", "Room download failed");
                }

            } else {
                log.log(Level.DEBUG, "Room Image not found");
                request.setAttribute("errorPage", "Room add failed. DB error");
            }
        } else {
            log.log(Level.DEBUG, "Room image data validation error");
            request.setAttribute("errorPage", "Data validation failed");
        }
        super.doPost(request, response);
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

    private Optional<RoomImage> roomImageDownloadByCaption(int roomID, String jpgCaption) {
        RoomRepository roomRepository = new RoomRepositorySQLImpl();
        RoomService roomService = new RoomService(roomRepository);
        return roomService.getRoomImageByCaption(roomID, jpgCaption);
    }
}
