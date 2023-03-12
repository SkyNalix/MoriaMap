package dev.moriamap.model;

import java.util.ArrayList;
import java.util.List;

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
    public Graph() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    /**
     * Class constructor specifying vertices and edges.
     *
     * @param vertices the vertices of this new Graph
     * @param edges the edges of this new Graph
     */
    public Graph(List<Vertex> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
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
     *
     * @param e the edge to be added
     * @return true if the edge was added
     */
    public boolean addEdge(Edge e) {
        if (this.edges.contains(e)) {
            return false;
        }

        if (e == null) {
            throw new IllegalArgumentException("null edge is not allowed.");
        }

        if (this.vertices.contains(e.getFrom()) && this.vertices.contains(e.getTo())) {
            this.edges.add(e);
            return true;
        }

        if (!this.vertices.contains(e.getFrom()) && !(this.vertices.contains(e.getTo()))) {
            this.addVertex(e.getFrom());
            this.addVertex(e.getTo());
        }

        if (this.vertices.contains(e.getFrom())) {
            this.addVertex(e.getTo());
        }

        if (this.vertices.contains(e.getTo())) {
            this.addVertex(e.getFrom());
        }

        e.getFrom().addNeighbor(e.getTo());
        e.getTo().addNeighbor(e.getFrom());
        return this.edges.add(e);
    }
}
