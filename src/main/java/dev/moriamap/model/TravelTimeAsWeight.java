package dev.moriamap.model;

import java.util.function.*;

public class TravelTimeAsWeight implements BiFunction<Double, Edge, Double> {
    private LocalTime departureTime;
    private TransportNetwork network;

    /**
     * Class constructor specifying departure time and network.
     * @param departureTime the time of departure
     * @param network the traversed network
     */
    public TravelTimeAsWeight(LocalTime departureTime,
                              TransportNetwork network) {
        this.departureTime = departureTime;
        this.network = network;
    }

    @Override public Double apply(Double current, Edge edge) {
        return current;
    }
}
