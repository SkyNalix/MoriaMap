package dev.moriamap.model;
//TODO: 18/03/2023 add TransportSegment
/**
 * Represents a Line variant.
 */
public class Variant {

    // id of this variant.
    private int id;

    // the line to which this variant belongs.
    private Line line;

    /**
     * Class constructor.
     * <p>
     * Initializes start and end stops to null.
     * <p/>
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
     * <p>
     *     As the Variant is empty its start and end stops
     *     are initialized as null.
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
     * {@return the id of this Variant.}
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of this Variant to the given value.
     * <p>
     *     Only positive values are allowed.
     * </p>
     *
     * @param id the new id of this Variant
     * @return true if the id value is allowed and has been set
     */
    public boolean setId(int id) {
        if (id  < 1) {
            return false;
        }
        this.id = id;
        return true;
    }

    /**
     * {@return the line to which this Variant belongs.}
     */
    public Line getLine() {
        return line;
    }

    /**
     * Sets the line of this Variant to the given value.
     *<p>
     *     If the new ine is null it remains unchanged.
     *
     * @param line the new line to which this Variant belongs
     * @return true if the line is not null and has been set correctly
     */
    public boolean setLine(Line line) {
        if (line == null) {
            return false;
        }
        this.line = line;
        return true;
    }


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
