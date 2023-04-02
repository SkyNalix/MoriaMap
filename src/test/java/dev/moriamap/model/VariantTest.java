package dev.moriamap.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Time;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

class VariantTest {

    @Test void Construction() {
        Variant v = Variant.empty("1", "14");
        assertEquals("1", v.getName());
    }

    @Test void testEmptyVariantWithNullLineThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> Variant.empty("1", null));
    }

    @Test void testEmptyVariantWithNullNameThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> Variant.empty(null, "14"));
    }

    @Test void variantsHavingSameIdAndLinesWithSameReferencesAreEqual() {
        Variant v = Variant.empty("1","14");
        Variant v1 = Variant.empty("1","14");
        Time t = new Time(0);
        v.addTrainDeparture(t);
        v1.addTrainDeparture(t);
        assertEquals(v,v1);
    }

    @Test void variantsHavingSameIdAndSameLineNameAndSameTransportSegmentsAreEqual() {
        Variant v = Variant.empty("1","14");
        Variant v1 = Variant.empty("1","14");
        Stop s1 = Stop.from("s1",GeographicPosition.SOUTH_POLE);
        Stop s2 = Stop.from("s2",GeographicPosition.NORTH_POLE);
        TransportSegment ts = TransportSegment.from(s1, s2, "14","Variant 1", Duration.ZERO, 0.0);
        v.addTransportSegments(ts);
        v1.addTransportSegments(ts);
        assertEquals(v,v1);
    }

    @Test void variantsHavingSameIdAndSameLineNameAndDifferentTransportSegmentsAreNotEqual() {
        Variant v = Variant.empty("1","14");
        Variant v1 = Variant.empty("1","14");
        Stop s1 = Stop.from("s1",GeographicPosition.SOUTH_POLE);
        Stop s2 = Stop.from("s2",GeographicPosition.NORTH_POLE);
        TransportSegment ts1 = TransportSegment.from(s1, s2, "14","Variant 1", Duration.ZERO, 0.0);
        TransportSegment ts2 = TransportSegment.from(s1, s2, "14","Variant 2", Duration.ZERO, 0.0);
        v.addTransportSegments(ts1);
        v1.addTransportSegments(ts2);
        assertNotEquals(v,v1);
    }

    @Test void variantsHavingSameIdAndSameLineNameAndDifferentTransportSegmentsSizeAreNotEqual() {
        Variant v = Variant.empty("1","14");
        Variant v1 = Variant.empty("1","14");
        Stop s1 = Stop.from("s1",GeographicPosition.SOUTH_POLE);
        Stop s2 = Stop.from("s2",GeographicPosition.NORTH_POLE);
        TransportSegment ts1 = TransportSegment.from(s1, s2, "14","Variant 1", Duration.ZERO, 0.0);
        TransportSegment ts2 = TransportSegment.from(s1, s2, "14","Variant 2", Duration.ZERO, 0.0);
        v.addTransportSegments(ts1);
        v.addTransportSegments(ts2);
        v1.addTransportSegments(ts2);
        assertNotEquals(v,v1);
    }

    @Test void variantsWithDifferentTrainDepartureSizeAreNotEqual(){
        Variant v = Variant.empty("1","14");
        Variant v1 = Variant.empty("1","14");
        Time t1 = new Time(0);
        Time t2 = new Time(1);
        v.addTrainDeparture(t1);
        v.addTrainDeparture(t2);
        v1.addTrainDeparture(t2);
        assertNotEquals(v, v1);
    }

    @Test void variantIsEqualToItself() {
        Variant v = Variant.empty("1", "13");
        assertEquals(v, v);
    }

    @Test void variantInstanceIsNotEqualToNull() {
        Variant v = Variant.empty("1", "14");
        assertNotEquals(v, null);
    }

    @Test void objectIsNotEqualToVariant() {
        Object o = new Object();
        Variant v = Variant.empty("3","4");
        assertNotEquals(v,o);
    }

    @Test void variantsWithDifferentIdAndSameLineAreNotEqual(){
        Variant v = Variant.empty("2","4");
        Variant v1 = Variant.empty("4", "4");
        assertNotEquals(v,v1);
    }

    @Test void testVariantOfSameIdInDifferentLinesAreNotEqual() {
        Variant v = Variant.empty("2", "2");
        Variant v1 = Variant.empty("2", "3");
        assertNotEquals(v,v1);
    }

    @Test void variantHasNoTransportSegmentAtCreation(){
        Variant v = Variant.empty("1", "14");
        List<TransportSegment> ts = v.getTransportSegments();
        assertTrue(ts.isEmpty());
    }

    @Test void addANullTransportSegmentThrowsException(){
        Variant v = Variant.empty("1", "14");
        assertThrows(IllegalArgumentException.class,
                () -> v.addTransportSegments(null)
        );
    }

    @Test void addATransportSegmentToVariant(){
        Variant v = Variant.empty("1", "14");
        Stop s1 = Stop.from("s1",GeographicPosition.SOUTH_POLE);
        Stop s2 = Stop.from("s2",GeographicPosition.NORTH_POLE);
        TransportSegment ts = TransportSegment.from(s1, s2, "14","Variant 1", Duration.ZERO, 0.0);
        assertTrue(v.addTransportSegments(ts));
    }

    @Test void addingTwiceTheSameTransportSegmentReturnFalse(){
        Variant v = Variant.empty("1", "14");
        Stop s1 = Stop.from("s1",GeographicPosition.SOUTH_POLE);
        Stop s2 = Stop.from("s2",GeographicPosition.NORTH_POLE);
        TransportSegment ts = TransportSegment.from(s1, s2, "14","Variant 1", Duration.ZERO, 0.0);
        v.addTransportSegments(ts);
        assertFalse(v.addTransportSegments(ts));
    }

    @Test void addNullTimeToVariantThrowsException(){
        Variant v = Variant.empty("1", "14");
        assertThrows(IllegalArgumentException.class,
                () -> v.addTrainDeparture(null)
        );
    }

    @Test void addingTwiceTheSameTimeToVariantReturnFalse(){
        Variant v = Variant.empty("1", "14");
        Time t = new Time(0);
        v.addTrainDeparture(t);
        assertFalse(v.addTrainDeparture(t));
    }

    @Test void getTransportSegments(){
        Variant v = Variant.empty("1", "14");
        Stop s1 = Stop.from("s1",GeographicPosition.SOUTH_POLE);
        Stop s2 = Stop.from("s2",GeographicPosition.NORTH_POLE);
        TransportSegment ts = TransportSegment.from(s1, s2, "14","Variant 1", Duration.ZERO, 0.0);
        List<TransportSegment> res = new ArrayList<TransportSegment>();
        v.addTransportSegments(ts);
        res.add(ts);
        assertEquals(res, v.getTransportSegments());
    }

    @Test void getLineName(){
        Variant v = Variant.empty("1", "14");
        assertEquals("14", v.getLineName());
    }

    @Test void getTrainDepartures(){
        Variant v = Variant.empty("1", "14");
        Time t = new Time(0);
        List<Time> res = new ArrayList<Time>();
        res.add(t);
        v.addTrainDeparture(t);
        assertEquals(res, v.getTrainDepartures());
    }

    @Test void hashCodeOfSemanticallyEqualVariantsAreEqual(){
        Variant v1 = Variant.empty("1", "14");
        Variant v2 = Variant.empty("1", "14");
        Stop s1 = Stop.from("s1",GeographicPosition.SOUTH_POLE);
        Stop s2 = Stop.from("s2",GeographicPosition.NORTH_POLE);
        TransportSegment ts = TransportSegment.from(s1, s2, "14","Variant 1", Duration.ZERO, 0.0);
        v1.addTransportSegments(ts);
        v2.addTransportSegments(ts);
        Time t = new Time(0);
        v1.addTrainDeparture(t);
        v2.addTrainDeparture(t);
        assertEquals(v1.hashCode(), v2.hashCode());
    }

    @Test void getStart(){
        Variant v = Variant.empty("1", "14");
        Stop s1 = Stop.from("s1",GeographicPosition.SOUTH_POLE);
        Stop s2 = Stop.from("s2",GeographicPosition.NORTH_POLE);
        Stop s3 = Stop.from("s3",GeographicPosition.SOUTH_POLE);
        Stop s4 = Stop.from("s4",GeographicPosition.SOUTH_POLE);
        Stop s5 = Stop.from("s5",GeographicPosition.SOUTH_POLE);
        Stop s6 = Stop.from("s6",GeographicPosition.SOUTH_POLE);
        TransportSegment ts1 = TransportSegment.from(s1, s2, "14","Variant 1", Duration.ZERO, 0.0);
        TransportSegment ts2 = TransportSegment.from(s2, s3, "14","Variant 1", Duration.ZERO, 0.0);
        TransportSegment ts3 = TransportSegment.from(s3, s4, "14","Variant 1", Duration.ZERO, 0.0);
        TransportSegment ts4 = TransportSegment.from(s4, s5, "14","Variant 1", Duration.ZERO, 0.0);
        TransportSegment ts5 = TransportSegment.from(s5, s6, "14","Variant 1", Duration.ZERO, 0.0);
        v.addTransportSegments(ts2);
        v.addTransportSegments(ts4);
        v.addTransportSegments(ts5);
        v.addTransportSegments(ts3);
        v.addTransportSegments(ts1);
        assertEquals(s1.getName(),v.getStart().getName());
    }

    @Test void getEnd(){
        Variant v = Variant.empty("1", "14");
        Stop s1 = Stop.from("s1",GeographicPosition.SOUTH_POLE);
        Stop s2 = Stop.from("s2",GeographicPosition.NORTH_POLE);
        Stop s3 = Stop.from("s3",GeographicPosition.SOUTH_POLE);
        Stop s4 = Stop.from("s4",GeographicPosition.SOUTH_POLE);
        Stop s5 = Stop.from("s5",GeographicPosition.SOUTH_POLE);
        Stop s6 = Stop.from("s6",GeographicPosition.SOUTH_POLE);
        TransportSegment ts1 = TransportSegment.from(s1, s2, "14","Variant 1", Duration.ZERO, 0.0);
        TransportSegment ts2 = TransportSegment.from(s2, s3, "14","Variant 1", Duration.ZERO, 0.0);
        TransportSegment ts3 = TransportSegment.from(s3, s4, "14","Variant 1", Duration.ZERO, 0.0);
        TransportSegment ts4 = TransportSegment.from(s4, s5, "14","Variant 1", Duration.ZERO, 0.0);
        TransportSegment ts5 = TransportSegment.from(s5, s6, "14","Variant 1", Duration.ZERO, 0.0);
        v.addTransportSegments(ts2);
        v.addTransportSegments(ts1);
        v.addTransportSegments(ts5);
        v.addTransportSegments(ts3);
        v.addTransportSegments(ts4);
        assertEquals(s6.getName(),v.getEnd().getName());
    }


}
