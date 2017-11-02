package com.fibanez.web.rest.service;

import com.fibanez.exception.RoboticHooverException;
import com.fibanez.util.ErrorEnum;
import com.fibanez.web.rest.dto.request.RoboticHooverRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RoboticHooverRequestValidatorServiceTest {

    private RoboticHooverRequestValidatorService requestValidatorService;

    private RoboticHooverRequest roboticHooverRequest;

    @Before
    public void setUp() {
        requestValidatorService = new RoboticHooverRequestValidatorServiceImpl();
    }

    @After
    public void tearDown() throws Exception {
        requestValidatorService = null;
        roboticHooverRequest = null;
    }

    /*
    * Request with Null values
    *
    * Expected: Exception with roomSize with error WRONG_ROOMSIZE_PARAMETER
    */
    @Test
    public void validateRoomSizeWhenNull() {
        roboticHooverRequest = new RoboticHooverRequest(null, null, null, null);
        try {
            requestValidatorService.validateRoomSize(roboticHooverRequest.getRoomSize());
        } catch (Exception e) {
            assertTrue(e instanceof RoboticHooverException);
            assertEquals(ErrorEnum.WRONG_ROOMSIZE_PARAMETER, ((RoboticHooverException) e).getError());
        }
    }

    /*
    * Request with Null values
    *
    * Expected: Exception with coords with error WRONG_HOOVER_COORD_PARAMETER
    */
    @Test
    public void validateInitialHooverPositionWhenNull() {
        roboticHooverRequest = new RoboticHooverRequest(null, null, null, null);
        try {
            requestValidatorService.validateHooverCoords(roboticHooverRequest.getCoords(), new Point());
        } catch (Exception e) {
            assertTrue(e instanceof RoboticHooverException);
            assertEquals(ErrorEnum.WRONG_HOOVER_COORD_PARAMETER, ((RoboticHooverException) e).getError());
        }
    }

    /*
    * Request with Null values
    *
    * Expected: Exception with patches list with error WRONG_PATCH_COORDS_PARAMETER
    */
    @Test
    public void validatePatchesListWhenNull() {
        roboticHooverRequest = new RoboticHooverRequest(null, null, null, null);
        try {
            requestValidatorService.validatePatches(roboticHooverRequest.getPatches(), new Point());
        } catch (Exception e) {
            assertTrue(e instanceof RoboticHooverException);
            assertEquals(ErrorEnum.WRONG_PATCH_COORDS_PARAMETER, ((RoboticHooverException) e).getError());
        }
    }

    /*
    * Request with Null values
    *
    * Expected: Exception with instructions list with error WRONG_INSTRUCTIONS_PARAMETER
    */
    @Test
    public void validateInstructionsWhenNull() {
        roboticHooverRequest = new RoboticHooverRequest(null, null, null, null);
        try {
            requestValidatorService.validateInstructions(roboticHooverRequest.getInstructions());
        } catch (Exception e) {
            assertTrue(e instanceof RoboticHooverException);
            assertEquals(ErrorEnum.WRONG_INSTRUCTIONS_PARAMETER, ((RoboticHooverException) e).getError());
        }
    }

    /*
    * Request with Null values
    *
    * Expected: Exception with instructions list with error WRONG_INSTRUCTIONS_PARAMETER
    */
    @Test
    public void validateInstructionsWhenContainsWrongInstructions() {
        roboticHooverRequest = new RoboticHooverRequest(null, null, null, "NESA");
        try {
            requestValidatorService.validateInstructions(roboticHooverRequest.getInstructions());
        } catch (Exception e) {
            assertTrue(e instanceof RoboticHooverException);
            assertEquals(ErrorEnum.WRONG_INSTRUCTIONS_PARAMETER, ((RoboticHooverException) e).getError());
        }
    }

}
