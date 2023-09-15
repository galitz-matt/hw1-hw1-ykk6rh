package main.java.edu.virginia.sde.hw1;

import java.util.*;

public class StateData {

    private final Map<String, Integer> statePopulations;
    private Map<String, Integer> apportionmentMap = new HashMap<>();
    private final long totalPopulation;


    public StateData(Map<String, Integer> dataMap) {
        statePopulations = dataMap;
        totalPopulation = setTotalPopulation();
    }

    private long setTotalPopulation() {
        var totalPopulationBuild = 0;
        for (var stateName : statePopulations.keySet()) {
            totalPopulationBuild += statePopulations.get(stateName);
        }
        return totalPopulationBuild;
    }

    public void setApportionmentMap(double divisor) {
        for (var stateName : statePopulations.keySet()) {
            apportionmentMap.put(stateName, (int) Math.floor((double) statePopulations.get(stateName) / divisor));
        }
    }

    public int getApportionedSeats() {
        int apportionedSeats = 0;
        for (var stateName : apportionmentMap.keySet()) {
            apportionedSeats += apportionmentMap.get(stateName);
        }
        return apportionedSeats;
    }

    public List<String> getStatesInAlphabeticalOrder() {
        var orderedStates = new ArrayList<>(statePopulations.keySet());
        orderedStates.sort(CharSequence::compare);
        return orderedStates;
    }

    public long getTotalPopulation() {
        return totalPopulation;
    }

    private String whiteSpace(int length) {
        return " ".repeat(length);
    }

    private String getOutputHeader() {
        return "State" + whiteSpace(11) + "|Population|" + whiteSpace(1) + "Reps";
    }

    private String getOutputForState(String state) {
        var population = String.valueOf(statePopulations.get(state));
        var reps = String.valueOf(apportionmentMap.get(state));
        return String.format("%s%s|%s%s|%s%s", state,
                whiteSpace(16-state.length()),
                whiteSpace(10-population.length()),
                population,
                whiteSpace(5-reps.length()),
                reps);
    }

    public void printData() {
        System.out.println(getOutputHeader());
        for (var state : getStatesInAlphabeticalOrder()) {
            System.out.println(getOutputForState(state));
        }
    }
}
