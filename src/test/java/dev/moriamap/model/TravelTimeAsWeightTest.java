package dev.moriamap.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;

class TravelTimeAsWeightTest {

	@Test
	void nullStartTimeArgTest() {
		var tn = TransportNetwork.empty();
		assertThrows(IllegalArgumentException.class,
					 () -> new TravelTimeAsWeight(null,tn));
	}

	@Test
	void nullNetworkArgTest() {
		assertThrows(IllegalArgumentException.class,
					 () -> new TravelTimeAsWeight( LocalTime.MIN, null) );
	}

	@Test
	void applyThrowUnsupportedOperationExceptionTest() {
		var tn = TransportNetwork.empty();
		var travelTimeAsWeight = new TravelTimeAsWeight( LocalTime.MIN, tn);
		Stop s1 = Stop.from( "s1", GeographicPosition.NORTH_POLE );
		Stop s2 = Stop.from( "s2", GeographicPosition.NORTH_POLE );
		Edge dummyEdge = new Edge(s1,s2) {
			@Override
			public Vertex getFrom() {
				return super.getFrom();
			}

			@Override
			public Vertex getTo() {
				return super.getTo();
			}
		};
		assertThrows(UnsupportedOperationException.class,
					 () -> travelTimeAsWeight.apply( 0.0, dummyEdge ));
	}

	@Test
	void applyTest() {

		Stop s1 = Stop.from("s1", GeographicPosition.SOUTH_POLE);
		Stop s2 = Stop.from("s2", GeographicPosition.NORTH_POLE);

		TransportSegment ts1 = TransportSegment.from( s1, s2, "7B", "1",
													  Duration.ofMinutes( 3 ), 4 );
		TransportSegment ts2 = TransportSegment.from(s2, s1, "7B", "2",
													 Duration.ofMinutes( 4 ), 4);

		Variant v1 = Variant.empty("1", "7B");
		v1.addTransportSegment(ts1);
		Variant v2 = Variant.empty("2", "7B");
		v2.addTransportSegment(ts2);

		Line l = Line.of("7B");
		l.addVariant(v1);
		l.addVariant(v2);

		TransportNetwork tn = TransportNetwork.empty();
		tn.addStop(s1);
		tn.addStop(s2);
		tn.addTransportSegment(ts1);
		tn.addTransportSegment(ts2);
		tn.addLine(l);

		v1.addDeparture( LocalTime.of( 0,0,4 ) );

		var travelTimeAsWeight = new TravelTimeAsWeight( LocalTime.MIN, tn);
		assertEquals(184.0, travelTimeAsWeight.apply( 0.0, ts1 ));
	}

}
