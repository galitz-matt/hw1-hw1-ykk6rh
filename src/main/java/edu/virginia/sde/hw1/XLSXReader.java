package main.java.edu.virginia.sde.hw1;

import java.util.*;

public class XLSXReader implements Reader {

    protected List<String> fileLines = new ArrayList<>();
    protected Map<String, Integer> statePopulation = new HashMap<>();

    public XLSXReader(String filePath) {
        setFileLines(filePath);
        setStatePopulation();
    }

    private void setFileLines(String filePath) {

    }

    private void setStatePopulation() {

    }

    public Map<String, Integer> getStatePopulation() { return null; }
}
