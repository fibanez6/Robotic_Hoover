package com.fibanez.web.rest.controller;

import com.fibanez.domain.model.Room;
import com.fibanez.exception.RoboticHooverException;
import com.fibanez.service.RoboticHooverService;
import com.fibanez.service.RoomService;
import com.fibanez.util.ErrorEnum;
import com.fibanez.web.rest.dto.request.RoboticHooverRequest;
import com.fibanez.web.rest.service.RoboticHooverRequestValidatorService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.awt.*;
import java.util.HashSet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(RoboticHooverController.class)
public class RoboticHooverControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RoomService roomService;

    @Autowired
    RoboticHooverService roboticHooverService;

    @Autowired
    RoboticHooverRequestValidatorService requestValidatorService;

    /**
     * Success: true
     *
     * Inputs: {roomSize" : [5,5], coords" : [1,2], patches" : [[1, 0],[2, 2],[2, 3]], instructions" : "NNESEESWNWW"}
     *
     * Expected: HttpCode:200 content:{"coords": [1,3],"patches": 1}
     *
     * @throws Exception
     */
    @Test
    public void roboticHooverCleanPatchesWithSuccess() throws Exception {

        Mockito.when(roomService.saveRoom(Mockito.any(Room.class)))
                .thenReturn(new Room(new Point(), new HashSet<Point>()) );

        this.mockMvc.perform(post("/roboticHoover/cleanPatches")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{" +
                "  \"roomSize\" : [5,5]," +
                "  \"coords\" : [1,2]," +
                "  \"patches\" : [[1, 0],[2, 2],[2, 3]]," +
                "  \"instructions\" : \"NNESEESWNWW\"" +
                "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.coords", Matchers.contains(1,3)))
                .andExpect(jsonPath("$.patches").value(1))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * Success: false
     *
     * Inputs: {roomSize" : [], coords" : [], patches" : [[]], instructions" : ""}
     *
     * Expected: HttpCode:400 content:{"errors": [{"code": -2,"error": "Unexpected request parameters"}]}
     **
     * @throws Exception
     */
    @Test
    public void roboticHooverCleanPatchesWithFailureWith400() throws Exception {

        Mockito.when(requestValidatorService.validateInputs(Mockito.any(RoboticHooverRequest.class)))
                .thenThrow(new RoboticHooverException(ErrorEnum.WRONG_PARAMETERS));

        this.mockMvc.perform(post("/roboticHoover/cleanPatches")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{" +
                "  \"roomSize\" : []," +
                "  \"coords\" : []," +
                "  \"patches\" : [[]]," +
                "  \"instructions\" : \"\"" +
                "}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0].code").value(ErrorEnum.WRONG_PARAMETERS.getCode()))
                .andExpect(jsonPath("$.errors[0].error").value(ErrorEnum.WRONG_PARAMETERS.getError()))
                .andDo(MockMvcResultHandlers.print());
    }


}
