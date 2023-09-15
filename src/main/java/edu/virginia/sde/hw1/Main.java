package main.java.edu.virginia.sde.hw1;

public class Main {

    public static void main(String[] args) {
        MainTools.checkForFile(args);
        var filePath = args[0];
        var fileExtension = MainTools.getFileExtension(filePath);
        var reader = MainTools.getReader(filePath, fileExtension);
        var stateData = new StateData(reader.getStatePopulations());
        var totalSeats = MainTools.numberOfSeats(args);
        var divisor = MainTools.getDivisor(stateData.getTotalPopulation(), totalSeats);
        MainTools.jeffersonAlgorithm(stateData, totalSeats, divisor);
        stateData.printData();
    }

}