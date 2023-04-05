package dev.moriamap;

import java.time.Duration;

import dev.moriamap.model.GeographicPosition;
import dev.moriamap.model.Line;
import dev.moriamap.model.Stop;
import dev.moriamap.model.TransportSegment;
import dev.moriamap.model.Variant;

class Main {

    private static TransportNetwork createTransportNetwork(){
        Line l1 = Line.of("Ligne 1");
        Line l2 = Line.of("Ligne 2");
        Line l3 = Line.of("Ligne 3");
        Line l4 = Line.of("Ligne 4");

        Variant v1l1 = Variant.empty("Variant 1", "Ligne 1");
        Variant v2l1 = Variant.empty("Variant 2", "Ligne 1");
        Variant v1l2 = Variant.empty("Variant 1", "Ligne 2");
        Variant v2l2 = Variant.empty("Variant 2", "Ligne 2");
        Variant v1l3 = Variant.empty("Variant 1", "Ligne 3");
        Variant v2l3 = Variant.empty("Variant 2", "Ligne 3");
        Variant v1l4 = Variant.empty("Variant 1", "Ligne 4");
        Variant v2l4 = Variant.empty("Variant 2", "Ligne 5");

        Stop s1 = Stop.from("s1", GeographicPosition.at(0.0, -1.0));
        Stop s2 = Stop.from("s2", GeographicPosition.at(1.0, -1.0));
        Stop s3 = Stop.from("s3", GeographicPosition.at(0.0, 0.0));
        Stop s4 = Stop.from("s4", GeographicPosition.at(-1.0, 0.0));
        Stop s5 = Stop.from("s5", GeographicPosition.at(0.0, 1.0));
        Stop s6 = Stop.from("s6", GeographicPosition.at(1.0, 0.0));
        Stop s7 = Stop.from("s7", GeographicPosition.at(2.0, -1.0));
        Stop s8 = Stop.from("s8", GeographicPosition.at(2.0, -2.0));
        Stop s9 = Stop.from("s9", GeographicPosition.at(2.0, 1.0));
        Stop s10 = Stop.from("s10", GeographicPosition.at(-2.0, 1.0));

        //Line 1 Variant 1
        TransportSegment s10Tos2 = TransportSegment.from(s10, s2, "Ligne 1", "Variant 1", Duration.ofMinutes(2), 5);
        TransportSegment s2Tos1 = TransportSegment.from(s2, s1, "Ligne 1", "Variant 1", Duration.ofMinutes(4), 10);
        TransportSegment s1Tos4 = TransportSegment.from(s1, s4, "Ligne 1", "Variant 1", Duration.ofMinutes(1), 3);
        //Line 1 Variant 2
        TransportSegment s4Tos1 = TransportSegment.from(s4, s1, "Ligne 1", "Variant 1", Duration.ofMinutes(1), 3);
        TransportSegment s1Tos2 = TransportSegment.from(s1, s2, "Ligne 1", "Variant 1", Duration.ofMinutes(4), 10);
        TransportSegment s2Tos10 = TransportSegment.from(s2, s10, "Ligne 1", "Variant 1", Duration.ofMinutes(2), 5);

        //Line 2 Variant 1
        TransportSegment s5Tos3 = TransportSegment.from(s5, s3, "Ligne 2", "Variant 1", Duration.ofMinutes(2), 5);
        TransportSegment s6Tos4 = TransportSegment.from(s6, s4, "Ligne 2", "Variant 1", Duration.ofMinutes(3), 8);
        //Line 2 Variant 2
        TransportSegment s4Tos6 = TransportSegment.from(s4, s6, "Ligne 2", "Variant 2", Duration.ofMinutes(3), 8);
        TransportSegment s3Tos5 = TransportSegment.from(s3, s5, "Ligne 2", "Variant 2", Duration.ofMinutes(2), 5);

        //Line 3 Variant 1
        TransportSegment s1Tos3 = TransportSegment.from(s1, s3, "Ligne 3", "Variant 1", Duration.ofMinutes(1), 3);
        TransportSegment s6Tos7 = TransportSegment.from(s6, s7, "Ligne 3", "Variant 1", Duration.ofMinutes(3), 8);
        TransportSegment s7Tos8 = TransportSegment.from(s7, s8, "Ligne 3", "Variant 1", Duration.ofMinutes(2), 5);

        //Line 3 Variant 2
        TransportSegment s8Tos7 = TransportSegment.from(s8, s7, "Ligne 3", "Variant 2", Duration.ofMinutes(2), 5);
        TransportSegment s7Tos6 = TransportSegment.from(s7, s6, "Ligne 3", "Variant 2", Duration.ofMinutes(3), 8);
        TransportSegment s3Tos1 = TransportSegment.from(s3, s1, "Ligne 3", "Variant 2", Duration.ofMinutes(1), 3);

        //Line 4 Variant 1
        TransportSegment s5Tos9 = TransportSegment.from(s5, s9, "Ligne 4", "Variant 1", Duration.ofMinutes(3), 8);
        TransportSegment s9Tos8 = TransportSegment.from(s9, s8, "Ligne 4", "Variant 1", Duration.ofMinutes(1), 3);

        //Line 4 Variant 2
        TransportSegment s8Tos9 = TransportSegment.from(s8, s9, "Ligne 4", "Variant 2", Duration.ofMinutes(1), 3);
        TransportSegment s9Tos5 = TransportSegment.from(s9, s5, "Ligne 4", "Variant 2", Duration.ofMinutes(3), 8);

        //TransportSegment shared between line 2 and line 3
        TransportSegment s6Tos3 = TransportSegment.from(s6, s3, "Ligne 2", "Variant 2", Duration.ofMinutes(1), 3);
        TransportSegment s3Tos6 = TransportSegment.from(s3, s6, "Ligne 2", "Variant 1", Duration.ofMinutes(1), 3);

        //Add the Transport Segments to the variants.
        v1l1.addTransportSegment(s10Tos2);
        v1l1.addTransportSegment(s2Tos1);
        v1l1.addTransportSegment(s1Tos4);

        v2l1.addTransportSegment(s4Tos1);
        v2l1.addTransportSegment(s1Tos2);
        v2l1.addTransportSegment(s2Tos10);

        v1l2.addTransportSegment(s5Tos3);
        v1l2.addTransportSegment(s3Tos6);
        v1l2.addTransportSegment(s6Tos4);

        v2l2.addTransportSegment(s4Tos6);
        v2l2.addTransportSegment(s6Tos3);
        v2l2.addTransportSegment(s3Tos5);

        v1l3.addTransportSegment(s1Tos3);
        v1l3.addTransportSegment(s3Tos6);
        v1l3.addTransportSegment(s6Tos7);
        v1l3.addTransportSegment(s7Tos8);

        v2l3.addTransportSegment(s8Tos7);
        v2l3.addTransportSegment(s7Tos6);
        v2l3.addTransportSegment(s6Tos3);
        v2l3.addTransportSegment(s3Tos1);

        v1l4.addTransportSegment(s5Tos9);
        v1l4.addTransportSegment(s9Tos8);

        v2l4.addTransportSegment(s8Tos9);
        v2l4.addTransportSegment(s9Tos5);

        //Add variants to lines
        l1.addVariant(v1l1);
        l1.addVariant(v1l2);

        l2.addVariant(v1l2);
        l2.addVariant(v2l2);

        l3.addVariant(v1l3);
        l3.addVariant(v2l3);

        l4.addVariant(v1l4);
        l4.addVariant(v2l4);

        //TODO Create TransportSchedules and the TransportNetwork when we have it
        return null;

    }
	public static void main(String[] args) {
        throw new UnsupportedOperationException("Can not run");
    }
}
