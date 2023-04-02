package dev.moriamap.model;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Line variant.
 */
public final class Variant {

    /** The name of this variant */
    private String name;

    /** The line to which this variant belongs */
    private String lineName;

    /** The list of transportSegments of this variant */
    private List<TransportSegment> transportSegments;

    /** The list of train departures time of this variant */
    private List<Time> trainDepartures;

    /**
     * Class constructor.
     *
     * @param name of this Variant
     * @param lineName of this Variant
     */
    private Variant(String name, String lineName) {
        this.name = name;
        this.lineName = lineName;
        this.transportSegments = new ArrayList<>();
        this.trainDepartures = new ArrayList<>();
    }

    /**
     * Static factory building a new empty variant
     *
     * @param name if this Variant
     * @param lineName of this Variant
     * @throws IllegalArgumentException if an argument is null
     * @return a new empty Variant with the given id and lineName
     */
    public static Variant empty(String name, String lineName) {
        
        if (lineName == null || name == null) {
            throw new IllegalArgumentException("name and lineName can not be null");
        }

        return new Variant(name, lineName);
    }

    /**
     * Get the first Stop of this variant
     * @return the first Stop of this variant
     */
    public Stop getStart(){
        List<Stop> stops = new ArrayList<Stop>();
        for(TransportSegment ts : transportSegments){
            Stop from = (Stop)ts.getFrom();
            Stop to = (Stop)ts.getTo();
            if(stops.contains(to)) 
                stops.remove(to);
            if(!stops.contains(from))
                stops.add(from);
        }
        return stops.get(0); 
    }

    /**
     * Get the last Stop of this variant
     * @return the last Stop of this variant
     */
    public Stop getEnd(){
        List<Stop> stops = new ArrayList<Stop>();
        for(TransportSegment ts : transportSegments){
            Stop from = (Stop)ts.getFrom();
            Stop to = (Stop)ts.getTo();
            if(stops.contains(from)) 
                stops.remove(from);
            if(!stops.contains(to))
                stops.add(to);
        }
        return stops.get(0); 
    }

    /**
     * Add the given TransportSegment to our TransportSegments list.
     * @param ts TransportSegment to be added
     * @return false if the given transport segment was added
     * @throws IllegalArgumentException if the TransportSegment is Null
     */
    public boolean addTransportSegments(TransportSegment ts){
        if (ts == null) {
            throw new IllegalArgumentException("Null TransportSegment is not allowed");
        }

        if (this.transportSegments.contains(ts)) {
            return false;
        }
        return this.transportSegments.add(ts);
    }

    /**
     * Add the given Time to our Time list.
     * @param t Time to be added
     * @return false if the given time was added
     * @throws IllegalArgumentException if the time is Null
     */
    public boolean addTrainDeparture(Time t){
        if (t == null) {
            throw new IllegalArgumentException("Null TransportSegment is not allowed");
        }

        if (this.trainDepartures.contains(t)) {
            return false;
        }
        return this.trainDepartures.add(t);
    }

    /**
     * {@return the name of this variant}
     */
    public String getName(){
        return this.name;
    }

    /**
     * {@return the lineName of this variant}
     */
    public String getLineName(){
        return this.lineName;
    }

    /**
     * {@return a copy of this variant's train departure time list}
     */
    public List<Time> getTrainDepartures(){
        List <Time> res = new ArrayList<>(this.trainDepartures.size());
        for (Time t : this.trainDepartures)
            res.add(t);

        return res;
    }

    /**
     * {@return a copy of this variant's transportSegments list}
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
        for (int i = 0; i < this.trainDepartures.size(); i++) {
            if (!this.trainDepartures.get(i).equals(other.trainDepartures.get(i)))
                return false;
        }
        return other.lineName.equals(this.lineName)
                && other.name == this.name;
    }

    /**
     * Gets the hash code of this variant
     * @return the hash code of this variant
     */
    @Override public int hashCode(){
        final int prime = 13;
        int hash = 1;
        hash *= prime;
        hash += this.name.hashCode();
        hash += this.lineName.hashCode();
        for(TransportSegment ts : this.transportSegments) hash += ts.hashCode();
        for(Time t : this.trainDepartures) hash += t.hashCode();
        return hash;
    }
}
