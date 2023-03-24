package dev.moriamap.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.NoSuchElementException;

class GraphTest {
    class DummyGraph extends Graph {}
    class DummyVertex extends Vertex {}

    @Test void getOutgoingEdgesOfNullVertexThrowsException() {
        Graph g = new DummyGraph();
        assertThrows(
            IllegalArgumentException.class,
            () -> g.getOutgoingEdgesOf(null)
        );
    }

    @Test void getOutgoingEdgesOfNotContainedVertexThrowsException() {
        Graph g = new DummyGraph();
        assertThrows(
            NoSuchElementException.class,
            () -> g.getOutgoingEdgesOf(new DummyVertex())
        );
    }
}
