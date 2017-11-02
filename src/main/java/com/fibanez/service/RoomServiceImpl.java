package com.fibanez.service;

import com.fibanez.domain.model.Room;
import com.fibanez.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roomService")
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoomById(long id) {
        return roomRepository.findOne(id);
    }

    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void removeRoom(Room room) {
        roomRepository.delete(room);
    }
}
