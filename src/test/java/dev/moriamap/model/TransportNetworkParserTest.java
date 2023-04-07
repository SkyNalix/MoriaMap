package dev.moriamap.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TransportNetworkParserTest {
    
    @Test void testNumberOfTransportLine() throws InconsistentCSVLinesException,IOException{
        InputStream resource = CSVParserTest.class.getResourceAsStream("/map_data.csv");
        TransportNetwork tn = TransportNetworkParser.generate(resource);
        assertEquals(16,tn.getLines().size());
    }

    @Test void testNumberOfVariant() throws InconsistentCSVLinesException,IOException{
        List<Integer> transportNetworkVariantSizeList = new ArrayList<>();
        List<Integer> variantSizeList = List.of(5,9,4,4,6,5,4,2,6,9,11,7,8,6,5,2);

        InputStream resource = CSVParserTest.class.getResourceAsStream("/map_data.csv");
        TransportNetwork tn = TransportNetworkParser.generate(resource);

        for(int i =0; i < tn.getLines().size();i++){
            Line l = tn.getLines().get(i);
            transportNetworkVariantSizeList.add(l.getVariants().size());
        }

        assertEquals(variantSizeList,transportNetworkVariantSizeList);

    }


}
