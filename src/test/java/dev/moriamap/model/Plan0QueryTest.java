package dev.moriamap.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
class Plan0QueryTest {

	TransportNetwork tn;
	{
		try {
			tn = TransportNetworkParser.generateFrom(CSVParserTest.class.getResourceAsStream("/test_map_data.csv"));
		} catch (InconsistentCSVException e) {
			throw new RuntimeException(e);
		}
	}

	@Test void startingStopNotFoundTest() {
		assertDoesNotThrow( () -> {
			Plan0Query query = new Plan0Query( "Java", "Hoche" );
			query.execute( tn );
		});
	}

	@Test void targetStopNotFoundTest() {
		assertDoesNotThrow( () -> {
			Plan0Query query = new Plan0Query( "Lourmel", "Ocaml" );
			query.execute( tn );
		});
	}

	@Test void noProblemsFoundTest() {
		assertDoesNotThrow( () -> {
			Plan0Query query = new Plan0Query( "Lourmel", "Hoche" );
			query.execute( tn );
		});
	}

}
