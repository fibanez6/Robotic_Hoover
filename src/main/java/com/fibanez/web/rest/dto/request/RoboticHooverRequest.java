package com.fibanez.web.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class RoboticHooverRequest implements Serializable {

    @JsonProperty("roomSize")
    private int[] roomSize;

    @JsonProperty("coords")
    private int[] coords;

    @JsonProperty("patches")
    private int[][] patches;

    @JsonProperty("instructions")
    private String instructions;

}
