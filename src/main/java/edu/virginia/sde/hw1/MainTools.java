package main.java.edu.virginia.sde.hw1;

import java.util.Arrays;

public class MainTools {
    public static void checkForFile(String[] args) {
        if (args.length < 1) {
            throw new RuntimeException(ErrorMessages.noFileGivenError());
        }
    }


    private static boolean isValidExtension(String fileExtension) {
        return fileExtension.equals("csv") || fileExtension.equals("xlsx");
    }
    public static String getFileExtension(String filePath) {
        try {
            var parsedFilePath = filePath.split("\\.");
            var fileExtension = parsedFilePath[parsedFilePath.length - 1];
            if (!isValidExtension(fileExtension)) {
                throw new RuntimeException(ErrorMessages.invalidFileExtensionError());
            }
            return fileExtension;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException(ErrorMessages.filePathFormatError(filePath));
        }
    }

    public static Reader getReader(String filePath, String fileType) {
        return fileType.equals(".csv") ? new CSVReader(filePath) : new XLSXReader(filePath);
    }
    public static int numberOfSeats(String[] args) {
        return args.length < 2 ? 435 : Integer.parseInt(args[1]);
    }

    public static double getDivisor(long totalPopulation, int numSeats) {
        return (double) totalPopulation / numSeats;
    }

    public static void jeffersonAlgorithm(StateData stateData, int totalSeats, double divisor) {
        int apportionedSeats;
        double low = 0;
        double high = divisor;
        double mid = divisor / 2;
        while (true) {
            stateData.setApportionmentMap(mid);
            apportionedSeats = stateData.getApportionedSeats();
            if (apportionedSeats < totalSeats) {
                high = mid;
            }
            else if (apportionedSeats > totalSeats) {
                low = mid;
            }
            else {
                break;
            }
            mid = (low + high) / 2;
        }
    }
}
