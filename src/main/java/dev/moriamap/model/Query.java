package energy.model;

/**
 * A Query represents a request for route computation or shedule information
 * fetching.
 */
public interface Query {

    /**
     * Executes this Query on the given network.
     * @param network the network this Query acts on
     */
    void execute(TransportNetwork network);
}
