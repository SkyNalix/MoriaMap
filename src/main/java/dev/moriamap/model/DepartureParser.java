package dev.moriamap.model;

import java.io.InputStream;
import java.util.List;

public class DepartureParser {

    /**
     * 
     * @param tn the TransportNetwork to wich we add the departures
     * @param departures a Stream to the departures file
     * @throws InconsistentCSVException if there is an error in the departure file
     */
    public static void addDeparturesTo(TransportNetwork tn, InputStream departures) throws InconsistentCSVException{
        List<DepartureRecord> records = DepartureRecord.fromTuples(CSVParser.extractLines(departures));
        
        for(DepartureRecord t : records){
           Line l = tn.findLine(t.lineName());
           if(l == null) throw new InconsistentCSVException();
           
           Variant v = l.getVariantNamed(t.variantName());
           if(v == null) throw new InconsistentCSVException();

           if(!t.terminusName().equals(v.getStart().getName())) throw new InconsistentCSVException();

           v.addDeparture(t.departureTime());
        }

    }
}
