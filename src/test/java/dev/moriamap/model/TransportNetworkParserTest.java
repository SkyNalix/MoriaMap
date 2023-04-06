package dev.moriamap.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TransportNetworkParserTest {
    
    @Test void testFindLineTrue(){
        Duration time = Duration.ofMinutes(3);
        EdgeTuple et1 = new EdgeTuple("A", 1.0, 1.0, 
        "B", 2.0, 2.0, "Ligne Z", "1", time , 7.0);

        List<EdgeTuple> list = new ArrayList<>();
        list.add(et1);
        Line l = Line.of("Ligne Z");
        Variant v = Variant.empty("1",  "Ligne Z");
        l.addVariant(v);



        Stop s1 = Stop.from("A", GeographicPosition.from("1.0", "1.0"));
        Stop s2 = Stop.from("B", GeographicPosition.from("2.0", "2.0"));

        TransportSegment seg = TransportSegment.from(s1, s2,"Ligne Z","1", time, 7.0);
        v.addTransportSegment(seg);

        TransportNetwork TN = TransportNetworkParser.procedure(list);
        assertTrue(TN.findLine("Ligne Z").equals(l) );
    }

    @Test void testFindLineFalse(){

        Duration time = Duration.ofMinutes(3);
        EdgeTuple et1 = new EdgeTuple("A", 1.0, 1.0, 
        "B", 2.0, 2.0, "Ligne Z", "1", time , 7.0);

        List<EdgeTuple> list = new ArrayList<>();
        list.add(et1);
        Line l = Line.of("Ligne Z");
        TransportNetwork TN = TransportNetworkParser.procedure(list);

        assertFalse(TN.findLine("Ligne Z").equals(l) );
        
    }

    @Test void FindStopTrue(){
        Duration time = Duration.ofMinutes(3);
        EdgeTuple et1 = new EdgeTuple("A", 1.0, 1.0, 
        "B", 2.0, 2.0, "Ligne Z", "1", time , 7.0);

        List<EdgeTuple> list = new ArrayList<>();
        list.add(et1);
        Line l = Line.of("Ligne Z");
        Variant v = Variant.empty("1",  "Ligne Z");
        l.addVariant(v);



        Stop s1 = Stop.from("A", GeographicPosition.from("1.0", "1.0"));
        Stop s2 = Stop.from("B", GeographicPosition.from("2.0", "2.0"));

        TransportSegment seg = TransportSegment.from(s1, s2,"Ligne Z","1", time, 7.0);
        v.addTransportSegment(seg);

        TransportNetwork TN = TransportNetworkParser.procedure(list);
        assertTrue(TN.getStopByName("A").equals(s1) && TN.getStopByName("B").equals(s2));
    }

    @Test void FindStopFalse(){
        Duration time = Duration.ofMinutes(3);
        EdgeTuple et1 = new EdgeTuple("A", 1.0, 1.0, 
        "B", 2.0, 2.0, "Ligne Z", "1", time , 7.0);

        List<EdgeTuple> list = new ArrayList<>();
        list.add(et1);
        TransportNetwork TN = TransportNetworkParser.procedure(list);

        Stop s1 = Stop.from("A", GeographicPosition.from("14.0", "1.0"));
        Stop s2 = Stop.from("B", GeographicPosition.from("23.0", "2.0"));


        assertFalse(TN.getStopByName("A").equals(s1) && TN.getStopByName("B").equals(s2) );

    }



}
