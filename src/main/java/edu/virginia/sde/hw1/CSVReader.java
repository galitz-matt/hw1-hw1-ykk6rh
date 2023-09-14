package main.java.edu.virginia.sde.hw1;

import java.io.*;
import java.util.*;

public class CSVReader implements Reader {

    private final List<String> fileLines = new ArrayList<>();
    private final Map<String, Integer> statePopulation = new HashMap<>();
    private final int STATE_NAME = 0;
    private final int STATE_POPULATION = 1;

    public CSVReader(String filePath) {
        setFileLines(filePath);
        setStatePopulation(fileLines);
    }

    private void setFileLines(String filePath) {
        try (var reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); // skip header row
            String line;
            while ((line = reader.readLine()) != null) {
                fileLines.add(line);
            }
        } catch (FileNotFoundException e) {
            var errorMessage = String.format("Error - file \"%s\" not found\n", filePath);
            throw new RuntimeException(errorMessage);
        } catch (IOException e) {
            throw new RuntimeException("Error - verify input correctness");
        }
    }

    private void setStatePopulation(List<String> fileLines) {
        for (int lineNumber = 0; lineNumber < fileLines.size(); lineNumber++) {
            var splitLine = fileLines.get(lineNumber).split(",");
            try {
                var name = splitLine[STATE_NAME].strip();
                var population = Integer.parseInt(splitLine[STATE_POPULATION].strip());
                if (population >= 0) {
                    statePopulation.put(name, population);
                }
                else {
                    System.out.printf("Line %d ignored - Population must be positive integer value - \"%s\" \n", lineNumber + 2, fileLines.get(lineNumber));
                }
            }
            catch (NumberFormatException e) {
                System.out.printf("Line %d ignored - Bad format - \"%s\"\n", lineNumber + 2, fileLines.get(lineNumber));
            }
            catch (ArrayIndexOutOfBoundsException e) {
                String errorMessage = String.format("Error - Line %d has bad format - \"%s\"", lineNumber + 2, fileLines.get(lineNumber));
                throw new RuntimeException(errorMessage);
            }
        }
        if (statePopulation.isEmpty()) {
            throw new RuntimeException("\nError - .csv file is empty or no lines are properly formatted");
        }
    }

    public Map<String, Integer> getStatePopulation() {
        return statePopulation;
    }

}