package main.java.edu.virginia.sde.hw1;

import java.io.*;
import java.util.*;

public class CSVReader implements Reader {

    private List<String> fileLines = new ArrayList<>();
    private Map<String, Integer> statePopulation = new HashMap<>();

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
        for (int i = 0; i < fileLines.size(); i++) {
            var keyValue = fileLines.get(i).split(",");
            try {
                var name = keyValue[0].strip();
                var population = Integer.parseInt(keyValue[1].strip());
                if (population >= 0) {
                    statePopulation.put(name, population);
                }
                else {
                    System.out.printf("Line %d ignored - Population must be positive integer value - \"%s\" \n", i + 2, fileLines.get(i));
                }
            }
            catch (NumberFormatException e) {
                System.out.printf("Line %d ignored - Bad format - \"%s\"\n", i + 2, fileLines.get(i));
            }
            catch (ArrayIndexOutOfBoundsException e) {
                String errorMessage = String.format("Error - Line %d has bad format - \"%s\"", i + 2, fileLines.get(i));
                throw new RuntimeException(errorMessage);
            }
        }
        if (statePopulation.isEmpty()) {
            throw new RuntimeException("\nError - .csv file is empty or no lines are properly formatted");
        }
    }

    public List<String> getFileLines() {
        return fileLines;
    }

    public Map<String, Integer> getStatePopulation() {
        return statePopulation;
    }

}