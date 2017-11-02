package com.fibanez.web.rest.service;

import com.fibanez.domain.RoboticHooverInput;
import com.fibanez.exception.RoboticHooverException;
import com.fibanez.web.rest.dto.request.RoboticHooverRequest;

import java.awt.*;
import java.util.*;

public interface RoboticHooverRequestValidatorService {

    RoboticHooverInput validateInputs(RoboticHooverRequest hooverRequest) throws RoboticHooverException;

    Point validateRoomSize(int[] roomSize) throws RoboticHooverException;

    Point validateHooverCoords(int[] coords, final Point roomSize) throws RoboticHooverException;

    Set<Point> validatePatches(int[][] patches, final Point roomSize) throws RoboticHooverException;

    java.util.List<Character> validateInstructions(final String instructions) throws RoboticHooverException;
}
