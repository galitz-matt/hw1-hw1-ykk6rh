package main.java.edu.virginia.sde.hw1;

import java.util.List;
public class ErrorMessages {

    public static String inputError() {
        return "Error - check input";
    }
    public static String columnHeadingNotFoundError(String heading) {
        return String.format("Error - \"%s\" column heading not found", heading);
    }

    public static String fileNotFoundError(String filePath) {
        return String.format("Error - file \"%s\" not found\n", filePath);
    }

    public static String negativePopulationError(List<String> fileLines, int lineNumber) {
        return String.format("Line %d ignored - Population must be positive integer value - \"%s\" \n", lineNumber + 2, fileLines.get(lineNumber));
    }

    public static String badFileFormatError(String fileType) {
        return String.format("\nError - %s file is empty or no lines are properly formatted", fileType);
    }

    public static String badLineFormat(List<String> fileLines, int lineNumber) {
        return String.format("Line %d ignored - Bad format - \"%s\"\n", lineNumber + 2, fileLines.get(lineNumber));
    }
}
