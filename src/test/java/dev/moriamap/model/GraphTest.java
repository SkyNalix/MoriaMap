package dev.moriamap.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

class GraphTest {
    static class DummyGraph extends Graph {}
    static class DummyVertex implements Vertex {}
    static class DummyEdge extends Edge {
        public DummyEdge() { super(new DummyVertex(), new DummyVertex()); }
        public DummyEdge(Vertex from, Vertex to) { super(from, to); }
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

    @Test void getOutgoindEdgesOfNullVertexThrowsIllegalArgumentException() {
        Graph sut = new DummyGraph();
        assertThrows(
            IllegalArgumentException.class,
            () -> sut.getOutgoingEdgesOf(null)
        );
    }

    @Test void getOutgoingEdgesOfAbsentVertexThrowsIllegalArgumentException() {
        Graph sut = new DummyGraph();
        Vertex dummy = new DummyVertex();
        assertThrows(
            NoSuchElementException.class,
            () -> sut.getOutgoingEdgesOf(dummy)
        );
    }

    @Test void getOutgoingEdgesOfVertexWithoutOutgoingEdgesIsEmptyList() {
        Graph sut = new DummyGraph();
        Vertex from = new DummyVertex();
        sut.addVertex(from);
        assertTrue(sut.getOutgoingEdgesOf(from).isEmpty());
    }

    @Test void getOutgoingEdgesOfVertexWithOneOutgoingEdgesReturnsSuchList() {
        Graph sut = new DummyGraph();
        Vertex from = new DummyVertex();
        Edge edge = new DummyEdge(from, new DummyVertex());
        sut.addEdge(edge);
        List<Edge> outgoingEdges = new ArrayList<>();
        outgoingEdges.add(edge);
        assertEquals(outgoingEdges, sut.getOutgoingEdgesOf(from));
    }

    @Test void testIfVertexIsInGraphWithNullVertexThrowsException() {
        Graph sut = new DummyGraph();
        assertThrows(IllegalArgumentException.class, () -> sut.contains(null));
    }

    @Test void testIfVertexIsInGraphWhenAbsentReturnsFalse() {
        Graph sut = new DummyGraph();
        Vertex from = new DummyVertex();
        assertFalse(sut.contains(from));
    }

    @Test void testIfVertexIsInGraphWhenPresentReturnsTrue() {
        Graph sut = new DummyGraph();
        Vertex v1 = new DummyVertex();
        Vertex v2 = new DummyVertex();
        Vertex v3 = new DummyVertex();        
        sut.addVertex(v1);
        sut.addVertex(v2);
        sut.addVertex(v3);
        assertTrue(sut.contains(v2));
    }

    @Test void routeFromTraversalWithNullParentsThrowsException() {
        Vertex u = new DummyVertex();
        Vertex v = new DummyVertex();
        assertThrows(
          IllegalArgumentException.class,
          () -> Graph.getRouteFromTraversal(null, v, u)
        );
    }

    @Test void routeFromTraversalWithNullSourceThrowsException() {
        Map<Vertex, Edge> parents = new HashMap<>();
        Vertex u = new DummyVertex();
        assertThrows(
          IllegalArgumentException.class,
          () -> Graph.getRouteFromTraversal(parents, null, u)
        );
    }

    @Test void routeFromTraversalWithNullDestinationThrowsException() {
        Map<Vertex, Edge> parents = new HashMap<>();
        Vertex u = new DummyVertex();
        assertThrows(
          IllegalArgumentException.class,
          () -> Graph.getRouteFromTraversal(parents, u, null)
        );
    }

    @Test void routeFromTraversalWithAbsentDestinationThrowsException() {
        Map<Vertex, Edge> parents = new HashMap<>();
        Vertex u = new DummyVertex();
        Vertex v = new DummyVertex();
        assertThrows(
          NoSuchElementException.class,
          () -> Graph.getRouteFromTraversal(parents, u, v)
        );
    }

    @Test void routeFromTraversalWithAbsentSourceThrowsException() {
        Map<Vertex, Edge> parents = new HashMap<>();
        Vertex u = new DummyVertex();
        Vertex v = new DummyVertex();
        parents.put(v, new DummyEdge());
        assertThrows(
          NoSuchElementException.class,
          () -> Graph.getRouteFromTraversal(parents, u, v)
        );
    }

    @Test void routeFromCorrectTraversalReturnsCorrectRoute() {
        Map<Vertex, Edge> parents = new HashMap<>();
        Vertex v1 = new DummyVertex();
        Vertex v2 = new DummyVertex();
        Vertex v3 = new DummyVertex();
        Edge e1 = new DummyEdge(v1, v2);
        Edge e2 = new DummyEdge(v2, v3);
        parents.put(v2, e1);
        parents.put(v3, e2);
        List<Edge> route = new ArrayList<>();
        route.add(e1);
        route.add(e2);
        assertEquals(route, Graph.getRouteFromTraversal(parents, v1, v3));
    }
}
