package dev.moriamap.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Represents a Graph with a list of vertices and a list of edges.
 */
public abstract class Graph {

    /**
     * The vertices of this graph.
     */
    protected List<Vertex> vertices;

    /**
     * The edges of this graph.
     */
    protected List<Edge> edges;

    /**
     * Builds an empty Graph.
     * This graph does not have vertices nor edges.
     */
    protected Graph() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    /**
     * {@return the vertices of this Graph}
     */
    public List<Vertex> getVertices() {
        List<Vertex> res = new ArrayList<>(this.vertices.size());
        for (Vertex v: this.vertices) {
            res.add(v);
        }
        return res;
    }

    /**
     * {@return the edges of this Graph}
     */
    public List<Edge> getEdges() {
        List<Edge> res = new ArrayList<>(this.edges.size());
        for (Edge e: this.edges) {
            res.add(e);
        }
        return res;
    }

    /**
     * Adds the given vertex to this Graph.
     *
     * @param v the vertex to be added
     * @return true if the vertex was added
     * @throws IllegalArgumentException if the given vertex is null
     */
    public boolean addVertex(Vertex v) {
        if(this.vertices.contains(v)) {
            return false;
        }

        if (v == null) {
            throw new IllegalArgumentException("null vertex is not allowed.");
        }
        return this.addVertex(v);
    }

    /**
     * Adds the given edge to this Graph.
     * If the graph does not contain vertices of the given edge, we add them.
     * Neighbor relationship between vertices of the given edge is not modified.
     *
     * @param e the edge to be added
     * @return true if the edge was added
     * @throws IllegalArgumentException if the given edge is null
     */
    public boolean addEdge(Edge e) {
        if (this.edges.contains(e)) {
            return false;
        }

        if (e == null) {
            throw new IllegalArgumentException("null edge is not allowed.");
        }

        this.addVertex(e.getFrom());
        this.addVertex(e.getTo());
        e.getFrom().addNeighbor(e.getTo());
        return this.edges.add(e);
    }

    /**
     * Returns the list of outgoing edges of the specified Vertex.
     * @param vertex some Vertex
     * @return the list of outgoing edges of vertex
     * @throws IllegalArgumentException if vertex is null
     * @throes NoSuchElementException if vertex is not in this Graph
     */
    public List<Edge> getOutgoingEdgesOf(Vertex vertex) {
        if (vertex == null)
            throw new IllegalArgumentException("Vertex is null");
        if (!this.vertices.contains(vertex))
            throw new NoSuchElementException("Vertex not in graph");
        return new ArrayList<>();
    }
}
