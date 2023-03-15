package dev.moriamap.model;
/**
 * A Stop is a vertex of our Transport Graph
 */
public class Stop extends GeographicVertex {
    
    // The name of this stop
    private final String name;

    /**
     * Constructor of Stop
     * @param name The name of this stop
     * @param gp The Geographic position of this stop
     */
    protected Stop(String name,GeographicPosition gp){
        super(gp);
        this.name = name;
    }

    /**
     * Static factory method returning a Stop
     * @param name The name of this stop
     * @param gp The Geographic position of this stop
     * @return a new Stop
     */
    public static Stop from(String name, GeographicPosition gp){
        return new Stop(name,gp);
    }    

}
