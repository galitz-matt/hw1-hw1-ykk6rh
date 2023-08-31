package main.java.edu.virginia.sde.hw1;
import java.io.*;
import java.nio.Buffer;
import java.util.*;

public class CSVReader {

    public static List<String> readCSV(String filePath) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<String> allLines = new ArrayList<>();
            String line = reader.readLine(); // skip first line (State, Population)
            while((line = reader.readLine()) != null) {
                allLines.add(line);
            }
            return allLines;
        }
        catch (IOException e) {
            throw new FileNotFoundException("File not found - file does not exist or was entered incorrectly");
        }
    }
}