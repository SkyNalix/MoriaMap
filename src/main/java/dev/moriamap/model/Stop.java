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
    private Stop(String name,GeographicPosition gp){
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
    /**
     * Gets the name of this stop
     * @return the name of this stop
     */
    public String getName(){
        return this.name;
    }

    /**
     * Returns true if this is equal to the given object.
     * <p>
     *     Two stops are equal if they have the same position and the same name.
     * </p>
     *
     * @param object to compare with
     * @return true if this is equal to the given object
     */
    @Override public boolean equals(Object object) {
        return super.equals(object) && this.name.equals(((Stop)object).name);
    }

    /**
     * Returns the hash code of this Stop.
     *
     * @return the hash code of this Stop.
     */
    @Override public int hashCode() {
        final int prime = 13;
        int hash = super.hashCode();
        hash *= prime;
        hash += this.name.hashCode();

        return hash;
    }

}
