package dev.moriamap.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LineTest {
    @Test void testLineConstruction() {
        Line l = new Line("14");
        assertEquals("14", l.getName());
    }

    @Test void testLineStaticFactory() {
        Line l = Line.of("13");
        assertEquals("13", l.getName());
    }
}
