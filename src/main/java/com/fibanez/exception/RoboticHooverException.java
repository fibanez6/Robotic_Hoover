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

    public RoboticHooverException(String message, ErrorEnum error) {
        super(message);
        this.error = error;
    }

    public RoboticHooverException(String message, Throwable cause, ErrorEnum error) {
        super(message, cause);
        this.error = error;
    }
}
