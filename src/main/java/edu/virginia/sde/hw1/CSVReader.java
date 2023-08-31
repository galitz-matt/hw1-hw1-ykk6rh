package main.java.edu.virginia.sde.hw1;
import java.io.*;
import java.util.*;

public class CSVReader {
    public static void read(String filepath) throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for (String value : values) {
                    System.out.print(value + " ");
                }
                System.out.println(); // Move to the next line
            }
        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found: file entered does not exist or was entered incorrectly");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}