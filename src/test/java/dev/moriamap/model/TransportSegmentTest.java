package dev.moriamap.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.time.LocalTime;

public class TransportSegmentTest {
    
    @Test public void testConstruction(){
        Stop s1 = Stop.from("s1",GeographicPosition.SOUTH_POLE);
        Stop s2 = Stop.from("s2",GeographicPosition.NORTH_POLE);
        TransportSegment ts = TransportSegment.from(s1, s2, "14 Variant 1", Duration.ZERO, 0.0);
        assertEquals(0.0, ts.distance);
    }
    @Test public void testTransportSegmentNotEqualToNull(){
        Stop s1 = Stop.from("s1",GeographicPosition.SOUTH_POLE);
        Stop s2 = Stop.from("s2",GeographicPosition.NORTH_POLE);
        TransportSegment ts = TransportSegment.from(s1, s2, "14 Variant 1", Duration.ZERO, 0.0);
        assertNotEquals(ts, null);
    }

    @Test public void testIsNotEqualToNull(){
        Stop s1 = Stop.from("s1",GeographicPosition.SOUTH_POLE);
        Stop s2 = Stop.from("s2",GeographicPosition.NORTH_POLE);
        TransportSegment ts = TransportSegment.from(s1, s2, "14 Variant 1", Duration.ZERO, 0.0);
        assertNotEquals(ts, null);
    }

    @Test public void testObjectIsNotEqualToTransportSegment(){
        Object o = new Object();
        Stop s1 = Stop.from("s1",GeographicPosition.SOUTH_POLE);
        Stop s2 = Stop.from("s2",GeographicPosition.NORTH_POLE);
        TransportSegment ts = TransportSegment.from(s1, s2, "14 Variant 1", Duration.ZERO, 0.0);
        assertNotEquals(ts, o);
    }

    @Test public void testEqualsOnItselfReturnsTrue(){
        Stop s1 = Stop.from("s1",GeographicPosition.SOUTH_POLE);
        Stop s2 = Stop.from("s2",GeographicPosition.NORTH_POLE);
        TransportSegment ts = TransportSegment.from(s1, s2, "14 Variant 1", Duration.ZERO, 0.0);
        assertEquals(ts, ts);
    }

    @Test public void testEqualsOnATransportSegmentWithTheSameValuesReturnsTrue(){
        Stop s1 = Stop.from("s1",GeographicPosition.SOUTH_POLE);
        Stop s2 = Stop.from("s2",GeographicPosition.NORTH_POLE);
        TransportSegment ts1 = TransportSegment.from(s1, s2, "14 Variant 1", Duration.ZERO, 0.0);
        TransportSegment ts2 = TransportSegment.from(s1, s2, "14 Variant 1", Duration.ZERO, 0.0);
        assertEquals(ts1, ts2);
    }

    @Test public void testEqualsOnATransportSegmentWithDifferentDistanceReturnsFalse(){
        Stop s1 = Stop.from("s1",GeographicPosition.SOUTH_POLE);
        Stop s2 = Stop.from("s2",GeographicPosition.NORTH_POLE);
        TransportSegment ts1 = TransportSegment.from(s1, s2, "14 Variant 1", Duration.ZERO, 1.0);
        TransportSegment ts2 = TransportSegment.from(s1, s2, "14 Variant 1", Duration.ZERO, 0.0);
        assertNotEquals(ts1, ts2);
    }

    @Test public void testEqualsOnATransportSegmentWithDifferentDurationReturnsFalse(){
        Stop s1 = Stop.from("s1",GeographicPosition.SOUTH_POLE);
        Stop s2 = Stop.from("s2",GeographicPosition.NORTH_POLE);
        TransportSegment ts1 = TransportSegment.from(s1, s2, "14 Variant 1", Duration.ZERO, 1.0);
        TransportSegment ts2 = TransportSegment.from(s1, s2, "14 Variant 1", Duration.between(LocalTime.NOON, LocalTime.MIDNIGHT), 0.0);
        assertNotEquals(ts1, ts2);
    }

    @Test public void testEqualsOnATransportSegmentWithDifferentlineVariantNameReturnsFalse(){
        Stop s1 = Stop.from("s1",GeographicPosition.SOUTH_POLE);
        Stop s2 = Stop.from("s2",GeographicPosition.NORTH_POLE);
        TransportSegment ts1 = TransportSegment.from(s1, s2, "14 Variant 1", Duration.ZERO, 1.0);
        TransportSegment ts2 = TransportSegment.from(s1, s2, "14 Variant 2", Duration.ZERO, 0.0);
        assertNotEquals(ts1,ts2);
    }

    @Test public void testGetWeight(){
        Stop s1 = Stop.from("s1",GeographicPosition.SOUTH_POLE);
        Stop s2 = Stop.from("s2",GeographicPosition.NORTH_POLE);
        TransportSegment ts = TransportSegment.from(s1, s2, "14 Variant 1", Duration.ZERO, 1.0);
        assertEquals(1.0, ts.getWeight());
    }

    @Test public void testGetFrom(){
        Stop s1 = Stop.from("s1",GeographicPosition.SOUTH_POLE);
        Stop s2 = Stop.from("s2",GeographicPosition.NORTH_POLE);
        TransportSegment ts = TransportSegment.from(s1, s2, "14 Variant 1", Duration.ZERO, 0.0);
        assertEquals(s1, ts.getFrom());
    }

    @Test public void testGetTo(){
        Stop s1 = Stop.from("s1",GeographicPosition.SOUTH_POLE);
        Stop s2 = Stop.from("s2",GeographicPosition.NORTH_POLE);
        TransportSegment ts = TransportSegment.from(s1, s2, "14 Variant 1", Duration.ZERO, 0.0);
        assertEquals(s2, ts.getTo());
    }
}
