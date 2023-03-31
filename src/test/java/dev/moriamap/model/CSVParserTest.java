package dev.moriamap.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.*;

class CSVParserTest {
    @Test void extractLinesThrowsExceptionWhenArgumentIsNull() {
        assertThrows(IllegalArgumentException.class,() ->{
            CSVParser.extractLines(null);
        });
    }
    @Test void extractedInconsistantDataThrowException() {
        assertThrows(InconsistentCSVLineException.class,() ->{
            CSVParser.extractLines("/InconsistantCsv.csv");
        });
    }

    @Test void parserExtractsExceptedNumberoFines() throws InconsistentCSVLineException {
        List<List<String>> lines = CSVParser.extractLines("/map_data.csv");
        assertEquals(1770,lines.size());
    }

    @Test void parseLineThrowsExceptionIfDelimiterIsNull() {
        assertThrows(IllegalArgumentException.class,()->CSVParser.parseCSVLine("1;1",null));
    }

    @Test void parseLineThrowsExceptionIfDelimiterIsEmptyString() {
        assertThrows(IllegalArgumentException.class,()->CSVParser.parseCSVLine("1;1",""));
    }

    @Test void parseLineThrowsExceptionIfLineIsNull() {
        assertThrows(IllegalArgumentException.class,()->CSVParser.parseCSVLine(null,""));
    }

    @Test void parseLineThrowsExceptionIfLineIsEmpty() {
        assertThrows(IllegalArgumentException.class,()->CSVParser.parseCSVLine("",";"));
    }
}
