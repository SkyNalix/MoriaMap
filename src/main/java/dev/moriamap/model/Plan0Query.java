package dev.moriamap.model;

import java.util.List;
import java.util.Map;

/**
 * A Plan0Query represent a request for a path from a starting stop to the target stop
 */
public class Plan0Query implements Query {

	/**
	 * The name of starting stop
	 */
	String startStopName;

	/**
	 * The name of the target stop
	 */
 	String targetStopName;

	private Plan0Query(String startStopName, String targetStopName) {
		this.startStopName = startStopName;
		this.targetStopName = targetStopName;
	}

	/**
	 * Static factory.
	 * Create Plan0Query with the given stop names
	 * @param startStopName the name of the starting stop
	 * @param targetStopName the name of the target stop
	 * @return The created Plan0Query
	 */
	public static Plan0Query from(String startStopName, String targetStopName) {
		return new Plan0Query( startStopName, targetStopName );
	}

	@Override
	public void execute( TransportNetwork network ) {
		Stop start = network.getStopByName( startStopName );
		Stop target = network.getStopByName( targetStopName );
		if(start == null || target == null) {
			System.out.println( "One of the stops was not found" );
			return;
		}
		try {
			Map<Vertex, Edge> dfs = network.depthFirstSearch( start );
			List<Edge> path = Graph.getRouteFromTraversal( dfs, start, target );
			PrettyPrinter.printEdgePath(network, path );
		} catch(Exception e) {
			System.out.println( "An issue occurred during the path finding" );
		}
	}

}
