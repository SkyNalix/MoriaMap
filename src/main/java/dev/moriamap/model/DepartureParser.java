package dev.moriamap.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DepartureParser {


        /**
     * 
     * @param resource a Stream to the departure file 
     * @return a List of DepartureRecord
     * @throws IllegalArgumentException if there is an error in the departure file 
     */
    private static List<DepartureRecord> createTheDepartureRecord(InputStream resource) throws IllegalArgumentException {
        if (resource == null) throw new IllegalArgumentException("Path can not be null");

        Scanner sc = new Scanner(resource);
        List<DepartureRecord> list = new ArrayList<>();
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            String delimiter = ";";    

            List<String> tuple = Arrays.asList(line.split(delimiter));

            list.add( DepartureRecord.fromTuple(tuple) );
        }

        sc.close();
        return list;
    }

    /**
     * 
     * @param tn the TransportNetwork to wich we add the departures
     * @param departures a Stream to the departures file
     * @throws InconsistentCSVException if there is an error in the departure file
     */
    public static void addDeparturesTo(TransportNetwork tn, InputStream departures) throws InconsistentCSVException{
        List<DepartureRecord> records = createTheDepartureRecord(departures);
        
        for(DepartureRecord t : records){
           Line l = tn.findLine(t.lineName());
           if(l == null) throw new InconsistentCSVException();
           
           Variant v = l.getVariantNamed(t.variantName());
           if(v == null) throw new InconsistentCSVException();

           if(!t.terminusName().equals(v.getStart().getName())) throw new InconsistentCSVException();

           v.addDeparture(t.departureTime());
        }

    }

    public static void main(String[] args) throws InconsistentCSVException{
        InputStream resource = DepartureParser.class.getResourceAsStream("/timetables.csv");
        InputStream resourceMap = DepartureParser.class.getResourceAsStream("/map_data.csv");

        TransportNetwork tn = TransportNetworkParser.generateFrom(resourceMap);
        DepartureParser.addDeparturesTo(tn, resource);
    }
    
}
