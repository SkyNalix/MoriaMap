package dev.moriamap.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

/**
 * Class for printing different elements of the project
 */
public class PrettyPrinter {

	private static final String FORMAT_RESET = "\033[0m";

	private PrettyPrinter(){}

	private static String lineChangeToString(TransportNetwork tn, String lineName, TransportSegment segment) {
		Line line = tn.findLine( lineName );
		if(line == null) return "<null line>";
		Variant variant = line.getVariantNamed( segment.getVariantName() );
		return "\n## \033[1;37mLine " + lineName + " variant " + variant.getName()
			   + FORMAT_RESET + " ## \033[4mTerminus: " + variant.getEnd()
			   + FORMAT_RESET + "\n    "
			   + segment.getFrom();
	}

	/**
	 * Method that print the path
	 * @param tn the transport network used for the path creation
	 * @param path list of edges to print
	 * @return The constructed String to print
	 */
	public static String printTransportSegmentPath(TransportNetwork tn, List<Edge> path ) {
		if(path.isEmpty())
			return "";
		TransportSegment segment = (TransportSegment) path.get( 0 );
		String currentLine = segment.getLineName();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append( lineChangeToString(tn, currentLine, segment) );
		Duration totalDuration = Duration.ZERO;
		for( int i = 0; i < path.size(); i++ ) {
			Edge edge = path.get( i );
			segment = (TransportSegment) edge;
			totalDuration = totalDuration.plus( segment.getTravelDuration() );
			String time = segment.getTravelDuration().toString().substring( 2 );
			if( !segment.getLineName().equals( currentLine ) ) {
				stringBuilder.append( "\n" )
						.append( lineChangeToString( tn, segment.getLineName(), segment ) );
			}
			stringBuilder.append( " --(" ).append( time ).append( ")--> " );
			currentLine = segment.getLineName();
			if( i == path.size()-1 )
				stringBuilder.append( "\033[42m" ).append( segment.getTo() ).append( FORMAT_RESET );
			else
				stringBuilder.append( segment.getTo() );
		}
		stringBuilder.append("\n\nTotal traject duration: \033[5m\033[1m")
				.append(totalDuration.toString().substring( 2 ))
				.append( FORMAT_RESET );
		return stringBuilder.toString();
	}

	/**
	 * This method build a string that contain all the information to travel
	 * on the transport network, and with times at every line change
	 * @param tn the transport network this route is on
	 * @param route the route of edges to take in order
	 * @param lts list of times for every edge
	 * @return the built String with all the information
	 */
	public static String printTransportSegmentPathWithLineChangeTimes(
			TransportNetwork tn,
			List<Edge> route,
			List<LocalTime> lts) {
		// afficher le moment quand t'arrive a une station, et le temps quand tu leave la line
		// que quand il y changement de ligne ou variant
		// moment quand tu leave = localtime courrant + getTravelDuration() precedant
		if(route.isEmpty()) return "";
		TransportSegment segment = (TransportSegment) route.get( 0 );
		String currentLine = segment.getLineName();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				  .append("Taking line at ")
				  .append(lts.get(0))
				  .append( lineChangeToString(tn, currentLine, segment) );
		for( int i = 0; i < route.size(); i++ ) {
			Edge edge = route.get( i );
			segment = (TransportSegment) edge;
			if( !segment.getLineName().equals( currentLine ) ) {
				stringBuilder
						  .append("\nSwitching line at ")
						  .append(lts.get(i).plus( segment.getTravelDuration() ))
						  .append( lineChangeToString( tn, segment.getLineName(), segment) );
			}
			stringBuilder.append( " --> " );
			currentLine = segment.getLineName();
			if( i == route.size()-1 )
				stringBuilder.append( "\033[42m" ).append( segment.getTo() ).append( FORMAT_RESET );
			else
				stringBuilder.append( segment.getTo() );
		}
		return stringBuilder.toString();
	}



}
