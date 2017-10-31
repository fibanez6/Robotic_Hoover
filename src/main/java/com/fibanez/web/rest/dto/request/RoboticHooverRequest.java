package com.fibanez.web.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class RoboticHooverRequest {

    @JsonProperty("roomSize")
    int[] roomSize;

    @JsonProperty("coords")
    int[] coords;

    @JsonProperty("patches")
    int[][] patches;

    @JsonProperty("instructions")
    String instructions;

}
