package main.java.edu.virginia.sde.hw1;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            var csvMap = CSVReader.getMapFromCSV(args[0]);
        }
        catch (IOException e) {
            throw new IOException("\nError - check input");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error - no .csv file given");
            System.out.println(1);
        }
    }

    private static int getDivisor(Map<String, Integer> csvMap) {
        var totalPopulation = 0;
        for (String stateName : csvMap.keySet()) {
            totalPopulation += csvMap.get(stateName);
        }
        return totalPopulation / csvMap.size();
    }

    private static Map<String, Integer> getStateSeatMap(Map<String, Integer> csvMap, int divisor) {
        Map<String, Integer> stateSeatMap = new HashMap<>();
        for (String stateName : csvMap.keySet()) {
            var numSeats = Math.floorDiv(csvMap.get(stateName), divisor);
            stateSeatMap.put(stateName, numSeats);
        }
        return stateSeatMap;
    }

    private static int getTotalSeats(Map<String, Integer> stateSeatMap) {
        var totalSeats = 0;
        for (String stateName : stateSeatMap.keySet()) {
            totalSeats += stateSeatMap.get(stateName);
        }
        return totalSeats;
    }

}
