package dev.moriamap.model;

import java.io.OutputStream;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class PLAN1Query extends Query {

	private final String startStopName;
	private final String targetStopName;
	private final RouteOptimization optimizationChoice;
	private final LocalTime startTime;

	public PLAN1Query( OutputStream out,
					   String startStopName,
					   String targetStopName,
					   RouteOptimization optimizationChoice,
					   LocalTime startTime) {
		super(out);
		this.startStopName = startStopName;
		this.targetStopName = targetStopName;
		this.optimizationChoice = optimizationChoice;
		this.startTime = startTime;
	}

	@Override
	protected String run( TransportNetwork network ) throws QueryFailureException {
		BiFunction<Double, Edge, Double> optimizationBiFun;
		if(optimizationChoice == RouteOptimization.DISTANCE)
			optimizationBiFun = new DistanceAsWeight();
		else {
			optimizationBiFun = new TravelTimeAsWeight( startTime, network );
		}
		network.setTraversalStrategy( new DijkstraTraversalStrategy() );

		Stop start = network.getStopByName( startStopName );
		Stop target = network.getStopByName( targetStopName );
		if(start == null || target == null)
			throw new QueryFailureException("One of the stops was not found");
		Map<Vertex, Edge> traversal =  network.traversal( start, target, optimizationBiFun, true );
		List<Edge> path = Graph.getRouteFromTraversal( traversal, start, target );
		return network.getRouteDescription( path ,startTime);
	}

}
