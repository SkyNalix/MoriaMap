package dev.moriamap.model;

import java.util.function.*;
import java.util.*;

public class DistanceAsWeight implements BiFunction<Double, Edge, Double> {

    @Override public Double apply(Double current, Edge edge) {
        Objects.requireNonNull(current);
        Objects.requireNonNull(edge);
        if (edge instanceof TransportSegment ts) {
            return ts.getDistance();
        }
        throw new IllegalArgumentException("Edge is not TransportSegment");
    }
}
