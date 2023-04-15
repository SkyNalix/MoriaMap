package dev.moriamap.model;

import static org.junit.jupiter.api.Assertions.*;
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
		Plan0Query query = new Plan0Query( "Java", "Hoche" );
		assertDoesNotThrow(
				  () -> query.execute( tn )
						  );
		assertThrows( QueryFailureException.class,
					  () -> query.run(tn));
	}

	@Test void targetStopNotFoundTest() {
		Plan0Query query = new Plan0Query( "Lourmel", "Ocaml" );
		assertDoesNotThrow(
				  () -> query.execute( tn )
						  );
		assertThrows( QueryFailureException.class,
					  () -> query.run(tn));
	}

	@Test void noProblemsFoundTest() {
		Plan0Query query = new Plan0Query( "Lourmel", "Hoche" );
		assertDoesNotThrow(
				  () -> query.execute( tn )
						  );
		assertDoesNotThrow( () -> {
			query.run( tn );
		});
	}

	@Test void startStopNameGetterTest() {
		Plan0Query query = new Plan0Query( "Lourmel", "Hoche" );
		assertEquals( "Lourmel", query.startStopName() );
	}

	@Test void targetStopNameGetterTest() {
		Plan0Query query = new Plan0Query( "Lourmel", "Hoche" );
		assertEquals( "Hoche", query.targetStopName() );
	}

}
