package dev.moriamap.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EdgeTupleTest {

    @Test void returnedListHasExpectedSize() throws IOException, InconsistentCSVLinesException {
        List<List<String>> lines = CSVParser.extractLines("/map_data.csv");
        List<EdgeTuple> liste  = EdgeTuple.fromCSVLines(lines);
        assertEquals(1770,liste.size());
    }
}
