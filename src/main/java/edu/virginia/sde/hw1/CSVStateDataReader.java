package main.java.edu.virginia.sde.hw1;

import java.io.*;
import java.util.*;

public class CSVStateDataReader implements StateDataReader {

    public static List<String> getLines(String filePath) {
        try (var reader = new BufferedReader(new FileReader(filePath))) {
            var lineList = new ArrayList<String>();
            reader.readLine(); // skip header row
            String line;
            while ((line = reader.readLine()) != null) {
                lineList.add(line);
            }
            return lineList;
        } catch (FileNotFoundException e) {
            var errorMessage = String.format("Error - file \"%s\" not found\n", filePath);
            throw new RuntimeException(errorMessage);
        } catch (IOException e) {
            throw new RuntimeException("Error - verify input correctness");
        }
    }

    public static Map<String, Integer> getStateDataMap(String filePath) {
        var stateDataList = getLines(filePath);
        var stateDataMap = new HashMap<String, Integer>();
        for (int i = 0; i < stateDataList.size(); i++) {
            var keyValue = stateDataList.get(i).split(",");
            try {
                var name = keyValue[0].strip();
                var population = Integer.parseInt(keyValue[1].strip());
                if (population >= 0) {
                    stateDataMap.put(name, population);
                }
                else {
                    System.out.printf("Line %d ignored - Population must be positive integer value - \"%s\" \n", i + 2, stateDataList.get(i));
                }
            }
            catch (NumberFormatException e) {
                System.out.printf("Line %d ignored - Bad format - \"%s\"\n", i + 2, stateDataList.get(i));
            }
            catch (ArrayIndexOutOfBoundsException e) {
                String errorMessage = String.format("Error - Line %d has bad format - \"%s\"", i + 2, stateDataList.get(i));
                throw new RuntimeException(errorMessage);
            }
        }
        if (stateDataMap.isEmpty()) {
            throw new RuntimeException("\nError - .csv file is empty or no lines are properly formatted");
        }
        return stateDataMap;
    }

}