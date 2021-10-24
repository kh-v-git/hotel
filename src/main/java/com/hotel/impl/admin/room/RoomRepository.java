package com.hotel.impl.admin.room;

import java.util.List;

public interface RoomRepository {
    List<Room> getRoomList(String searchText, List<String> searchParametersList);

    boolean setRoom(Room room);

    Room getRoom(int id);

    boolean deleteRoom(int id);

    boolean updateRoom(Room room);
}
