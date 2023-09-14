package main.java.edu.virginia.sde.hw1;

import java.util.*;

public class XLSXReader implements Reader {

    private List<String> fileLines = new ArrayList<>();
    private Map<String, Integer> statePopulation = new HashMap<>();

    public XLSXReader(String filePath) {
        setFileLines(filePath);
        setStatePopulation();
    }

    private void setFileLines(String filePath) {

    }

    private void setStatePopulation() {

    }

    public List<String> getFileLines() { return null; }

    public Map<String, Integer> getStatePopulation() { return null; }
}
