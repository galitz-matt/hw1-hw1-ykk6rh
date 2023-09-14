package main.java.edu.virginia.sde.hw1;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new RuntimeException("Error - No .csv file given");
        }
        var csvReader = new CSVReader(args[0]);
        var stateData = new StateData(csvReader.getStatePopulations());
        var totalSeats = args.length < 2 ? 435 : Integer.parseInt(args[1]);
        var divisor = getDivisor(stateData.getTotalPopulation(), totalSeats);
        jeffersonAlgorithm(stateData, totalSeats, divisor);
        stateData.printData();
    }

    private static double getDivisor(long totalPopulation, int numSeats) {
        return (double) totalPopulation / numSeats;
    }

    private static void jeffersonAlgorithm(StateData stateData, int totalSeats, double divisor) {
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