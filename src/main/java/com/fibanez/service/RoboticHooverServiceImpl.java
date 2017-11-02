package com.fibanez.service;

import com.fibanez.domain.RoboticHooverInput;
import com.fibanez.domain.model.RoboticHoover;
import com.fibanez.domain.model.Room;
import com.fibanez.exception.RoboticHooverException;
import com.fibanez.web.rest.dto.response.RoboticHooverResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class RoboticHooverServiceImpl implements RoboticHooverService {

    @Autowired
    private RoomService roomService;

    @Override
    public RoboticHooverResponse runCleaning(RoboticHooverInput hooverInput) throws RoboticHooverException {

        // init room
        Room room = new Room(hooverInput.getRoomSize(), hooverInput.getPatchesPositions());

        // init hoover
        RoboticHoover hoover = new RoboticHoover(hooverInput.getHooverCoordPosition(), room);

        // move hoover
        hoover.executeInstructions(hooverInput.getInstructions());

        this.roomService.saveRoom(room);

        // results
        Point finalHooverPosition = hoover.getHooverPosition();
        int totalPatchesCleaned = room.getStainsCleanedCount().get();

        return new RoboticHooverResponse(new int[] {finalHooverPosition.x, finalHooverPosition.y}, totalPatchesCleaned, null);
    }



}
