package dev.moriamap.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A TransportNetworkParser is a parser that read a List of EdgeTuple to create a TransportNetwork
 */
public class TransportNetworkParser {

    private TransportNetworkParser(){

    }

    /**
     * apply the algorithm described in diagrams/transport-network-generation-algorithm to produce a TransportNetwork
     * @param tuples a List of EdgeTuple 
     * @return a TransportNetwork corrsponding to the network given in argument
     */
    public static TransportNetwork generate(InputStream transportNetworkFileContent) throws InconsistentCSVLinesException, IOException{

        List<EdgeTuple> tuples = EdgeTuple.fromTuples(CSVParser.extractLines(transportNetworkFileContent));
        TransportNetwork tn = TransportNetwork.empty();
        List<Variant> VARIANTS = new ArrayList<>();

        for(EdgeTuple t : tuples){
            Stop s1 = Stop.from(t.fromName(),GeographicPosition.at(t.fromLatitude(), t.fromLongitude()) ); 
            Stop realS1 = tn.findStop(s1);
            if(realS1 == null){
                tn.addStop(s1);
            }else{
                s1 = realS1;
            }

            Stop s2 = Stop.from(t.toName(),GeographicPosition.at(t.toLatitude(), t.toLongitude()) );
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
            }


            Variant v = null;

            for(Variant lv : l.getVariants()){
                if (lv.getName().equals(t.variantName()) && lv.getLineName().equals(t.lineName()))
                    v = lv;
            }
            if(v == null){
                v = Variant.empty(t.variantName(),t.lineName());
                l.addVariant(v);
                VARIANTS.add(v);
            }


            TransportSegment segment = TransportSegment.from(s1, s2, t.lineName(), t.variantName(), t.duration(),t.distance());
            tn.addTransportSegment(segment);
            v.addTransportSegment(segment);

        }

        return tn;
    }



}
