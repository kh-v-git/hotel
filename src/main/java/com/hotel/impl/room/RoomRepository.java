package com.hotel.impl.room;

import com.hotel.model.RoomImage;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    List<Room> getRoomList(String searchText, List<String> searchParametersList);
    List<Room> getRoomList(String searchText, List<String> searchParametersList, int position, int perPage);

    int getRoomAmount();
    int getRoomAmount(String searchText, List<String> searchParametersList);

    boolean setRoom(Room room);

    Optional<Room> getRoom(int id);

    boolean deleteRoom(int id);

    boolean updateRoom(Room room);

    boolean uploadNewRoomImage(int roomId, String caption, String mimeType, InputStream roomImage);

    boolean uploadRoomImage(int imageId, int roomId, String caption, String mimeType, InputStream roomImage);

    List<RoomImage> getRoomImageList(int roomId);

    boolean deleteRoomImage(int roomImageID);

    Optional<RoomImage> getRoomImageByCaption(int roomId, String roomImageCaption);
}
