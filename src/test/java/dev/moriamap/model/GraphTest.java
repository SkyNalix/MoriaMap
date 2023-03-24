package dev.moriamap.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class GraphTest {
    class DummyGraph extends Graph {}
    class DummyVertex extends Vertex {}
    class DummyEdge extends Edge {
        public DummyEdge() { super(new DummyVertex(), new DummyVertex()); }
        public double getWeight() { return 0; }
    }

    @Test void verticesOfNewGraphIsEmptyList() {
        Graph sut = new DummyGraph();
        assertTrue(sut.getVertices().isEmpty());
    }

    @Test void edgesOfNewGraphIsEmptyList() {
        Graph sut = new DummyGraph();
        assertTrue(sut.getEdges().isEmpty());
    }

    @Test void addNullVertexThrowsException() {
        Graph sut = new DummyGraph();
        assertThrows(
            IllegalArgumentException.class,
            () -> sut.addVertex(null)
        );
    }

    @Test void verticesOfGraphWithOneVertexReturnsVertex() {
        Graph sut = new DummyGraph();
        Vertex vertex = new DummyVertex();
        sut.addVertex(vertex);
        assertTrue(sut.getVertices().contains(vertex));
    }

    @Test void addNullEdgeThrowsException() {
        Graph sut = new DummyGraph();
        assertThrows(IllegalArgumentException.class, () -> sut.addEdge(null));
    }

    @Test void addEdgeWithTwoNewVerticesAddsTwoNewVertices() {
        Graph sut = new DummyGraph();
        Edge edge = new DummyEdge();
        sut.addEdge(edge);
        assertEquals(2, sut.getVertices().size());
    }
}
