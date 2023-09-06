package main.java.edu.virginia.sde.hw1;

import java.util.*;
public class StateData {

    public Map<String, Integer> statePopulationMap;
    public Map<String, Integer> apportionmentMap;


    public StateData(Map<String, Integer> csvMap) {
        statePopulationMap = csvMap;
        apportionmentMap = new HashMap<>();
    }

    public int getTotalPopulation() {
        int totalPopulation = 0;
        for (String stateName : statePopulationMap.keySet()) {
            totalPopulation += statePopulationMap.get(stateName);
        }
        return totalPopulation;
    }

    public void setApportionmentMap(double divisor) {
        for (String stateName : statePopulationMap.keySet()) {
            apportionmentMap.put(stateName, (int) Math.floor((double) statePopulationMap.get(stateName) / divisor));
        }
    }

    public int getApportionedSeats() {
        int apportionedSeats = 0;
        for (String stateName : apportionmentMap.keySet()) {
            apportionedSeats += apportionmentMap.get(stateName);
        }
        return apportionedSeats;
    }

}
