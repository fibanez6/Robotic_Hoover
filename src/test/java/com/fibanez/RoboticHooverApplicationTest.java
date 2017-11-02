package com.fibanez;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.actuate.health.ApplicationHealthIndicator;
import org.springframework.boot.actuate.health.Status;

public class RoboticHooverApplicationTest {

    @Before
    public void setUp() throws Exception { }

    @After
    public void tearDown() throws Exception { }

    @Test
    public void actuatorHealthCheckTest() throws Exception {
        ApplicationHealthIndicator healthIndicator = new ApplicationHealthIndicator();
        Assert.assertEquals(Status.UP, healthIndicator.health().getStatus());
    }

}
