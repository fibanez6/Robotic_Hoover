package com.fibanez.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorEnum {

    UNEXPECTED_ERROR(-1, "Unexpected error"),
    WRONG_PARAMETERS(-2, "Unexpected request parameters"),
    WRONG_ROOMSIZE_PARAMETER(-3, "Wrong room dimensions. The room dimensions must have positive values"),
    WRONG_HOOVER_COORD_PARAMETER(-3, "Wrong initial Hoover coordinate parameter."),
    WRONG_PATCH_COORDS_PARAMETER(-4, "Wrong patch coordinates parameter."),
    WRONG_INSTRUCTIONS_PARAMETER(-5, "Wrong instructions parameter.");

    private int code;

    private String error;
}
