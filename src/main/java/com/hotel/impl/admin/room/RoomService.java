package com.hotel.impl.admin.room;


import java.util.ArrayList;
import java.util.List;

public class RoomService {
    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getRoomList(String searchText, List<String> searchParametersList) {
        return roomRepository.getRoomList(searchText, searchParametersList);
    }
    public List<Room> getRoomList() {
        List<String> emptyList = new ArrayList<>();
        return roomRepository.getRoomList("", emptyList);
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

    public Room getRoom(int id) {
        return roomRepository.getRoom(id);
    }
}
