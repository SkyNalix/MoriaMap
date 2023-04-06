package dev.moriamap;
import dev.moriamap.model.*;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		System.out.println( "-- MoriaMap --" );

		TransportNetwork tn = null;
		if(tn == null) {
			System.out.println( "An issue occured when creating the transport network" );
			System.exit( 1 );
		}
		Scanner inputScanner = new Scanner(System.in);
		while(true) {
			System.out.print( "Name of the starting stop: " );
			String start_str = inputScanner.nextLine();
			if(start_str.isBlank()) break;
			System.out.print( "Name of the target stop: " );
			String end_str = inputScanner.nextLine();
			if(end_str.isBlank()) break;

			Stop start = tn.getStopByName( start_str );
			Stop end = tn.getStopByName( end_str );
			Map<Vertex, Edge> dfs = tn.depthFirstSearch( start );
			List<Edge> path = Graph.getRouteFromTraversal(dfs, start, end);
			PrettyPrinter.printEdgePath( path );
		}
    }

}
