package main.java.edu.virginia.sde.hw1;

import java.util.*;
import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSXReader implements Reader {

    private final List<String> fileLines = new ArrayList<>();
    private final Map<String, Integer> statePopulation = new HashMap<>();

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
