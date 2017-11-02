package com.fibanez.web.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoboticHooverResponse implements Serializable {

    @JsonProperty("coords")
    private int[] coords;

    @JsonProperty("patches")
    private int patches;

    @JsonProperty("errors")
    private List<RoboticHooverError> errors;

}
