package com.fibanez.domain.model;

import lombok.Data;

import java.awt.*;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Room {

    private final Point roomDimension;

    private AtomicInteger stainsCleanedCount = new AtomicInteger(0);

    boolean roomArray[][];

    public Room(Point roomDimension, Set<Point> stainPositions) {
        this.roomDimension = roomDimension;

        this.roomArray = new boolean[(int)roomDimension.getX()] [(int)roomDimension.getY()];

        stainPositions.forEach(patch -> this.roomArray[(int)patch.getX()] [(int)patch.getY()] = true);
    }

    public boolean isSpottedPosition(Point hooverPos) {
        return this.roomArray[(int)hooverPos.getX()] [(int)hooverPos.getY()];
    }

    public void cleanStain(Point patchPos) {
        stainsCleanedCount.incrementAndGet();
        this.roomArray[(int)patchPos.getX()] [(int)patchPos.getY()] = false;
    }


}
