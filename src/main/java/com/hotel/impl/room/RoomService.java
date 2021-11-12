package com.hotel.impl.room;


import com.hotel.model.RoomImage;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomService {
    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getRoomList(String searchText, List<String> searchParametersList) {
        return roomRepository.getRoomList(searchText, searchParametersList);
    }
    public List<Room> getRoomList(String searchText, List<String> searchParametersList, int start, int perPage) {
        return roomRepository.getRoomList(searchText, searchParametersList, start, perPage);
    }

    public List<Room> getRoomList() {
        List<String> emptyList = new ArrayList<>();
        return roomRepository.getRoomList("", emptyList);
    }

    public int getRoomAmount() {
        return roomRepository.getRoomAmount();
    }

    public int getRoomAmount(String searchText, List<String> searchParametersList) {
        return roomRepository.getRoomAmount(searchText, searchParametersList);
    }

    public boolean setRoom(Room room) {
        return roomRepository.setRoom(room);
    }

    public boolean deleteRoom(int id) {
        return roomRepository.deleteRoom(id);
    }

    public boolean updateRoom(Room room) {
        return roomRepository.updateRoom(room);
    }

    public Optional<Room> getRoom(int id) {
        return roomRepository.getRoom(id);
    }

    public boolean uploadNewRoomImage(int roomId, String caption, String mimeType, InputStream roomImage) {
        return roomRepository.uploadNewRoomImage(roomId, caption, mimeType, roomImage);
    }
    public boolean uploadRoomImage(int imageId, int roomId, String caption, String mimeType, InputStream roomImage) {
        return roomRepository.uploadRoomImage(imageId, roomId, caption, mimeType, roomImage);
    }

    public List<RoomImage> getRoomImageList(int id) {
        return roomRepository.getRoomImageList(id);
    }
    public boolean deleteRoomImage(int id) {
        return roomRepository.deleteRoomImage(id);
    }

    public Optional<RoomImage> getRoomImageByCaption (int roomId, String imageCaption) {
        return roomRepository.getRoomImageByCaption(roomId, imageCaption);
    }
}
