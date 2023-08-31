package main.java.edu.virginia.sde.hw1;

import java.io.*;
import java.util.*;

public class CSVReader {

    public static List<String> generateListFromCSV(String filePath) {
        try (var reader = new BufferedReader(new FileReader(filePath))) {
            var lineList = new ArrayList<String>();
            reader.readLine(); // skip header row
            String line;
            while ((line = reader.readLine()) != null) {
                lineList.add(line);
            }
            return lineList;
        }
        catch (FileNotFoundException e) {
            System.out.println("Error - .csv file does not exist or was entered incorrectly");
            System.exit(1);
        }
        catch (IOException e) {
            System.out.println("Error - make sure correct file path was entered");
            System.exit(1);
        }
        return null;
    }

    public static Map<String, Integer> generateMapFromCSV(String filePath) {
        var csvList = generateListFromCSV(filePath);
        var statePopulation = new HashMap<String, Integer>();
        for (int i = 0; i < csvList.size(); i++) {
            var keyValue = csvList.get(i).split(",");
            try {
                statePopulation.put(keyValue[0].strip(), Integer.parseInt(keyValue[1].strip()));
            }
            catch (NumberFormatException e) {
                System.out.printf("Line %d - Bad Format - %s\n", i+2, csvList.get(i));
            }
        }
        return statePopulation;
    }

}