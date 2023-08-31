package main.java.edu.virginia.sde.hw1;

import java.io.*;
import java.util.*;

public class CSVReader {

    public static List<String> readCSV(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<String> allLines = new ArrayList<>();
            reader.readLine(); // skip first line
            String line;
            while((line = reader.readLine()) != null) {
                allLines.add(line);
            }
            return allLines;
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

    public static void main(String[] args) {
        try {
            String filePath = args[0];
            String[] lines = readCSV(filePath).toArray(new String[0]);
            System.out.println(Arrays.toString(lines));
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error - enter f");
            System.exit(1);
        }
    }

}