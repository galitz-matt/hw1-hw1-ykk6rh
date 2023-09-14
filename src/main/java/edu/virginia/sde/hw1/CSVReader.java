package main.java.edu.virginia.sde.hw1;

import java.io.*;
import java.util.*;

public class CSVReader implements Reader {

    private List<String> fileLines;
    private Map<String, Integer> statePopulation;
    private int STATE_NAME = -1,
            STATE_POPULATION = -1;

    public CSVReader(String filePath) {
        fileLines = setFileLines(filePath);
        statePopulation = setStatePopulation(fileLines);
    }

    private List<String> setFileLines(String filePath) {
        var fileLinesBuild = new ArrayList<String>();
        try (var reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); // skip header row
            String line;
            while ((line = reader.readLine()) != null) {
                fileLinesBuild.add(line);
            }
            return fileLinesBuild;
        } catch (FileNotFoundException e) {
            var errorMessage = String.format("Error - file \"%s\" not found\n", filePath);
            throw new RuntimeException(errorMessage);
        } catch (IOException e) {
            throw new RuntimeException("Error - verify input correctness");
        }
    }

    private Map<String, Integer> setStatePopulation(List<String> fileLines) {
        var statePopulationBuild = new HashMap<String, Integer>();
        for (int lineNumber = 0; lineNumber < fileLines.size(); lineNumber++) {
            var splitLine = fileLines.get(lineNumber).split(",");
            try {
                var name = splitLine[STATE_NAME].strip();
                var population = Integer.parseInt(splitLine[STATE_POPULATION].strip());
                if (population >= 0) {
                    statePopulationBuild.put(name, population);
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
        return statePopulationBuild;
    }

    public Map<String, Integer> getStatePopulation() {
        return statePopulation;
    }

}