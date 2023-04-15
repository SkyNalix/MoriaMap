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

	String run( TransportNetwork network ) throws QueryFailureException {
		Stop start = network.getStopByName( startStopName );
		Stop target = network.getStopByName( targetStopName );
		if(start == null || target == null)
			throw new QueryFailureException();
		Map<Vertex, Edge> dfs = network.depthFirstSearch( start );
		List<Edge> path = Graph.getRouteFromTraversal( dfs, start, target );
		return PrettyPrinter.printTransportSegmentPath(network, path );
	}

	@Override
	public void execute( TransportNetwork network ) {
		String result;
		try {
			result = run( network );
		} catch( QueryFailureException e ) {
			result = "An issue was found on the inputs";
		} catch( Exception e ) {
			result = e.getMessage();
		}
		System.out.println( "Error:" + result );
	}

}
