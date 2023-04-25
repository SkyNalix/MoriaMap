package dev.moriamap.model;

/**
 * A WalkSegment represents the action of walking from a geographic vertex to
 * another geographic vertex.
 */
public class WalkSegment extends Edge {

    /**
     * Walk speed in km/h.
     */
    public static final double WALK_SPEED = 4.5;

    /**
     * Drudgery factor of the action of walking.
     */
    public static final double WALK_DRUDGERY = 10.0;

    /**
     * The distance between the ends of this WalkSegment.
     */
    public final double distance;

    /**
     * Class constructor specifying origin and destination. The ends are
     * considered on Earth.
     * @param from the GeographicVertex where this WalkSegment starts
     * @param to the GeographicVertex where this WalkSegment ends
     */
    public WalkSegment(GeographicVertex from, GeographicVertex to) {
        super(from, to);
        this.distance = GeographicPosition
            .euclideanDistanceOnEarth(from.getGeographicPosition(),
                                      to.getGeographicPosition());
    }
}
