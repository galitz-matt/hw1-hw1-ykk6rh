package main.java.edu.virginia.sde.hw1;

import java.util.*;
public class StateData {

    private Map<String, Integer> statePopulation;
    private Map<String, Integer> apportionmentMap = new HashMap<>();
    private long totalPopulation;


    public StateData(Map<String, Integer> dataMap) {
        statePopulation = dataMap;
        setTotalPopulation();
    }

    public void setTotalPopulation() {
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

    public Map<String, Integer> getStatePopulation() {
        return statePopulation;
    }

    public Map<String, Integer> getApportionmentMap() {
        return apportionmentMap;
    }

    public long getTotalPopulation() {
        return totalPopulation;
    }
}
