package main.java.edu.virginia.sde.hw1;

import java.util.*;
public class StateData {

    private final Map<String, Integer> statePopulation;
    private Map<String, Integer> apportionmentMap = new HashMap<>();
    private long totalPopulation;


    public StateData(Map<String, Integer> dataMap) {
        statePopulation = dataMap;
        setTotalPopulation();
    }

    private void setTotalPopulation() {
        for (String stateName : statePopulation.keySet()) {
            totalPopulation += statePopulation.get(stateName);
        }
    }

    public void setApportionmentMap(double divisor) {
        for (String stateName : statePopulation.keySet()) {
            apportionmentMap.put(stateName, (int) Math.floor((double) statePopulation.get(stateName) / divisor));
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
        List<String> orderedStates = new ArrayList<>(statePopulation.keySet());
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
            numStateSeats = getApportionmentMap().get(stateName);
            System.out.printf("%s - %d\n", stateName, numStateSeats);
        }
    }
}
