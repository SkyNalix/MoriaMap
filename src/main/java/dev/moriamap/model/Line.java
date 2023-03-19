package dev.moriamap.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a line of a transport network.
 */
public class Line {

    // Name of this line
    private String name;

    // Variants of this line
    private List<Variant> variants;

    /**
     * Class constructor specifying name.
     *<p>
     *     The list of variants is initialized as an empty list.
     * @param  name the name of this Line
     */
    private Line(String name) {
        this.name = name;
        this.variants = new ArrayList<>();
    }

    /**
     * {@return the name of this line}
     */
    public String getName() {
        return this.name;
    }

    public List<Variant> getVariants() {
        List <Variant> res = new ArrayList<>(this.variants.size());
        for (Variant v : this.variants)
            res.add(v);

        return res;
    }

    /**
     * Static factory.
     *
     * @param name of this line
     * @throws IllegalArgumentException if name is null
     * @return a new Line with the given name
     */
    public static Line of(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Null name is not allowed");
        }
        return new Line(name);
    }

    public boolean containsVariant(Variant v) {
        return this.variants.contains(v);
    }

    public boolean addVariant(Variant v) {
        if (v == null) {
            throw new IllegalArgumentException("Null variant is not allowed");
        }

        if (this.containsVariant(v)) {
            return false;
        }

        return this.variants.add(v);
    }

    @Override public boolean equals(Object object) {
        if (this ==  object)
            return true;
        if (object == null || object.getClass() != this.getClass())
            return false;
        Line other = (Line) object;
        if (other.variants.size() != this.variants.size())
            return false;
        for (int i = 0; i < this.variants.size(); i++) {
            if (!this.variants.get(i).equals(other.variants.get(i)))
                return false;
        }
        return this.name.equals(other.name);
    }


}
