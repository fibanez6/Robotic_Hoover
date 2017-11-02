package com.fibanez.web.rest.controller;

import com.fibanez.domain.RoboticHooverInput;
import com.fibanez.exception.RoboticHooverException;
import com.fibanez.service.RoboticHooverService;
import com.fibanez.web.rest.dto.request.RoboticHooverRequest;
import com.fibanez.web.rest.dto.response.RoboticHooverError;
import com.fibanez.web.rest.dto.response.RoboticHooverResponse;
import com.fibanez.web.rest.service.RoboticHooverRequestValidatorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/roboticHoover")
public class RoboticHooverController {

    private static final Logger logger = LoggerFactory.getLogger(RoboticHooverController.class);

    @Autowired
    private RoboticHooverService roboticHooverService;

    @Autowired
    private RoboticHooverRequestValidatorService requestValidatorService;

    @ApiOperation(value = "Run robotic hoover",
            notes = "The goal of the service is to take the room dimensions, the locations of the dirt patches, " +
                    "the hoover location and the driving instructions as input.",
            response = RoboticHooverResponse.class,
            httpMethod = "POST"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 400,
                    message = "Bad request",
                    response = RoboticHooverError.class
            ),
            @ApiResponse(
                    code = 500,
                    message = "Internal server exception"
            ),
            @ApiResponse(
                    code = 200,
                    message = "The final hoover position (X, Y)" +
                              "\n\nThe number of patches of dirt the robot cleaned up",
                    response = RoboticHooverResponse.class
            )
    })
    @RequestMapping(value = "/cleanPatches",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public HttpEntity<RoboticHooverResponse> cleanPatches(@RequestBody RoboticHooverRequest request) {

        RoboticHooverResponse roboticHooverResp;

        try {
            // validate input parameters
            RoboticHooverInput roboticHooverInput = requestValidatorService.validateInputs(request);

            // move robotic hoover
            roboticHooverResp = roboticHooverService.runCleaning(roboticHooverInput);

            return new ResponseEntity<>(roboticHooverResp, HttpStatus.OK);
        } catch (RoboticHooverException e) {
            logger.error(e.getMessage(), e);
            roboticHooverResp = new RoboticHooverResponse(null, 0, Collections.singletonList(new RoboticHooverError(e.getError())) );
            return new ResponseEntity<>(roboticHooverResp, HttpStatus.BAD_REQUEST);
        }
    }

}
