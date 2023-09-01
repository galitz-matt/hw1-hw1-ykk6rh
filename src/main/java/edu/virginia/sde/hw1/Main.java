package main.java.edu.virginia.sde.hw1;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            var csvMap = CSVReader.getMapFromCSV(args[0]);
            var csvList = CSVReader.getListFromCSV(args[0]);
            int numSeats = args.length < 2 ? 435 : Integer.parseInt(args[1]);
            var divisor = getDivisor(csvMap, numSeats);
            var apportionmentMap = getApportionmentMap(csvMap, divisor);

            /* Placeholder for Jefferson Algo */

            for (String stateName : CSVReader.getStatesInAlphabeticalOrder(csvList)) {
                var numStateSeats = apportionmentMap.get(stateName);
                System.out.printf("%s = %d\n", stateName, numStateSeats);
            }
        }
        catch (IOException e) {
            throw new IOException("\nError - check input");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error - no .csv file given");
            System.out.println(1);
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

    private static void updateApportionmentMap(Map<String, Integer> apportionmentMap, int divisor) {
        apportionmentMap.replaceAll((n, v) -> Math.floorDiv(apportionmentMap.get(n), divisor));
    }

}
