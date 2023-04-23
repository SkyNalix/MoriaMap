package dev.moriamap.model;

import java.util.Arrays;
import java.util.List;

/**
 * 
 */
public class Utils {
    
    private Utils(){
        throw new AssertionError();
    }

    /**
    * Calculates the cost of substituting one character with another.
    * @param a the first character
    * @param b the second character
    * @return the cost of substitution, which is 0 if the two characters are the same, and 1 otherwise
    */
    public static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    /**
    * Returns the minimum value among the given integers.
    * @param numbers the integers among which the minimum value is to be found
    * @return the smallest integer value in the array, or Integer.MAX_VALUE if the input is empty
    */
    public static int min(int... numbers) {
        return Arrays.stream(numbers)
          .min().orElse(Integer.MAX_VALUE);
    }
    
    /**
    * Calculates the minimum edit distance between two strings using the Wagner-Fisher algorithm,
    * also known as the Levenshtein distance.
    * @param x the first string
    * @param y the second string
    * @return the minimum number of insertions, deletions, and substitutions needed to transform x into y
    */
    public static int calculate(String x, String y) {
        int[][] dp = new int[x.length() + 1][y.length() + 1];
        for (int i = 0; i <= x.length(); i++) {
            for (int j = 0; j <= y.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                }
                else if (j == 0) {
                    dp[i][j] = i;
                }
                else {
                    dp[i][j] = min(dp[i - 1][j - 1] 
                     + costOfSubstitution(x.charAt(i - 1), y.charAt(j - 1)), 
                      dp[i - 1][j] + 1, 
                      dp[i][j - 1] + 1);
                }
            }
        }
        return dp[x.length()][y.length()];
    }

    /**
    * Finds the stop in the given list of stops that has the closest name to the given stop name,
    * as measured by the Levenshtein distance.
    * @param stopName the name of the stop to find the closest match for
    * @param stops the list of stops to search for a match in
    * @return the stop in the list with the closest name to the given stop name, or null if no match was found
    */
    public static Stop getNearestStop(String stopName,List<Stop> stops){
        var min = calculate(stopName, stops.get(0).getName());
        var res = stops.get(0);
        for(int i=1;i<stops.size();i++){
            var distance = calculate(stopName, stops.get(i).getName());
            if(distance< min){
                min = distance;
                res = stops.get(i);
            }
        }
        // If the closest match is more than three edit operations away, return null
        if(min >= 3) return null;
        return res;
    }
}
