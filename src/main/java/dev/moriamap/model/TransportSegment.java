package dev.moriamap.model;
import java.time.Duration;

/**
 * A TransportSegment is an Edge of our Graph
 */
public class TransportSegment extends Edge{
    
    // The variant's id of this TransportSegment
    public String lineVariantName;

    // The travel time of this TransportSegment
    public final Duration travelTime;

    // The distance of this TransportSegment
    public final double distance;

    /**
     * The constructor of TransportSegment
     * @param from The origin of this TransportSegment
     * @param to The destination of this TransportSegment
     * @param lineVariantName The name of the line and variant of this TransportSegment
     * @param travelTime The travel time of this TransportSegment
     * @param distance The distance of this Transport Segment
     */
    private TransportSegment(Vertex from, Vertex to,String lineVariantName,Duration travelTime, double distance){
        super(from, to);
        this.lineVariantName = lineVariantName;
        this.travelTime = travelTime;
        this.distance = distance;
    }

    /**
     * Static factory method returning a TransportSegment
     * @param from The origin of this TransportSegment
     * @param to The destination of this TransportSegment
     * @param lineVariantName The name of the line and variant of this TransportSegment
     * @param travelTime The travel time of this TransportSegment
     * @param distance The distance of this Transport Segment
     * @return a new TransportSegment
     */
    public static TransportSegment from(Vertex from, Vertex to, String lineVariantName, Duration travelTime, double distance){
        return new TransportSegment(from,to,lineVariantName,travelTime,distance);
    }

    /**
     * Returns the weight of a TransportSegment. Useful to implement algorithms such as A*.
     * @return the weight of this TransportSegment
     */
    @Override
    public double getWeight(){
        return travelTime.getSeconds() + distance;
    }

}
