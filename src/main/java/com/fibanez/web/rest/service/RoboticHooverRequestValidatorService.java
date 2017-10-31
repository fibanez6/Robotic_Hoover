package com.fibanez.web.rest.service;

import com.fibanez.domain.RoboticHooverInput;
import com.fibanez.exception.RoboticHooverException;
import com.fibanez.util.ErrorEnum;
import com.fibanez.web.rest.dto.request.RoboticHooverRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class RoboticHooverRequestValidatorService implements IRoboticHooverRequestValidatorService {

    public static final String REGEX_INSTRUCTION = "[^NESW]+";
    private final Logger logger = LoggerFactory.getLogger(RoboticHooverRequestValidatorService.class);

    @Override
    public RoboticHooverInput validateInputs(RoboticHooverRequest hooverRequest) throws RoboticHooverException {

        if (hooverRequest == null) {
            throw new RoboticHooverException(ErrorEnum.WRONG_PARAMETERS);
        }

        Point roomSize = validateRoomSize(hooverRequest.getRoomSize());
        Point initHooverCoord = validateHooverCoords(hooverRequest.getCoords(), roomSize);
        Set<Point> patchesSet = validatePatches(hooverRequest.getPatches(), roomSize);
        List<Character> instructions = validateInstructions(hooverRequest.getInstructions());

        return new RoboticHooverInput(roomSize, initHooverCoord, patchesSet, instructions);

    }

    private Point validateRoomSize(int[] roomSize) throws RoboticHooverException {
        if(null == roomSize || roomSize.length != 2 || roomSize[0] < 1 || roomSize[1] < 1) {
            throw new RoboticHooverException(ErrorEnum.WRONG_ROOMSIZE_PARAMETER);
        }
        logger.debug("RoomSize parameter - OK!");
        return new Point(roomSize[0], roomSize[1]);
    }

    private Point validateHooverCoords(int[] coords, final Point roomSize) throws RoboticHooverException {
        if(null == coords || coords.length != 2 || coords[0] > roomSize.getX() || coords[0] < 0 || coords[1] > roomSize.getY() || coords[1] < 0) {
            throw new RoboticHooverException(ErrorEnum.WRONG_HOOVER_COORD_PARAMETER);
        }
        logger.debug("Coords parameter - OK!");
        return new Point(coords[0], coords[1]);
    }

    private Set<Point> validatePatches(int[][] patches, final Point roomSize) throws RoboticHooverException {
        if(patches == null) {
            throw new RoboticHooverException(ErrorEnum.WRONG_PATCH_COORDS_PARAMETER);
        }

        Set<Point> patchesSet = Arrays.stream(patches)
                .filter( coord -> coord[0] >= 0 && coord[1] >= 0 )
                .filter( coord -> coord[0] < roomSize.getX() && coord[1] < roomSize.getY() )
                .map( coord -> new Point(coord[0], coord[1]))
                .collect(Collectors.toSet());

        logger.debug("Patches - OK!");
        return patchesSet;
    }

    private List<Character> validateInstructions(final String instructions) throws RoboticHooverException {
        if(StringUtils.isEmpty(instructions)) {
            throw new RoboticHooverException(ErrorEnum.WRONG_INSTRUCTIONS_PARAMETER);
        }
        String trimmedInstructions = StringUtils.trimAllWhitespace(instructions).toUpperCase();

        Pattern p = Pattern.compile(REGEX_INSTRUCTION);
        Matcher m = p.matcher(instructions);
        if(!m.matches()) {
            logger.debug("Instructions - OK!");
            return trimmedInstructions.chars().mapToObj(e->(char)e).collect(Collectors.toList());
        }
        else {
            throw new RoboticHooverException(ErrorEnum.WRONG_INSTRUCTIONS_PARAMETER);
        }
    }

}
