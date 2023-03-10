package dev.moriamap.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a vertex of a graph
 */
public abstract class Vertex {

    protected List<Vertex> neighbors;

    /**
     * Builds a Vertex which does not have neighbors.
     */
    public Vertex() {
        this.neighbors = new ArrayList<>();
    }

    /**
     * Neighbors getter.
     * @return the neighbor's list of the caller.
     */
    public List<Vertex> getNeighbors() {
        return this.neighbors;
    }

    /**
     * Adds a neighbor to the caller.
     * @param neighbor to be added.
     */
    public void addNeighbor(Vertex neighbor) {
        this.neighbors.add(neighbor);
    }

    /**
     * Removes a neighbor to the caller.
     * @param neighbor to be removed.
     */
    public void removeNeighbor(Vertex neighbor) {
        this.neighbors.remove(neighbor);
    }
}
