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
        for (String stateName : statePopulations.keySet()) {
            totalPopulationBuild += statePopulations.get(stateName);
        }
        return totalPopulationBuild;
    }

    public void setApportionmentMap(double divisor) {
        for (String stateName : statePopulations.keySet()) {
            apportionmentMap.put(stateName, (int) Math.floor((double) statePopulations.get(stateName) / divisor));
        }
    }

    public int getApportionedSeats() {
        int apportionedSeats = 0;
        for (String stateName : apportionmentMap.keySet()) {
            apportionedSeats += apportionmentMap.get(stateName);
        }
        return apportionedSeats;
    }

    public List<String> getStatesInAlphabeticalOrder() {
        List<String> orderedStates = new ArrayList<>(statePopulations.keySet());
        orderedStates.sort(CharSequence::compare);
        return orderedStates;
    }

    public Map<String, Integer> getApportionmentMap() {
        return apportionmentMap;
    }

    public long getTotalPopulation() {
        return totalPopulation;
    }

    public void printData() {

        int numStateSeats;
        for (String stateName : getStatesInAlphabeticalOrder()) {
            numStateSeats = apportionmentMap.get(stateName);
            System.out.printf("%s - %d\n", stateName, numStateSeats);
        }
    }
}
