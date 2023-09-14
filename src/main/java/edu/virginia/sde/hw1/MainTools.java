package main.java.edu.virginia.sde.hw1;

public class MainTools {

    protected static void checkForFile(String[] args) {
        if (args.length < 1) {
            throw new RuntimeException(ErrorMessages.noFileGivenError());
        }
    }

    protected static String checkFileType(String filePath) {
        try {
            var parsedFilePath = filePath.split("[\\//]");
            var file = parsedFilePath[parsedFilePath.length - 1];
            return file.split("\\.")[1];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException(ErrorMessages.filePathFormatError(filePath));
        }
    }
    protected static int numberOfSeats(String[] args) {
        return args.length < 2 ? 435 : Integer.parseInt(args[1]);
    }

    protected static double getDivisor(long totalPopulation, int numSeats) {
        return (double) totalPopulation / numSeats;
    }

    protected static void jeffersonAlgorithm(StateData stateData, int totalSeats, double divisor) {
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
