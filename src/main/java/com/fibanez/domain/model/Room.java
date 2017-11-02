package com.fibanez.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.awt.*;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue
    private long id;

    private final Point roomDimension;

    private AtomicInteger stainsCleanedCount = new AtomicInteger(0);

    private boolean roomArray[][];

    public Room(Point roomDimension, Set<Point> stainPositions) {
        this.roomDimension = roomDimension;

        this.roomArray = new boolean[(int)roomDimension.getX()] [(int)roomDimension.getY()];

        stainPositions.forEach(stain -> this.roomArray[(int)stain.getX()] [(int)stain.getY()] = true);
    }

    public boolean isSpottedPosition(Point hooverPos) {
        return this.roomArray[(int)hooverPos.getX()] [(int)hooverPos.getY()];
    }

    public void cleanStain(Point patchPos) {
        stainsCleanedCount.incrementAndGet();
        this.roomArray[(int)patchPos.getX()] [(int)patchPos.getY()] = false;
    }


}
