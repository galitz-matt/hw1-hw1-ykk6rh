package main.java.edu.virginia.sde.hw1;

import java.io.*;
import java.util.*;

public class CSVReader {

    public static List<String> getListFromCSV(String filePath) {
        try (var reader = new BufferedReader(new FileReader(filePath))) {
            var lineList = new ArrayList<String>();
            reader.readLine(); // skip header row
            String line;
            while ((line = reader.readLine()) != null) {
                lineList.add(line);
            }
            return lineList;
        } catch (FileNotFoundException e) {
            System.out.printf("Error - file \"%s\" not found\n", filePath);
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Error - verify input correctness");
            System.exit(1);
        }
        return null;
    }

    public static List<String> getStatesInAlphabeticalOrder(Map<String, Integer> csvMap) {
        List<String> orderedStates = new ArrayList<>(csvMap.keySet());
        orderedStates.sort(CharSequence::compare);
        return orderedStates;
    }

    public static Map<String, Integer> getMapFromCSV(String filePath) throws IOException {
        var csvList = getListFromCSV(filePath);
        var csvMap = new HashMap<String, Integer>();
        for (int i = 0; i < csvList.size(); i++) {
            var keyValue = csvList.get(i).split(",");
            try {
                var stateName = keyValue[0].strip();
                var statePopulation = Integer.parseInt(keyValue[1].strip());
                if (statePopulation >= 0) {
                    csvMap.put(stateName, statePopulation);
                } else {
                    System.out.printf("Line %d ignored - Population must be positive integer value - \"%s\" \n", i + 2, csvList.get(i));
                }
            } catch (NumberFormatException e) {
                System.out.printf("Line %d ignored - Population must be given numerically - \"%s\"\n", i + 2, csvList.get(i));
            } catch (ArrayIndexOutOfBoundsException e) {
                String errorMessage = String.format("Error - Line %d has bad format - \"%s\"", i + 2, csvList.get(i));
                throw new IOException(errorMessage);
            }
        }
        if (csvMap.isEmpty()) {
            throw new IOException("\nError - .csv file is empty or no lines are properly formatted");
        }
        return csvMap;
    }

}