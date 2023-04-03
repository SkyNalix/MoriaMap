package dev.moriamap.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransportNetworkParser {

    public void algorithm(List<String> tuples){
        List<Stop> stops = new ArrayList<>();
        List<Variant> variants = new ArrayList<>();
        List<Line> lines = new ArrayList<>();

        for(int i =0; i < tuples.size();i++){
            Stop s1 = null;
            Stop s2 = null;
            
            String t = tuples.get(i);

            List<Stop> tupple_test = stringToStop(t);
            if(stops.contains( tupple_test.get(0) )){ // changer le contains / equals
                s1 = tupple_test.get(0);
            }else{
                s1 = tupple_test.get(0);
                stops.add(s1);
            }

            if(stops.contains( tupple_test.get(1) )){
                s2 = tupple_test.get(1);
            }else{
                s2 = tupple_test.get(1);
                stops.add(s1);
            }

            Line l = null;
            if(lines.contains(stringToLine(t)) ){
                l = stringToLine(t);
            }else{
                l = stringToLine(t);
                lines.add(l);
            }

            Variant v = null;
            if(variants.contains( stringToVariant(t))){
                v = stringToVariant(t);
            }else{
                v = stringToVariant(t);
                variants.add(v);
                l.addVariant(v);
            }

            String distance = t.split(";")[6];
            String time = t.split(";")[5];
            // TransportSegemnt segment = new TransportSegment(S1,S2,time,distance,v);
            // ajouter SEGMENT à la liste des arêtes sortantes de S1
            //ajouter SEGMENT au variant V
            // return new TransportNetwork(stops,lines);
        }
    }

    private static List<Stop> stringToStop(String str){
        String[] array = str.split(";"); // s1
        String[] lattitude_longitudeS1 = array[1].split(",");
        String[] lattitude_longitudeS2 = array[2].split(",");

        // GeographicPosition gp1 =  GeographicPosition.from(lattitude_longitudeS1[0],lattitude_longitudeS1[1]);
        // GeographicPosition gp2 =  GeographicPosition.from(lattitude_longitudeS2[0],lattitude_longitudeS2[1]);
        // Stop stop1 = Stop.from(array[0],gp1);
        // Stop stop2 = Stop.from(array[2],gp2)
        // return List.of(gp1,gp2);

        return null;
    }

    private static Line stringToLine(String str){
        String line_variant = str.split(";")[4];
        return Line.of((line_variant.split(" "))[0]);
    }

    private static Variant stringToVariant(String str){
        String line_variant = str.split(";")[4];

        Line line = Line.of((line_variant.split(" "))[0]);
        int id = Integer.parseInt((line_variant.split(" "))[2]);

        return Variant.empty(id ,line);
    }


    /*public static void main(String[] args){
        List<Line> l = new ArrayList<>();
        Line a = Line.of("14");
        Line b = Line.of("14");

        l.add(a);
        if(l.contains(b)){System.out.println("b");}

    } */


}
