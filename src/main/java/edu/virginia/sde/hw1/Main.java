package main.java.edu.virginia.sde.hw1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            throw new IOException("Error - No .csv file given");
        }
        var stateData = new StateData(CSVStateDataReader.getStateDataMap(args[0]));
        var totalSeats = args.length < 2 ? 435 : Integer.parseInt(args[1]);
        var divisor = getDivisor(stateData, totalSeats);
        jeffersonAlgorithm(stateData, totalSeats, divisor);
        for (String stateName : getStatesInAlphabeticalOrder(stateData.statePopulationMap)) {
            var numStateSeats = stateData.apportionmentMap.get(stateName);
            System.out.printf("%s - %d\n", stateName, numStateSeats);
        }
    }

    private static List<String> getStatesInAlphabeticalOrder(Map<String, Integer> stateDataMap) {
        List<String> orderedStates = new ArrayList<>(stateDataMap.keySet());
        orderedStates.sort(CharSequence::compare);
        return orderedStates;
    }

    private static double getDivisor(StateData stateData, int numSeats) {
        return (double) stateData.getTotalPopulation() / numSeats;
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