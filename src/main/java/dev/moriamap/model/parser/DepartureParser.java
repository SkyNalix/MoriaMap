package dev.moriamap.model.parser;

import java.io.InputStream;
import java.util.List;

import dev.moriamap.model.network.Line;
import dev.moriamap.model.network.TransportNetwork;
import dev.moriamap.model.network.Variant;
/**
 * This class read a departure file and add the departure to a TransportNetwork
 */
public class DepartureParser {

    private DepartureParser(){

    }

    /**
     * Take a TransportNetwork and a departure file and add 
     * the departures read from it to the TransportNetwork
     * @param tn the TransportNetwork to which we add the departures
     * @param departures a Stream to the departures file
     * @throws InconsistentCSVException if there is an error in the departure file
     * @throws IllegalArgumentException if the TransportNetwork is null
     */
    public static void addDeparturesTo(TransportNetwork tn, InputStream departures) throws InconsistentCSVException,IllegalArgumentException{
        if(tn == null) throw new IllegalArgumentException("TransportNetwork is null");
        List<DepartureRecord> records = DepartureRecord.fromTuples(CSVParser.extractLines(departures));
        
        for(DepartureRecord t : records){
           Line l = tn.findLine(t.lineName());
           if(l == null) throw new InconsistentCSVException();
           
           Variant v = l.getVariantNamed(t.variantName());

            if(!t.terminusName().equals(v.getStart().getName())) throw new InconsistentCSVException();

           v.addDeparture(t.departureTime());
        }

    }
}
