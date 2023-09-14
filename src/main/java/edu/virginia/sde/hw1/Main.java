package main.java.edu.virginia.sde.hw1;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new RuntimeException("Error - No .csv file given");
        }
        var csvReader = new CSVReader(args[0]);
        var stateData = new StateData(csvReader.getStatePopulation());
        var totalSeats = args.length < 2 ? 435 : Integer.parseInt(args[1]);
        var divisor = getDivisor(stateData.getTotalPopulation(), totalSeats);
        jeffersonAlgorithm(stateData, totalSeats, divisor);
        for (String stateName : getStatesInAlphabeticalOrder(stateData.getStatePopulation())) {
            var numStateSeats = stateData.getApportionmentMap().get(stateName);
            System.out.printf("%s - %d\n", stateName, numStateSeats);
        }
    }

    private static List<String> getStatesInAlphabeticalOrder(Map<String, Integer> stateDataMap) {
        List<String> orderedStates = new ArrayList<>(stateDataMap.keySet());
        orderedStates.sort(CharSequence::compare);
        return orderedStates;
    }

    private static double getDivisor(long totalPopulation, int numSeats) {
        return (double) totalPopulation / numSeats;
    }

    private static void jeffersonAlgorithm(StateData stateData, int totalSeats, double divisor) {
        int apportionedSeats;
        double low = 0;
        double high = divisor;
        double mid = divisor / 2;
        while (true) {
            stateData.setApportionmentMap(mid);
            apportionedSeats = stateData.getApportionedSeats();
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