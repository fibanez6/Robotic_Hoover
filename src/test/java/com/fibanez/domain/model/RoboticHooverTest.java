package com.fibanez.domain.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class RoboticHooverTest {

    private Room room;
    private RoboticHoover hoover;
    private List<Character> instructions;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        room = null;
        hoover = null;
        instructions = null;
    }

    @Test
    public void testHooverInitialPosition() {
        hoover = new RoboticHoover(new Point(2, 2), room);
        assertEquals(new Point(2, 2), this.hoover.getHooverPosition());
    }

    @Test
    public void testMoveNorthWithValidValue() throws Exception {
        hoover = new RoboticHoover(new Point(1, 3), room);
        assertEquals(new Point(1, 3), this.hoover.getHooverPosition());
        hoover.moveNorth();
        assertEquals(new Point(1, 4), this.hoover.getHooverPosition());
    }

    @Test
    public void testMoveNorthWithNotValidValue() throws Exception {
        hoover = new RoboticHoover(new Point(1, 4), room);
        assertEquals(new Point(1, 4), this.hoover.getHooverPosition());
        hoover.moveNorth();
        assertEquals(new Point(1, 4), this.hoover.getHooverPosition());
    }

    @Test
    public void testMoveEasthWithValidValue() throws Exception {
        hoover = new RoboticHoover(new Point(3, 1), room);
        assertEquals(new Point(3, 1), this.hoover.getHooverPosition());
        hoover.moveEast();
        assertEquals(new Point(4, 1), this.hoover.getHooverPosition());
    }

    @Test
    public void testMoveEasthWithNotValidValue() throws Exception {
        hoover = new RoboticHoover(new Point(4, 1), room);
        assertEquals(new Point(4, 1), this.hoover.getHooverPosition());
        hoover.moveEast();
        assertEquals(new Point(4, 1), this.hoover.getHooverPosition());
    }

    @Test
    public void testMoveSouthWithValidValue() throws Exception {
        hoover = new RoboticHoover(new Point(1, 1), room);
        assertEquals(new Point(1, 1), this.hoover.getHooverPosition());
        hoover.moveSouth();
        assertEquals(new Point(1, 0), this.hoover.getHooverPosition());
    }

    @Test
    public void testMoveSouthWithNotValidValue() throws Exception {
        hoover = new RoboticHoover(new Point(1, 0), room);
        assertEquals(new Point(1, 0), this.hoover.getHooverPosition());
        hoover.moveSouth();
        assertEquals(new Point(1, 0), this.hoover.getHooverPosition());
    }

    @Test
    public void testMoveWestWithValidValue() throws Exception {
        hoover = new RoboticHoover(new Point(1, 1), room);
        assertEquals(new Point(1, 1), this.hoover.getHooverPosition());
        hoover.moveWest();
        assertEquals(new Point(0, 1), this.hoover.getHooverPosition());
    }

    @Test
    public void testMovewesthWithNotValidValue() throws Exception {
        hoover = new RoboticHoover(new Point(0, 1), room);
        assertEquals(new Point(0, 1), this.hoover.getHooverPosition());
        hoover.moveWest();
        assertEquals(new Point(0, 1), this.hoover.getHooverPosition());
    }

    @Test
    public void executeInstructionWith2MovesAndCleanStainPosition() {
        room = new Room(new Point(5, 5),
                new HashSet<>(Arrays.asList(
                        new Point(1, 0),
                        new Point(2, 2),
                        new Point(2, 3)
                )));
        hoover = new RoboticHoover(new Point(1, 1), room);
        assertEquals(new Point(1, 1), this.hoover.getHooverPosition());
        assertEquals(5, room.getRoomArray().length);

        instructions = new LinkedList<>(Arrays.asList('N', 'E'));
        hoover.executeInstructions(instructions);

        assertEquals(new Point(2, 2), this.hoover.getHooverPosition());
        assertEquals(1, room.getStainsCleanedCount().get());
        assertFalse(room.getRoomArray()[2][2]);
    }

}
