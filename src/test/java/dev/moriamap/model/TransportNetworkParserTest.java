package dev.moriamap.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.*;

public class TransportNetworkParserTest {
    

    @Test
    void errorParsingFile(){ //src\test\java\dev\moriamap\ressources\testdata.csv
        assertThrows(
            IllegalArgumentException.class,
            () ->  TransportNetworkParser.load("src/test/java/dev/moriamap/ressources/does_not_exist.csv")         //GeographicPosition.at(180.0, 42.0)
          );
    }

    @Test 
    void correct_number_of_lines(){
        List<Line> transport_network = TransportNetworkParser.load("src/test/java/dev/moriamap/ressources/testdata.csv");
        assertEquals(2, transport_network.size());
    }

    @Test 
    void correct_number_of_variants(){
        List<Line> transport_network = TransportNetworkParser.load("src/test/java/dev/moriamap/ressources/testdata.csv");
        int[] variant_array = {2,1};
        for(int i =0; i <transport_network.size();i++){
            assertEquals( variant_array[i] , transport_network.get(i).getVariants().size());
        }
    }

}
