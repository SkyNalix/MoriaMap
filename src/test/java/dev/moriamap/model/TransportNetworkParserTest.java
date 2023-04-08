package dev.moriamap.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

class TransportNetworkParserTest {    
    @Test void testNumberOfTransportLine() throws InconsistentCSVLinesException,IOException{
        InputStream resource = CSVParserTest.class.getResourceAsStream("/test_data_transportNetwork.csv");
        TransportNetwork tn = TransportNetworkParser.generateFrom(resource);
        assertEquals(2,tn.getLines().size());
    }

    @Test void testNumberOfVariant() throws InconsistentCSVLinesException,IOException{
        List<Integer> transportNetworkVariantSizeList = new ArrayList<>();
        List<Integer> variantSizeList = List.of(3,3);

        InputStream resource = CSVParserTest.class.getResourceAsStream("/test_data_transportNetwork.csv");
        TransportNetwork tn = TransportNetworkParser.generateFrom(resource);

        for(int i =0; i < tn.getLines().size();i++){
            Line l = tn.getLines().get(i);
            transportNetworkVariantSizeList.add(l.getVariants().size());
        }

        assertEquals(variantSizeList,transportNetworkVariantSizeList);

    }

    @Test void findLineNameEqualTrue() throws IOException,InconsistentCSVLinesException{
        InputStream resource = CSVParserTest.class.getResourceAsStream("/test_data_transportNetwork.csv");

        TransportNetwork tn = TransportNetworkParser.generateFrom(resource);

        assertEquals(tn.findLine("8").getName(),"8" );
    }

    @Test void findLineObjectEqualFalse()throws IOException,InconsistentCSVLinesException{
        InputStream resource = CSVParserTest.class.getResourceAsStream("/test_data_transportNetwork.csv");

        TransportNetwork tn = TransportNetworkParser.generateFrom(resource);
        Line l = Line.of("8");
        assertNotEquals(tn.findLine("8"),l);
    }

    @Test void findStopObjectEqualFalse() throws IOException,InconsistentCSVLinesException {
        InputStream resource = CSVParserTest.class.getResourceAsStream("/test_data_transportNetwork.csv");
        List<List<String>> resourceList = CSVParser.extractLines(resource);
        List<EdgeTuple> edgeTupleList =  EdgeTuple.fromTuples(resourceList);

        TransportNetwork tn = TransportNetworkParser.generateFromEdgeTuple(edgeTupleList);

        Stop stop = Stop.from("Faidherbe - Chaligny", GeographicPosition.at(48.384028566383108, 2.384028566383108));
       
       assertNotEquals(tn.getStopByName("Faidherbe - Chaligny"),stop) ;
    }


}
