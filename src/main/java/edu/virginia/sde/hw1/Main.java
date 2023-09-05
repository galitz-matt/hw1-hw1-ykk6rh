package main.java.edu.virginia.sde.hw1;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            throw new ArrayIndexOutOfBoundsException("Error - no .csv file given");
        }
        try {
            var csvMap = CSVReader.getMapFromCSV(args[0]);
            var totalSeats = args.length < 2 ? 435 : Integer.parseInt(args[1]);
            var divisor = getDivisor(csvMap, totalSeats);
            var apportionmentMap = getApportionmentMap(csvMap, divisor);
            jeffersonAlgorithm(apportionmentMap, csvMap, totalSeats, divisor);
            for (String stateName : CSVReader.getStatesInAlphabeticalOrder(csvMap)) {
                var numStateSeats = apportionmentMap.get(stateName);
                System.out.printf("%s - %d\n", stateName, numStateSeats);
            }
        }
        catch (IOException e) {
            throw new IOException("\nError - check input");
        }
    }

    private static int getTotalPopulation(Map<String, Integer> csvMap) {
        int totalPopulation = 0;
        for (String stateName : csvMap.keySet()) {
            totalPopulation += csvMap.get(stateName);
        }
        return totalPopulation;
    }

    private static int getDivisor(Map<String, Integer> csvMap, int numSeats) {
        return getTotalPopulation(csvMap) / numSeats;
    }

    private static Map<String, Integer> getApportionmentMap(Map<String, Integer> csvMap, int divisor) {
        Map<String, Integer> apportionmentMap = new HashMap<>();
        for (String stateName : csvMap.keySet()) {
            apportionmentMap.put(stateName, Math.floorDiv(csvMap.get(stateName), divisor));
        }
        return apportionmentMap;
    }

    private static void updateApportionmentMap(Map<String, Integer> apportionmentMap, Map<String, Integer> csvMap, int divisor) {
        apportionmentMap.replaceAll((n, v) -> csvMap.get(n) / divisor);
    }

    private static int getApportionedSeats(Map<String, Integer> apportionmentMap) {
        int apportionedSeats = 0;
        for (String stateName : apportionmentMap.keySet()) {
            apportionedSeats += apportionmentMap.get(stateName);
        }
        return apportionedSeats;
    }

    private static void jeffersonAlgorithm(Map<String, Integer> apportionmentMap, Map<String, Integer> csvMap, int totalSeats, int divisor) {
        int apportionedSeats;
        var low = 0;
        var high = divisor;
        var mid = divisor / 2;
        while (true) {
            updateApportionmentMap(apportionmentMap, csvMap, mid);
            apportionedSeats = getApportionedSeats(apportionmentMap);
            if (apportionedSeats < totalSeats) {
                high = mid;
            }
            else if (apportionedSeats > totalSeats) {
                low = mid;
            }
            else {
                break;
            }
            mid = (low + high) / 2;
        }
    }

}
