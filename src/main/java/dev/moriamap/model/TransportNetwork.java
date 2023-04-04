package dev.moriamap.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

// TODO: tests, SonarQube

public final class TransportNetwork extends Graph {
    private final List<Line> lines;

    private TransportNetwork() {
        super();
        this.lines = new ArrayList<>();
    }

    /**
     * {@return a new empty TransportNetwork with no lines, no stops and no transport segments}
     */
    public static TransportNetwork empty() {
        return new TransportNetwork();
    }

    /**
     * {@return the Stop at position gp in the transport network,
     * or null if not found}
     * @param gp the geographic position of the wanted stop
     * @throws IllegalArgumentException if gp is null
     */
    public Stop getStopFromPosition(GeographicPosition gp) {
        if (gp == null) throw new IllegalArgumentException();
        for (Stop s : this.getStops()) {
            if (s.getGeographicPosition().equals(gp))
                return s;
        }
        return null;
    }

    /**
     * {@return the Stop which has the wanted name in the network,
     * or null if it is not found}
     * @param name the name of the wanted stop
     * @throws IllegalArgumentException if name is null
     */
    public Stop getStopByName(String name) {
        if (name == null) throw new IllegalArgumentException();
        for (Stop s : this.getStops()) {
            if (s.getName().equals(name))
                return s;
        }
        return null;
    }

    /**
     * {@return the lines (e.g. bus, tram) of this network}
     */
    public List<Line> getLines() {
        return new ArrayList<>(lines);
    }

    /**
     * {@return all the variants of all the lines of this TransportNetwork}
     */
    public List<Variant> getVariants() {
        List<Variant> variants = new ArrayList<>();
        for (Line l : lines) variants.addAll(l.getVariants());
        return variants;
    }

    /**
     * {@return all the stops of this TransportNetwork}
     */
    public List<Stop> getStops() {
        List<Vertex> vertices = this.getVertices();
        List<Stop> stops = new ArrayList<>();
        for (Vertex v : vertices) stops.add((Stop)v);
        return stops;
    }

    /**
     * Add a line to the TransportNetwork
     * {@return true if added, false if already present}
     * @param l the Line to add
     */
    public boolean addLine(Line l) {
        if (lines.contains(l)) return false;
        return lines.add(l);
    }

    /**
     * {@return the Stop in the TransportNetwork equal to stop or null if not found}
     * @param stop the stop to find by equals(Object)
     */
    public Stop findStop(Stop stop) {
        if (stop == null) throw new IllegalArgumentException();
        for (Stop s : this.getStops())
            if (s.equals(stop))
                return s;
        return null;
    }

    /**
     * {@return the Line whose name is name or null if not found}
     * @param name the name of the Line
     */
    public Line findLine(String name) {
        if (name == null) throw new IllegalArgumentException();
        for (Line l : this.getLines())
            if (l.getName().equals(name))
                return l;
        return null;
    }

    /**
     * Add a Stop to this TransportNetwork. This will actually add a Vertex
     * to the Graph.
     * @param stop the Stop to add
     */
    public void addStop(Stop stop) {
        this.addVertex(stop);
    }

    /**
     * Add a TransportSegment to this TransportNetwork. This will actually
     * add an Edge to the Graph.
     * @param transportSegment the TransportSegment to add
     */
    public void addTransportSegment(TransportSegment transportSegment) {
        this.addEdge(transportSegment);
    }
}
