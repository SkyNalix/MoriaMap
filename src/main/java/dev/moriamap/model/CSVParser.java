package dev.moriamap.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This class pareses data from CSV file and provides it as row string.
 */
public final class CSVParser {

    private CSVParser(){}

    /**
     * Parses a CSV file line.
     * @param line to be parsed
     * @param delimiter fields separators
     * @return a list of strings containing each field
     */
    public static List<String> parseCSVLine(String line, String delimiter) {
        if (line == null) {
            throw new IllegalArgumentException("Line can not be null.");
        }
        if (line.equals("")) {
            throw new IllegalArgumentException("Line can not be empty.");
        }
        if (delimiter == null) {
            throw new IllegalArgumentException("Delimiter can not be null.");
        }
        if (delimiter.equals("")) {
            throw new IllegalArgumentException("Delimiter can not be an empty string.");
        }
        return new ArrayList<>(Arrays.asList(line.split(delimiter)));
    }

    /**
     * Parses a CSV file.
     * @param path to CSV file
     * @return a list of lists of strings containing raw data.
     * @throws InconsistentCSVLineException if two lines do not have same number of fields
     */
    public static List<List<String>> extractLines(String path) throws InconsistentCSVLineException {
        if (path == null) {
            throw new IllegalArgumentException("Path can not be null");
        }
        InputStream in = CSVParser.class.getResourceAsStream( path);
        List<List<String>> content = new ArrayList<>();
        assert in != null;
        Scanner sc = new Scanner(in);
        while (sc.hasNext()){
            content.add(CSVParser.parseCSVLine(sc.nextLine(),";"));

        }
        int len = content.get(0).size();
        boolean same = true;
        for (List<String> fields:content) {
            if (fields.size() != len) {
                same  = false;
            }
        }
        if (!same) {
            throw new InconsistentCSVLineException();
        }
        try {
            in.close();

        } catch (IOException e) {
           throw new RuntimeException();
        }
        return content;
    }
}
