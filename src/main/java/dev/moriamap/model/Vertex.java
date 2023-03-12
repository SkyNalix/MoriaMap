package dev.moriamap.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Represents a vertex of a graph
 */
public abstract class Vertex {

    // The list of neighbors to which this vertex is linked.
    protected List<Vertex> neighbors;

    /**
     * Builds a Vertex which does not have neighbors.
     */
    public Vertex() {
        this.neighbors = new ArrayList<>();
    }

    /**
     * Gets the list of the neighbors.
     * @return a copy of the neighbor's list of the caller.
     */
    public List<Vertex> getNeighbors() {
        List<Vertex> res = new ArrayList<>();
        Collections.copy(res,this.neighbors);
        return res;
    }

    /**
     * Adds a neighbor to the caller.
     * @param neighbor to be added.
     */
    public void addNeighbor(Vertex neighbor) {

        if (!this.neighbors.contains(neighbor)) {
            this.neighbors.add(neighbor);
        }
    }

    /**
     * Removes a neighbor to the caller.
     * @param neighbor to be removed.
     */
    public void removeNeighbor(Vertex neighbor) {
        this.neighbors.remove(neighbor);
    }
}
