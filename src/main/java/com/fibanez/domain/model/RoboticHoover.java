package com.fibanez.domain.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.List;

@Data
@RequiredArgsConstructor
public class RoboticHoover {

    private final Logger logger = LoggerFactory.getLogger(RoboticHoover.class);

    private final Point hooverPosition;

    private final Room room;

    private int cleanedPatchCounter;

    public void executeInstructions(List<Character> instructions) {
        logger.info("Moving to: " + instructions.toString());
        instructions.stream().forEach(i -> {
            switch(i) {
                case 'N': moveNorth(); break;
                case 'E': moveEast(); break;
                case 'S': moveSouth(); break;
                case 'W': moveWest(); break;
                default:
                    logger.error("Command not recognised: " + i);
            }
            if(room.isSpottedPosition(this.hooverPosition)) {
                this.cleanedPatchCounter++;
                this.room.cleanStain(this.hooverPosition);
            }
        });
    }

    public void moveNorth() {
        int upperRoomEdge = this.room.getRoomDimension().y-1;
        if(this.hooverPosition.y < upperRoomEdge) {
            this.hooverPosition.y++;
        }
    }

    public void moveSouth() {
        if(this.hooverPosition.y > 0) {
            this.hooverPosition.y--;
        }
    }

    public void moveWest() {
        if(this.hooverPosition.x > 0) {
            this.hooverPosition.x--;
        }
    }

    public void moveEast() {
        int easternRoomEdge = this.room.getRoomDimension().x-1;
        if(this.hooverPosition.x < easternRoomEdge) {
            this.hooverPosition.x++;
        }
    }

}
