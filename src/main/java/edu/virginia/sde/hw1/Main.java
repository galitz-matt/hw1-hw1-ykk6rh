package main.java.edu.virginia.sde.hw1;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new RuntimeException("Error - No .csv file given");
        }
        var csvReader = new CSVReader(args[0]);
        var stateData = new StateData(csvReader.getStatePopulations());
        var totalSeats = args.length < 2 ? 435 : Integer.parseInt(args[1]);
        var divisor = MainTools.getDivisor(stateData.getTotalPopulation(), totalSeats);
        MainTools.jeffersonAlgorithm(stateData, totalSeats, divisor);
        stateData.printData();
    }

}