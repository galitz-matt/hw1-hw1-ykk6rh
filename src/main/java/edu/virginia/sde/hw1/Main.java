package main.java.edu.virginia.sde.hw1;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            var csvMap = CSVReader.getMapFromCSV(args[0]);
        }
        catch (IOException e) {
            throw new IOException("\nError - check input");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error - no .csv file given");
            System.out.println(1);
        }
    }



}
