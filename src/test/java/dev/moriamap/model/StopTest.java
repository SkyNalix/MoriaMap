package dev.moriamap.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.List;
import java.util.ArrayList;

class StopTest {
    @Test void stopConstructorTest(){
        Stop s = Stop.from("test",GeographicPosition.NULL_ISLAND);
        assertEquals("test",s.getName());
    }

    @Test void stopInstanceIsEqualToItselfTest() {
        Stop s = Stop.from("Stop", GeographicPosition.at(15.59,18.43));
        assertEquals(s,s);
    }
    @Test void instanceOfDifferentClassIsNotEqual() {
        Stop v = Stop.from("Stop", GeographicPosition.at(15.59,18.43));
        GeographicVertex w = GeographicVertex.at(15.59,18.43);
        assertNotEquals(v,w);
    }

    @Test void hashCodeOfSemanticallyEqualStopsAreEqual() {
        Stop v = Stop.from("Stop", GeographicPosition.at(15.59,18.43));
        Stop w = Stop.from("Stop", GeographicPosition.at(15.59,18.43));
        assertEquals(v.hashCode(),w.hashCode());
    }

    @Test void stopsWithDifferentNamesAreNotEqual() {
        Stop v = Stop.from("Stop1", GeographicPosition.at(15.59,18.43));
        Stop w = Stop.from("Stop", GeographicPosition.at(15.59,18.43));
        assertNotEquals(v.hashCode(),w.hashCode());
    }

    @Test void stopsWithDifferentCoordinatesAreNotEqual() {
        Stop v = Stop.from("Stop", GeographicPosition.at(18.59,18.43));
        Stop w = Stop.from("Stop", GeographicPosition.at(15.59,18.43));
        assertNotEquals(v.hashCode(),w.hashCode());
    }

    @Test void stopIsNotEqualToNull() {
        Stop v = Stop.from("Stop", GeographicPosition.at(18.59,18.43));
        assertNotEquals(null, v);
    }
}
