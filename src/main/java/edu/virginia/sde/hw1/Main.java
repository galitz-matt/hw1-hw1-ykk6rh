package main.java.edu.virginia.sde.hw1;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            var csvMap = CSVReader.getMapFromCSV(args[0]);
            int numSeats = args.length < 2 ? 435 : Integer.parseInt(args[1]);
        }
        catch (IOException e) {
            throw new IOException("\nError - check input");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error - no .csv file given");
            System.out.println(1);
        }
    }

    private static int getTotalPopulation(Map<String, Integer> csvMap) {
        int totalPopulation = 0;
        for (String stateName : csvMap.keySet()) {
            totalPopulation += csvMap.get(stateName);
        }
        return totalPopulation;
    }

    private static int getDivisor(int totalPopulation, int numSeats) {
        return totalPopulation / numSeats;
    }

}
