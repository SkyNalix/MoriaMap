package dev.moriamap.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
class PrettyPrinterTest {

	TransportNetwork tn;
	{
		try {
			tn = TransportNetworkParser.generateFrom(CSVParserTest.class.getResourceAsStream("/test_map_data.csv"));
		} catch (InconsistentCSVException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void emptyListTest() {
		assertEquals("",PrettyPrinter.printTransportSegmentPath(tn, new ArrayList<>() ));
	}

	@Test
	void nonEmptyListGiveNonEmptyStringTest() {
		Stop start = tn.getStopByName( "Lourmel" );
		Stop target = tn.getStopByName( "Hoche" );
		Map<Vertex, Edge> dfs = tn.depthFirstSearch( start );
		List<Edge> path = Graph.getRouteFromTraversal( dfs, start, target );
		assertNotEquals("", PrettyPrinter.printTransportSegmentPath(tn, path ));
	}

	@Test
	void printTransportSegmentPathWithLineChangeTimesTest() {
		Stop s1 = Stop.from("s1", GeographicPosition.SOUTH_POLE);
		Stop s2 = Stop.from("s2", GeographicPosition.NORTH_POLE);
		Stop s3 = Stop.from("s3", GeographicPosition.NORTH_POLE);

		TransportSegment ts1 = TransportSegment.from( s1, s2, "7B", "1",
													  Duration.ofMinutes( 2 ), 4 );
		TransportSegment ts2 = TransportSegment.from(s2, s3, "14", "1",
													 Duration.ofMinutes( 2 ), 4);

		Variant v1 = Variant.empty("1", "7B");
		v1.addTransportSegment(ts1);
		Variant v2 = Variant.empty("1", "14");
		v2.addTransportSegment(ts2);

		Line l = Line.of("7B");
		l.addVariant(v1);
		Line l2 = Line.of("14");
		l2.addVariant( v2 );

		TransportNetwork tn = TransportNetwork.empty();
		tn.addStop(s1);
		tn.addStop(s2);
		tn.addStop(s3);
		tn.addTransportSegment(ts1);
		tn.addTransportSegment(ts2);
		tn.addLine(l);
		tn.addLine(l2);

		tn.getVariants().get(0).addDeparture( LocalTime.of( 0, 1, 0 ) );
		tn.getVariants().get(1).addDeparture( LocalTime.of( 0,7,0 ) );

		List<Edge> route = new ArrayList<>();
		route.add(ts1);
		route.add(ts2);

		assertNotEquals("", tn.getRouteDescription(route, LocalTime.MIN));
	}

}
