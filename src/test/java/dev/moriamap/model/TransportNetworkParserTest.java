package dev.moriamap.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
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

        TransportNetwork tn = TransportNetworkParser.generate(list); //tn.findLine("Ligne Z").equals(l) 
        assertEquals(tn.findLine("Ligne Z"),l);
    }

    @Test void testFindLineFalse(){

        Duration time = Duration.ofMinutes(3);
        EdgeTuple et1 = new EdgeTuple("A", 1.0, 1.0, 
        "B", 2.0, 2.0, "Ligne Z", "1", time , 7.0);

        List<EdgeTuple> list = new ArrayList<>();
        list.add(et1);
        Line l = Line.of("Ligne Z");
        TransportNetwork tn = TransportNetworkParser.generate(list);

        assertNotEquals(tn.findLine("Ligne Z"),l);
        
    }

    @Test void testFindStopTrue(){
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

        TransportNetwork tn = TransportNetworkParser.generate(list);
        assertTrue(tn.getStopByName("A").equals(s1) && tn.getStopByName("B").equals(s2));
    }

    @Test void testFindStopFalse(){
        Duration time = Duration.ofMinutes(3);
        EdgeTuple et1 = new EdgeTuple("A", 1.0, 1.0, 
        "B", 2.0, 2.0, "Ligne Z", "1", time , 7.0);

        List<EdgeTuple> list = new ArrayList<>();
        list.add(et1);
        TransportNetwork tn = TransportNetworkParser.generate(list);

        Stop s1 = Stop.from("A", GeographicPosition.from("14.0", "1.0"));
        Stop s2 = Stop.from("B", GeographicPosition.from("23.0", "2.0"));


        assertFalse(tn.getStopByName("A").equals(s1) && tn.getStopByName("B").equals(s2) );

    }



}
