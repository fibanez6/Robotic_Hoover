package com.fibanez.service;

import com.fibanez.domain.RoboticHooverInput;
import com.fibanez.exception.RoboticHooverException;
import com.fibanez.web.rest.dto.response.RoboticHooverResponse;

public interface RoboticHooverService {

    RoboticHooverResponse runCleaning(RoboticHooverInput hooverInput) throws RoboticHooverException;

}
