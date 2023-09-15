package main.java.edu.virginia.sde.hw1;

public class Main {

    public static void main(String[] args) {
        MainTools.checkForFile(args);

        var csvReader = new CSVReader(args[0]);
        var stateData = new StateData(csvReader.getStatePopulations());
        var totalSeats = MainTools.numberOfSeats(args);
        var divisor = MainTools.getDivisor(stateData.getTotalPopulation(), totalSeats);
        MainTools.jeffersonAlgorithm(stateData, totalSeats, divisor);
        stateData.printData();
    }

}