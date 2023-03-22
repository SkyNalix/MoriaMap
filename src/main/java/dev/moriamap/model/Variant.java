package dev.moriamap.model;
import java.util.List;

/**
 * Represents a Line variant.
 */
public final class Variant {

    // id of this variant.
    private int id;

    // the line to which this variant belongs.
    private Line line;

    private List<TransportSegment> transportSegments;

    /**
     * Class constructor.
     *
     * @param id of this Variant
     * @param line of this Variant
     */
    private Variant(int id, Line line) {
        this.id = id;
        this.line = line;
    }

    /**
     * Static factory.
     *
     * @param id if this Variant
     * @param line of this Variant
     * @return a new empty Variant with the given id and line
     */
    public static Variant empty(int id, Line line) {
        if (id < 0) {
            throw new IllegalArgumentException("id can not be negative.");
        }

        if (line == null) {
            throw new IllegalArgumentException(" line can not be null.");
        }

        return new Variant(id, line);
    }

    /**
     * Add the given TransportSegment to our TransportSegments list.
     * @param ts TransportSegment to be added
     * @throws IllegalArgumentException if the TransportSegment is Null or already contained in the list
     */
    public void addTransportSegments(TransportSegment ts){
        if (ts == null) {
            throw new IllegalArgumentException("Null TransportSegment is not allowed");
        }

        if (this.transportSegments.contains(ts)) {
            throw new IllegalArgumentException("The list already contains the given value");
        }
        this.transportSegments.add(ts);
    }

    /**
     * {@return the id of this Variant.}
     */
    public int getId() {
        return id;
    }

    /**
     * {@return the line to which this Variant belongs.}
     */
    public Line getLine() {
        return this.line;
    }

    /**
     * Check if this variant is equal to the given line.
     * <p>
     *     Two variants are equal if they have the same
     *     (by a call to equals) line and the same id.
     * </p>
     * @param object to be compared to
     * @return true if this is equal to object
     */
    @Override public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || object.getClass() != this.getClass())
            return false;
        Variant other = (Variant) object;
        return other.line.equals(this.line)
                && other.id == this.id;
    }
}
