package main.java.edu.virginia.sde.hw1;

import java.io.*;
import java.util.*;

public class CSVReader implements Reader {

    private final List<String> fileLines;
    private final Map<String, Integer> statePopulations;
    private int STATE,
            POPULATION;

    public CSVReader(String filePath) {
        fileLines = setFileLines(filePath);
        statePopulations = setStatePopulations();
    }

    private static String[] getParsedHeadings(String headings) {
        return Arrays.stream(headings.split(",")).map(String::trim).map(String::toLowerCase).toArray(String[]::new);
    }

    private int findStateHeading(String[] parsedHeadings) {
        for (int i = 0; i < parsedHeadings.length; i++) {
            if (parsedHeadings[i].equalsIgnoreCase("state")) {
                return i;
            }
        }
        throw new RuntimeException("Error - \"State\" column heading not found");
    }

    private int findPopulationHeading(String[] parsedHeader) {
        for (int i = 0; i < parsedHeader.length; i++) {
            if (parsedHeader[i].equalsIgnoreCase("population")) {
                return i;
            }
        }
        throw new RuntimeException("Error - \"Population\" column heading not found");
    }

    private void processHeadings(BufferedReader reader) {
        try {
            var headings = reader.readLine();
            var parsedHeadings = getParsedHeadings(headings);
            STATE = findStateHeading(parsedHeadings);
            POPULATION = findPopulationHeading(parsedHeadings);
        }
        catch (IOException e) {
            throw new RuntimeException("Error - check input");
        }
    }

    private void populateFileLinesBuild(BufferedReader reader, List<String> fileLinesBuild) {
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                fileLinesBuild.add(line);
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Error - check input");
        }
    }

    private List<String> setFileLines(String filePath) {
        var fileLinesBuild = new ArrayList<String>();
        try (var reader = new BufferedReader(new FileReader(filePath))) {
            processHeadings(reader);
            populateFileLinesBuild(reader, fileLinesBuild);
        } catch (IOException e) {
            var errorMessage = String.format("Error - file \"%s\" not found\n", filePath);
            throw new RuntimeException(errorMessage);
        }
        return fileLinesBuild;
    }

    private String[] getParsedLine(int lineNumber) {
        return fileLines.get(lineNumber).split(",");
    }

    private Map<String, Integer> setStatePopulations() {
        var statePopulationsBuild = new HashMap<String, Integer>();
        for (int lineNumber = 0; lineNumber < fileLines.size(); lineNumber++) {
            var parsedLine = getParsedLine(lineNumber);
            try {
                var name = parsedLine[STATE].strip();
                var population = Integer.parseInt(parsedLine[POPULATION].strip());
                if (population >= 0) {
                    statePopulationsBuild.put(name, population);
                }
                else {
                    System.out.printf("Line %d ignored - Population must be positive integer value - \"%s\" \n", lineNumber + 2, fileLines.get(lineNumber));
                }
            }
            catch (NumberFormatException e) {
                System.out.printf("Line %d ignored - Bad format - \"%s\"\n", lineNumber + 2, fileLines.get(lineNumber));
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.printf("Line %d ignored - Bad format - \"%s\"", lineNumber + 2, fileLines.get(lineNumber));
            }
        }
        if (statePopulationsBuild.isEmpty()) {
            throw new RuntimeException("\nError - .csv file is empty or no lines are properly formatted");
        }
        return statePopulationsBuild;
    }

    public Map<String, Integer> getStatePopulations() {
        return statePopulations;
    }

}