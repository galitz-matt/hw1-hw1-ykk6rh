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

    private int findXHeading(String[] parsedHeadings, String heading) {
        for (int i = 0; i < parsedHeadings.length; i++) {
            if (parsedHeadings[i].equalsIgnoreCase(heading.toLowerCase())) {
                return i;
            }
        }
        throw new RuntimeException(ErrorMessages.columnHeadingNotFoundError(heading));
    }

    private void processHeadings(BufferedReader reader) {
        try {
            var headings = reader.readLine();
            var parsedHeadings = getParsedHeadings(headings);
            STATE = findXHeading(parsedHeadings, "state");
            POPULATION = findXHeading(parsedHeadings, "population");
        }
        catch (IOException e) {
            throw new RuntimeException(ErrorMessages.inputError());
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
            throw new RuntimeException(ErrorMessages.inputError());
        }
    }

    private List<String> setFileLines(String filePath) {
        var fileLinesBuild = new ArrayList<String>();
        try (var reader = new BufferedReader(new FileReader(filePath))) {
            processHeadings(reader);
            populateFileLinesBuild(reader, fileLinesBuild);
        } catch (IOException e) {
            throw new RuntimeException(ErrorMessages.fileNotFoundError(filePath));
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
            System.out.println(ErrorMessages.negativePopulationError(fileLines, lineNumber));
        }
    }

    private void checkEmptyStatePopulationsBuild(Map<String, Integer> statePopulationsBuild) {
        if (statePopulationsBuild.isEmpty()) {
            throw new RuntimeException(ErrorMessages.fileContentFormatError());
        }
    }

    private Map<String, Integer> setStatePopulations() {
        var statePopulationsBuild = new HashMap<String, Integer>();
        for (int lineNumber = 0; lineNumber < fileLines.size(); lineNumber++) {
            try {
                putLineToStatePopulationsBuild(statePopulationsBuild, lineNumber);
            }
            catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println(ErrorMessages.lineFormatError(fileLines, lineNumber));
            }
        }
        checkEmptyStatePopulationsBuild(statePopulationsBuild);
        return statePopulationsBuild;
    }

    public Map<String, Integer> getStatePopulations() {
        return statePopulations;
    }

}