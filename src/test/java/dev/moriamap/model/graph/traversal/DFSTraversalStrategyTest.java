package dev.moriamap.model.graph.traversal;

import org.junit.jupiter.api.Test;

import dev.moriamap.model.graph.Edge;
import dev.moriamap.model.graph.Graph;
import dev.moriamap.model.graph.Vertex;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class DFSTraversalStrategyTest {
    private static class DummyVertex implements Vertex {}
    private static class DummyGraph extends Graph {
        void addDummyEdge(DummyEdge de) { super.addEdge(de); }
        void addDummyVertex(DummyVertex dv) { super.addVertex(dv); }
    }
    private static class DummyEdge extends Edge {
        public DummyEdge(DummyVertex from, DummyVertex to) {super(from, to);}
    }

    @Test void traversalWithNullSrcThrowsException() {
        var sut = new DFSTraversalStrategy();
        assertThrows(
          NullPointerException.class,
          () -> sut.traversal(null, null, null, true, null)
        );
    }

    @Test void traversalWithNullDstAndFlagTrueThrowsException() {
        var sut = new DFSTraversalStrategy();
        var dv = new DummyVertex();
        assertThrows(
          NullPointerException.class,
          () -> sut.traversal(dv, null, null, true, null)
        );
    }

    @Test void traversalWithNullDstAndFlagFalseDoesNotThrowNPE() {
        var sut = new DFSTraversalStrategy();
        var src = new DummyVertex();
        var graph = new DummyGraph();
        try {
            sut.traversal(src, null, null, false, graph);
        } catch (NullPointerException npe) {
            fail("Thrown NullPointerException");
        } catch (Throwable t) {}
    }

    @Test void traversalWithNullGraphThrowsException() {
        var sut = new DFSTraversalStrategy();
        var src = new DummyVertex();
        var dst = new DummyVertex();
        assertThrows(
          NullPointerException.class,
          () -> sut.traversal(src, dst, null, false, null)
        );
    }
    
    @Test void traversalWithAbsentSourceThrowsException() {
        var graph = new DummyGraph();
        var absent = new DummyVertex();
        assertThrows(
          NoSuchElementException.class,
          () -> graph.traversal(absent, null, null, false)
        );
    }

    @Test void dsfInGraphWithOnlySourceReturnsEmtpyMap() {
        var graph = new DummyGraph();
        var src = new DummyVertex();
        graph.addDummyVertex(src);
        var sut = new DFSTraversalStrategy();
        graph.setTraversalStrategy(sut);
        assertTrue(graph.traversal(src, null, null, false).isEmpty());
    }

    @Test void dfsInGraphWithOneEdgeReturnsMapWithOneEntry() {
        var sut = new DummyGraph();
        var src = new DummyVertex();
        var dst = new DummyVertex();
        var edge = new DummyEdge(src, dst);
        sut.addDummyEdge(edge);
        assertEquals(edge, sut.traversal(src, null, null, false).get(dst));
    }

    @Test void dfsTraversalContainsExpectedKeys() {
        var graph = new DummyGraph();
        var v1 = new DummyVertex();
        var v2 = new DummyVertex();
        var v3 = new DummyVertex();
        var v4 = new DummyVertex();
        var e14 = new DummyEdge(v1, v4);
        var e43 = new DummyEdge(v4, v3);
        var e21 = new DummyEdge(v2, v1);
        var e24 = new DummyEdge(v2, v4);
        var e32 = new DummyEdge(v3, v2);
        graph.addDummyEdge(e14);
        graph.addDummyEdge(e43);
        graph.addDummyEdge(e21);
        graph.addDummyEdge(e24);
        graph.addDummyEdge(e32);
        var sut = new DFSTraversalStrategy();
        graph.setTraversalStrategy(sut);
        var traversal = graph.traversal(v1, null, null, false);
        var keys = new HashSet<DummyVertex>();
        keys.add(v4);
        keys.add(v3);
        keys.add(v2);
        assertEquals(keys, traversal.keySet());
    }

    @Test void dfsTraversalStopsWhenDestinationIsFound() {
        var graph = new DummyGraph();
        var v1 = new DummyVertex();
        var v2 = new DummyVertex();
        var v3 = new DummyVertex();
        var v4 = new DummyVertex();
        var e14 = new DummyEdge(v1, v4);
        var e43 = new DummyEdge(v4, v3);
        var e21 = new DummyEdge(v2, v1);
        var e24 = new DummyEdge(v2, v4);
        var e32 = new DummyEdge(v3, v2);
        graph.addDummyEdge(e14);
        graph.addDummyEdge(e43);
        graph.addDummyEdge(e21);
        graph.addDummyEdge(e24);
        graph.addDummyEdge(e32);
        var res = new HashSet<>();
        res.add(v4);
        assertEquals(res, graph.traversal(v1, v4, null, true).keySet());
    }

    @Test void dfsTraversalIsEmptyIfSrcEqualsDstAndFlagIsTrue() {
        var graph = new DummyGraph();
        var v1 = new DummyVertex();
        var v2 = new DummyVertex();
        var v3 = new DummyVertex();
        var v4 = new DummyVertex();
        var e14 = new DummyEdge(v1, v4);
        var e43 = new DummyEdge(v4, v3);
        var e21 = new DummyEdge(v2, v1);
        var e24 = new DummyEdge(v2, v4);
        var e32 = new DummyEdge(v3, v2);
        graph.addDummyEdge(e14);
        graph.addDummyEdge(e43);
        graph.addDummyEdge(e21);
        graph.addDummyEdge(e24);
        graph.addDummyEdge(e32);
        assertTrue(graph.traversal(v2, v2, null, true).isEmpty());
    }

    @Test void dfsWhenSrcEqualsDestAndFlagFalseReturnsCompleteTraversal() {
        var graph = new DummyGraph();
        var v1 = new DummyVertex();
        var v2 = new DummyVertex();
        var v3 = new DummyVertex();
        var e12 = new DummyEdge(v1, v2);
        var e23 = new DummyEdge(v2, v3);
        graph.addDummyEdge(e12);
        graph.addDummyEdge(e23);
        Map<Vertex, Edge> parents = graph.traversal(v1, v1, null, false);
        Map<Vertex, Edge> empty = new HashMap<>();
        assertNotEquals(empty, parents);
        List<Edge> route = Graph.getRouteFromTraversal(parents, v2, v3);
        List<Edge> res = new ArrayList<>();
        res.add(e23);
        assertEquals(res, route);
    }
}
