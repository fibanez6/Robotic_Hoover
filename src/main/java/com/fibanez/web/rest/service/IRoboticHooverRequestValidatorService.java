package com.fibanez.web.rest.service;

import com.fibanez.domain.RoboticHooverInput;
import com.fibanez.exception.RoboticHooverException;
import com.fibanez.web.rest.dto.request.RoboticHooverRequest;

public interface IRoboticHooverRequestValidatorService {

    RoboticHooverInput validateInputs(RoboticHooverRequest hooverRequest) throws RoboticHooverException;

}
