package dev.moriamap.model;

import java.util.*;

/**
 * A Vertex is the fundamental unit of which graphs are formed.
 */
public abstract class Vertex {

    /**
     * The vertices for which an edge has them as destination and this Vertex as
     * source.
     */
    protected List<Vertex> neighbors;

    /**
     * The edges that have this Vertex as source.
     */
    protected List<Edge> outgoingEdges;

    /**
     * Class constructor without neighbors.
     */
    public Vertex() {
        this.neighbors = new ArrayList<>();
        this.outgoingEdges = new ArrayList<>();
    }

    /**
     * {@return the neighbors of this Vertex}
     */
    public List<Vertex> getNeighbors() {
        List<Vertex> res = new ArrayList<>(this.neighbors.size());
        for (Vertex v: this.neighbors) 
            res.add(v);
        return res;
    }

    /**
     * {@return the outgoing edges of this Vertex}
     */
    public List<Edge> getOutgoindEdges() {
        List<Edge> list = new ArrayList<>(this.outgoingEdges.size());
        for (Edge edge: this.outgoingEdges)
            list.add(edge);
        return list;
    }

    /**
     * Adds the specified Vertex to this Vertex neighbors if it is not already
     * a neighbor.
     * @param neighbor the Vertex to add to this Vertex neighbors
     * @return true if the specified vertex was added to this Vertex neighbors
     * @throws IllegalArgumentException if specified Vertex is this Vertex
     * @throws NullPointerException if specified Vertex is null
     */
    public boolean addNeighbor(Vertex neighbor) {
        if (this == neighbor)
            throw new IllegalArgumentException(
              "This can not be a neighbor of itself"
            );
        if (neighbor == null)
            throw new IllegalArgumentException("Neighbor can not be null");
        if (!this.neighbors.contains(neighbor))
            return this.neighbors.add(neighbor);
        return false;
    }

    /**
     * Adds the specified Edge as an outgoing edge of this Vertex if it is not
     * already an outgoing edge.
     * @param edge the Edge to add as an outgoing edge
     * @return true if the specified Edge was added
     * @throws IllegalArgumentException if edge is null or is an outgoing edge
     */
    public boolean addOutgoingEdge(Edge edge) {
        if (!edge.getFrom().equals(this))
            throw new IllegalArgumentException("Not an outgoing edge");
        if (edge == null)
            throw new IllegalArgumentException("Outgoing edge can not be null");
        if (!this.outgoingEdges.contains(edge))
            return this.outgoingEdges.add(edge);
        return false;
    }

    /**
     * Removes the specified vertex from the neighbors of this Vertex.
     * @param neighbor the Vertex to remove
     * @return true if the specified Vertex was removed
     */
    public boolean removeNeighbor(Vertex neighbor) {
        return this.neighbors.remove(neighbor);
    }

    /**
     * Removes the specified Edge from the outgoing edges of this Vertex.
     * @param edge the Edge to remove
     * @return true if the specified Edge was removed
     */
    public boolean removeOutgoingEdge(Edge edge) {
        return this.outgoingEdges.remove(edge);
    }
}
