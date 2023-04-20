package dev.moriamap.model;

import java.util.ArrayList;
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
     * 
     * @param a
     * @param b
     * @return
     */
    public static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    /**
     * 
     * @param numbers
     * @return
     */
    public static int min(int... numbers) {
        return Arrays.stream(numbers)
          .min().orElse(Integer.MAX_VALUE);
    }
    
    /**
     * 
     * @param x
     * @param y
     * @return
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
     * 
     * @param stopName
     * @param stop
     * @return
     */
    public static Stop getNearestStop(String stopName,List<Stop> stop){
        var min = calculate(stopName, stop.get(0).getName());
        var res = stop.get(0);
        for(int i=1;i<stop.size();i++){
            var distance = calculate(stopName, stop.get(i).getName());
            if(distance< min){
                min = distance;
                res = stop.get(i);
            }
        }
        if(min >= 5) return null;
        return res;
    }
}
