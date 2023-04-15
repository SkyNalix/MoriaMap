package dev.moriamap.model;

/**
 * A LECTTIMEQuery represents a request for schedule information fetching.
 */
public class LECTTIMEQuery implements Query{
    
    // The name of the stop  to which transports pass.
    private final String stopName;

    private LECTTIMEQuery(String stopName) {
        this.stopName = stopName;
    }

    /**
     * Static factory.
     * Creates a LECTTIMEQUERY for the given stop.
     * @param stopName name of the stop
     * @return the created query
     */
    public static LECTTIMEQuery fromString(String stopName) {
        return new LECTTIMEQuery(stopName);
    }
    
    @Override
    public void execute(TransportNetwork network) {
            Stop stop = network.getStopByName(stopName);
            if( stop == null ) {
                System.out.println( "Stop was not found" );
                return;
            }
            Passages passages = network.getPassages(stop);
            System.out.println( passages.getFullDescription());
        }
}
