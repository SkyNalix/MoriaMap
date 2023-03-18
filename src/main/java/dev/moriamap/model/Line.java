package dev.moriamap.model;

/**
 * Represents a line of a transport network.
 */
public class Line {

    // Name of this line
    private String name;

    /**
     * Class constructor specifying name.
     *
     * @param  name the name of this Line
     */
    public Line(String name) {
        this.name = name;
    }

    /**
     * {@return the name of this line}
     */
    public String getName() {
        return this.name;
    }

    /**
     * Static factory.
     * @param name of this line
     * @return a new Line with the given name
     */
    public static Line of(String name) {
        return new Line(name);
    }
}
