package dev.moriamap.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransportNetworkParser {

    //TODO: 27/03/2023 add the Stop to their Line
    /**
     * @param fileName the name of the file with the extension
     * @return a List<Line> containing all the Line and its variant from the file given
     */
    public static List<Line> load(String file_path_and_name){ 
        try {
            ArrayList<Line> result = new ArrayList<>();
            ArrayList<String> listOfIdLine = new ArrayList<>();
            File file = new File(file_path_and_name);

            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                String str = sc.nextLine();
                String[] array = str.split(";");
                
                String[] tmp = array[4].split(" ");
                
                Line line = Line.of(tmp[0]);
                if(!listOfIdLine.contains(tmp[0])){ // add the line
                    result.add(line);
                    listOfIdLine.add(tmp[0]);
                }

                //add Variant
                boolean x = true;
                for(int i =0; i < result.size();i++){
                    Variant variant = Variant.empty(Integer.parseInt(tmp[2]), line);
                    if(!result.get(i).containsVariant(variant)  && tmp[0].equals(result.get(i).getName()) ){

                            for(int j=0; j < result.get(i).getVariants().size();j++){ //if not this they are two variant 1
                                if(result.get(i).getVariants().get(j).getId() == Integer.parseInt(tmp[2]))
                                    x = false;
                            }
                        
                        if (x)
                            result.get(i).addVariant(variant);
                    }
                }

                //create Stop
                String[] gp = array[1].split(",");
                Stop stop = Stop.from(array[0],GeographicPosition.at(Double.parseDouble(gp[0]), Double.parseDouble(gp[1])));
            }
            sc.close();
            return result;

        }catch(Exception e){
            throw new IllegalArgumentException("File name not found in TransportNetworkParser");
        }
    }


}
