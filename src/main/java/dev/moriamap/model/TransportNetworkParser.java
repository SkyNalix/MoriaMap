package dev.moriamap.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A TransportNetworkParser is a parser that read a List<EdgeTuple> to create a TransportNetwork
 */
public class TransportNetworkParser {

    private TransportNetworkParser(){

    }

    /**
     * apply the algorithm described in diagrams/transport-network-generation-algorithm to produce a TransportNetwork
     * @param tuples a List of EdgeTuple 
     * @return a TransportNetwork corrsponding to the network given in argument
     */
    public static TransportNetwork generate(List<EdgeTuple> tuples){
        TransportNetwork tn = TransportNetwork.empty();
        List<Variant> VARIANTS = new ArrayList<>();

        for(int i=0;i<tuples.size();i++){
            EdgeTuple t = tuples.get(i);

            String tName = t.fromName();
            String tLatitude = String.valueOf(t.fromLatitude());
            String tLongitude = String.valueOf(t.fromLongitude());
            
            Stop s1 = Stop.from(tName, GeographicPosition.from(tLatitude ,tLongitude));
            Stop realS1 = tn.findStop(s1);
            if(realS1 == null){
                tn.addStop(s1);
            }else{
                s1 = realS1;
            }

            String tName2 = t.toName();
            String tLatitude2 = String.valueOf(t.toLatitude());
            String tLongitude2 = String.valueOf(t.toLongitude());
            Stop s2 = Stop.from(tName2, GeographicPosition.from(tLatitude2 ,tLongitude2));
            Stop realS2 = tn.findStop(s2);
            if(realS2 == null){
                tn.addStop(s2);
            }else{
                s2 = realS2;
            }


            Line l = tn.findLine(t.lineName());
            if(l == null){
                l = Line.of(t.lineName());
                tn.addLine(l);
            }else{
                l = Line.of(t.lineName());
            }


            Variant v = null;
            if(VARIANTS.contains( Variant.empty(t.variantName(), t.lineName()) )){
                v = Variant.empty(t.variantName(), t.lineName());
            }else{
                v = Variant.empty(t.variantName(), t.lineName());
                VARIANTS.add(v);
                l.addVariant(v);
            }


            TransportSegment segment = TransportSegment.from(s1, s2, t.lineName(), t.variantName(), t.duration(),t.distance());
            tn.addEdge(segment);
            v.addTransportSegment(segment);

        }

        return tn;
    }

}
