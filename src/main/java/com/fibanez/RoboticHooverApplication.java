package com.fibanez;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.fibanez.web.rest", "com.fibanez.service"})
@SpringBootApplication
public class RoboticHooverApplication {

    private static Logger logger = LoggerFactory.getLogger(RoboticHooverApplication.class);

    public static void main(String[] args) throws Exception {

        SpringApplication.run(RoboticHooverApplication.class, args);

    }

}
