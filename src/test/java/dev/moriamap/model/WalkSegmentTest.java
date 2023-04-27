package dev.moriamap.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;

class WalkSegmentTest {

    @Test void walkSegmentHasWalkSpeed() {
        assertEquals(4.5, WalkSegment.WALK_SPEED);
    }

    @Test void walkSegmentHasDrudgery() {
        assertEquals(10.0, WalkSegment.WALK_DRUDGERY);
    }

    @Test void distanceOfWalkSegmentBetweenNorthAndSouthPoleIsTwiceEarthRad() {
        var gv1 = GeographicVertex.at(GeographicPosition.NORTH_POLE);
        var gv2 = GeographicVertex.at(GeographicPosition.SOUTH_POLE);
        var sut = new WalkSegment(gv1, gv2);
        assertEquals(2.0 * GeographicPosition.EARTH_RADIUS, sut.distance);
    }

    @Test void travelTimeOfWalkSegmentBetweenNorthAndSouthPole() {
        var gv1 = GeographicVertex.at(GeographicPosition.NORTH_POLE);
        var gv2 = GeographicVertex.at(GeographicPosition.SOUTH_POLE);
        var sut = new WalkSegment(gv1, gv2);
        Duration real = Duration.ofHours( 4424 )
                                .plusMinutes( 18 )
                                .plusSeconds( 20 );
        assertEquals(real, sut.travelTime());
    }
}