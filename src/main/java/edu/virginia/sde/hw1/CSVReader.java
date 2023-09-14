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
        statePopulations = setStatePopulations(fileLines);
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
        throw new RuntimeException("\"State\" column heading not found");
    }

    private int findPopulationHeading(String[] parsedHeader) {
        for (int i = 0; i < parsedHeader.length; i++) {
            if (parsedHeader[i].equalsIgnoreCase("population")) {
                return i;
            }
        }
        throw new RuntimeException("\"Population\" column heading not found");
    }

    private void processHeadings(BufferedReader reader) {
        try {
            var headings = reader.readLine();
            var parsedHeadings = getParsedHeadings(headings);
            STATE = findStateHeading(parsedHeadings);
            POPULATION = findPopulationHeading(parsedHeadings);
        }
        catch (IOException e) {
            throw new RuntimeException("Error");
        }
    }

    private List<String> setFileLines(String filePath) {
        var fileLinesBuild = new ArrayList<String>();
        try (var reader = new BufferedReader(new FileReader(filePath))) {
            processHeadings(reader);
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

    private Map<String, Integer> setStatePopulations(List<String> fileLines) {
        var statePopulationsBuild = new HashMap<String, Integer>();
        for (int lineNumber = 0; lineNumber < fileLines.size(); lineNumber++) {
            var splitLine = fileLines.get(lineNumber).split(",");
            try {
                var name = splitLine[STATE].strip();
                var population = Integer.parseInt(splitLine[POPULATION].strip());
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
                String errorMessage = String.format("Error - Line %d has bad format - \"%s\"", lineNumber + 2, fileLines.get(lineNumber));
                throw new RuntimeException(errorMessage);
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