package main.java.edu.virginia.sde.hw1;

import java.io.*;
import java.util.*;

public class CSVReader implements Reader {

    private final List<String> fileLines;
    private final Map<String, Integer> statePopulations;
    private int STATE, POPULATION;

    public CSVReader(String filePath) {
        fileLines = setFileLines(filePath);
        statePopulations = setStatePopulations();
    }

    private static String[] getParsedHeadings(String headings) {
        return Arrays.stream(headings.split(",")).map(String::trim).map(String::toLowerCase).toArray(String[]::new);
    }

    private int findXHeading(String[] parsedHeadings, String X) {
        for (int i = 0; i < parsedHeadings.length; i++) {
            if (parsedHeadings[i].equalsIgnoreCase(X.toLowerCase())) {
                return i;
            }
        }
        String errorMessage = String.format("Error - \"%s\" column heading not found", X);
        throw new RuntimeException(errorMessage);
    }

    private void processHeadings(BufferedReader reader) {
        try {
            var headings = reader.readLine();
            var parsedHeadings = getParsedHeadings(headings);
            STATE = findXHeading(parsedHeadings, "state");
            POPULATION = findXHeading(parsedHeadings, "population");
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

    private String getNameFromParsedLine(String[] parsedLine) {
        return parsedLine[STATE].strip();
    }

    private int getPopulationFromParsedLine(String[] parsedLine) {
        return Integer.parseInt(parsedLine[POPULATION].strip());
    }

    private void putLineToStatePopulationsBuild(Map<String, Integer> statePopulationsBuild, int lineNumber) {
        var parsedLine = getParsedLine(lineNumber);
        var name = getNameFromParsedLine(parsedLine);
        var population = getPopulationFromParsedLine(parsedLine);
        if (population >= 0) {
            statePopulationsBuild.put(name, population);
        }
        else {
            System.out.printf("Line %d ignored - Population must be positive integer value - \"%s\" \n", lineNumber + 2, fileLines.get(lineNumber));
        }
    }

    private void addressEmptyStatePopulationsBuild(Map<String, Integer> statePopulationsBuild) {
        if (statePopulationsBuild.isEmpty()) {
            throw new RuntimeException("\nError - .csv file is empty or no lines are properly formatted");
        }
    }

    private Map<String, Integer> setStatePopulations() {
        var statePopulationsBuild = new HashMap<String, Integer>();
        for (int lineNumber = 0; lineNumber < fileLines.size(); lineNumber++) {
            try {
                putLineToStatePopulationsBuild(statePopulationsBuild, lineNumber);
            }
            catch (NumberFormatException e) {
                System.out.printf("Line %d ignored - Bad format - \"%s\"\n", lineNumber + 2, fileLines.get(lineNumber));
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.printf("Line %d ignored - Bad format - \"%s\"", lineNumber + 2, fileLines.get(lineNumber));
            }
        }
        addressEmptyStatePopulationsBuild(statePopulationsBuild);
        return statePopulationsBuild;
    }

    public Map<String, Integer> getStatePopulations() {
        return statePopulations;
    }

}