package main.java.edu.virginia.sde.hw1;

import java.util.*;
public interface Reader {

    private void setFileLines(String filePath) {}

    private void setStatePopulation(List<String> fileLines) {}

    Map<String, Integer> getStatePopulations();

}

