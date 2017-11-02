package com.fibanez.exception;

import com.fibanez.util.ErrorEnum;
import lombok.Getter;

@Getter
public class RoboticHooverException extends Exception {

    private ErrorEnum error;

    public RoboticHooverException(ErrorEnum error) {
        super();
        this.error = error;
    }
}
