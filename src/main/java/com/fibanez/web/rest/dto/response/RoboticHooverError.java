package com.fibanez.web.rest.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fibanez.util.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoboticHooverError {

    @JsonProperty("error")
    private String error;

    @JsonProperty("code")
    private int code;

    public RoboticHooverError(ErrorEnum errorEnum) {
        this.error = errorEnum.getError();
        this.code = errorEnum.getCode();
    }
}
