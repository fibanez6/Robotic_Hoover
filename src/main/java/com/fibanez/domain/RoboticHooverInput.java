package com.fibanez.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class RoboticHooverInput {

    private final Point roomSize;

    private final Point hooverCoordPosition;

    private final Set<Point> patchesPositions;

    private final List<Character> instructions;

}
