package dev.moriamap.model;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Line variant.
 */
public final class Variant {

    // id of this variant.
    public int id;

    // the line to which this variant belongs.
    public String lineName;

    private List<TransportSegment> transportSegments;

    /**
     * Class constructor.
     *
     * @param id of this Variant
     * @param lineName of this Variant
     */
    private Variant(int id, String lineName) {
        this.id = id;
        this.lineName = lineName;
    }

    /**
     * Static factory.
     *
     * @param id if this Variant
     * @param lineName of this Variant
     * @return a new empty Variant with the given id and lineName
     */
    public static Variant empty(int id, String lineName) {
        if (id < 0) {
            throw new IllegalArgumentException("id can not be negative.");
        }

        if (lineName.equals("")) {
            throw new IllegalArgumentException(" line can not be an empty string.");
        }

        return new Variant(id, lineName);
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
     * @return a copy of this variant's transportSegments list
     */
    public List<TransportSegment> getTransportSegments(){
        List <TransportSegment> res = new ArrayList<>(this.transportSegments.size());
        for (TransportSegment ts : this.transportSegments)
            res.add(ts);

        return res;
    }

    /**
     * Check if this variant is equal to the given line.
     * <p>
     *     Two variants are equal if they have the same lineName, the same id 
     *     and the same (by a call to equals) transportSegments in the same order.
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
        if (other.transportSegments.size() != this.transportSegments.size())
            return false;
        for (int i = 0; i < this.transportSegments.size(); i++) {
            if (!this.transportSegments.get(i).equals(other.transportSegments.get(i)))
                return false;
        }
        return other.lineName.equals(this.lineName)
                && other.id == this.id;
    }
}
