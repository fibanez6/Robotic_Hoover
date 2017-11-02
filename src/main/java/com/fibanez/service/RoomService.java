package com.fibanez.service;


import com.fibanez.domain.model.Room;

import java.util.List;

public interface RoomService {

    List<Room> getAllRooms();

    Room getRoomById(long id);

    Room saveRoom(Room room);

    void removeRoom(Room room);

}
