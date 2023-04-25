package dev.moriamap.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WalkSegmentTest {

    @Test void walkSegmentHasWalkSpeed() {
        var gv1 = GeographicVertex.at(33.0, 32.0);
        var gv2 = GeographicVertex.at(42.0, 12.5);
        var sut = new WalkSegment(gv1, gv2);
        assertEquals(4.5, sut.WALK_SPEED);
    }

    @Test void walkSegmentHasDrudgery() {
        var gv1 = GeographicVertex.at(12.6, 21.9);
        var gv2 = GeographicVertex.at(2.0, 12.5);
        var sut = new WalkSegment(gv1, gv2);
        assertEquals(10.0, sut.WALK_DRUDGERY);
    }

    @Test void distanceOfWalkSegmentBetweenNorthAndSouthPoleIsTwiceEarthRad() {
        var gv1 = GeographicVertex.at(GeographicPosition.NORTH_POLE);
        var gv2 = GeographicVertex.at(GeographicPosition.SOUTH_POLE);
        var sut = new WalkSegment(gv1, gv2);
        assertEquals(2.0 * GeographicPosition.EARTH_RADIUS, sut.distance);
    }
}
