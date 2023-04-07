package dev.moriamap.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

class TransportNetworkParserTest {
    
    @Test void testNumberOfTransportLine() throws InconsistentCSVLinesException,IOException{
        InputStream resource = CSVParserTest.class.getResourceAsStream("/test_data.csv");
        TransportNetwork tn = TransportNetworkParser.generate(resource);
        assertEquals(2,tn.getLines().size());
    }

    @Test void testNumberOfVariant() throws InconsistentCSVLinesException,IOException{
        /*
         * 
         * 8 : 3
         * 4 : 3
         * 
         */
        List<Integer> transportNetworkVariantSizeList = new ArrayList<>();
        List<Integer> variantSizeList = List.of(3,3);

        InputStream resource = CSVParserTest.class.getResourceAsStream("/test_data.csv");
        TransportNetwork tn = TransportNetworkParser.generate(resource);

        for(int i =0; i < tn.getLines().size();i++){
            Line l = tn.getLines().get(i);
            transportNetworkVariantSizeList.add(l.getVariants().size());
        }

        assertEquals(variantSizeList,transportNetworkVariantSizeList);

    }


}
