package dev.moriamap.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.List;
import java.util.ArrayList;

public class GeographicPositionTest {

    @Test void latitudeOfNullIslandIs0() {
        GeographicPosition sut = GeographicPosition.NULL_ISLAND;
        assertEquals(0.0, sut.getLatitude());
    }

    @Test void longitudeOfNullIslandIs0() {
        GeographicPosition sut = GeographicPosition.NULL_ISLAND;
        assertEquals(0.0, sut.getLongitude());
    }

    @Test void latitudeOfGPAtLatitude5Is5() {
        GeographicPosition sut = GeographicPosition.at(5.0, 0.0);
        assertEquals(5.0, sut.getLatitude());
    }

    @Test void longitudeOfGPAtLongitude42Is42() {
        GeographicPosition sut = GeographicPosition.at(18.39, 42.0);
        assertEquals(42.0, sut.getLongitude());
    }

    @Test void latitudeOf180DegreesThrowsIllegalArgumentException() {
        assertThrows(
          IllegalArgumentException.class,
          () -> GeographicPosition.at(180.0, 42.0)
        );
    }

    @Test void latitudeOfMinus91DegreesThrowsIllegalArgumentException() {
        assertThrows(
          IllegalArgumentException.class,
          () -> GeographicPosition.at(-91.5, 42.0)
        );
    }

    @Test void longitudeOfMinus181DegreesThrowsIllegalArgumentException() {
        assertThrows(
          IllegalArgumentException.class,
          () -> GeographicPosition.at(42.0, -181.0)
        );
    }

    @Test void longitudeOf281DegreesThrowsIllegalArgumentException() {
        assertThrows(
          IllegalArgumentException.class,
          () -> GeographicPosition.at(42.0, 281.4)
        );
    }

    @Test void cartesianCoordinatesOfNullIslandIsOnEarthAreRt_0_0(){
        GeographicPosition nullIsland = GeographicPosition.NULL_ISLAND;
        List<Double> res = new ArrayList<>();
        double radius = GeographicPosition.EARTH_RADIUS;
        res.add(radius);
        res.add(0.0);
        res.add(0.0);
        assertEquals(res, nullIsland.toCartesianAsList(radius));
    }

    @Test void cartesianCoordinatesOfNorthPoleOnEarthAre0_0_Rt() {
        GeographicPosition northPole = GeographicPosition.NORTH_POLE;
        List<Double> res = new ArrayList<>();
        double radius = GeographicPosition.EARTH_RADIUS;
        res.add(0.0);
        res.add(0.0);
        res.add(radius);
        assertEquals(res, northPole.toCartesianAsList(radius));
    }

    @Test void cartesianCoordinatesOfSouthPoleOnEarthAre0_0_MinusRt() {
        GeographicPosition southPole = GeographicPosition.SOUTH_POLE;
        List<Double> res = new ArrayList<>();
        double radius = GeographicPosition.EARTH_RADIUS;
        res.add(0.0);
        res.add(0.0);
        res.add(-radius);
        assertEquals(res, southPole.toCartesianAsList(radius));
    }

    @Test void negativeRadiusForCartesianCoordinatesThrowsException() {
        GeographicPosition point = GeographicPosition.at(-42.0, 142.0);
        assertThrows(
          IllegalArgumentException.class,
          () -> point.toCartesian(-0.0)
        );
    }

    @Test void euclidianDistanceBetweenSameGPIs0() {
        GeographicPosition point = GeographicPosition.at(-42.0, -42.0);
        double res = GeographicPosition.euclidianDistance(
          point,
          point,
          GeographicPosition.EARTH_RADIUS
        );
        assertEquals(0.0, res);
    }

    @Test void euclidianDistanceBetweenNorthAndSouthPoleIsTwiceEarthRadius() {
        GeographicPosition northPole = GeographicPosition.NORTH_POLE;
        GeographicPosition southPole = GeographicPosition.SOUTH_POLE;
        double distance = GeographicPosition.EARTH_RADIUS;
        assertEquals(
          2 * distance,
          GeographicPosition.euclidianDistance(northPole, southPole, distance)
        );
    }

    @Test void euclidianDistanceWithNegativeRadiusThrowsException() {
        GeographicPosition p1 = GeographicPosition.at(32.0, 42.0);
        GeographicPosition p2 = GeographicPosition.at(42.0, 15.0);
        assertThrows(
          IllegalArgumentException.class,
          () -> GeographicPosition.euclidianDistance(p1, p2, -0.0)
        );
    }

    @Test void distanceFromNorthPoleToSouthPoleIsTwiceTheEarthRadius() {
        GeographicPosition northPole = GeographicPosition.NORTH_POLE;
        GeographicPosition southPole = GeographicPosition.SOUTH_POLE;
        double distance = GeographicPosition.EARTH_RADIUS;
        assertEquals(2 * distance, northPole.distanceFrom(southPole));
    }
}
