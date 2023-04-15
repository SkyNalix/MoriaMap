package dev.moriamap.model;

import java.util.List;
import java.util.Map;

/**
 * A Plan0Query represent a request for a path from a starting stop to the target stop
 * @param startStopName the name of the starting stop
 * @param targetStopName the name of the target stop
 */
public record Plan0Query(
		  String startStopName,
		  String targetStopName) implements Query {

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
