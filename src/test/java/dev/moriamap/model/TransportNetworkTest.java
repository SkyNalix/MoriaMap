package dev.moriamap.model;

import org.junit.jupiter.api.Test;

import java.time.Duration;

public class TransportNetworkTest {
    private TransportNetwork newTransportNetworkHelper() {
        Stop s1 = Stop.from("s1",GeographicPosition.SOUTH_POLE);
        Stop s2 = Stop.from("s2",GeographicPosition.NORTH_POLE);

        TransportSegment ts1 = TransportSegment.from(s1, s2, "7B", "1",
                Duration.ZERO, 4);
        TransportSegment ts2 = TransportSegment.from(s2, s1, "7B", "2",
                Duration.ZERO, 4);

        Variant v1 = Variant.empty("1", "7B");

        TransportNetwork tn = TransportNetwork.empty();
        return tn;
    }
    @Test
    void getStopFromPosition() {
        Stop s1 = Stop.from("s1",GeographicPosition.SOUTH_POLE);
        TransportNetwork tn = TransportNetwork.empty();

    }
}
