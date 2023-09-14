package main.java.edu.virginia.sde.hw1;

import java.util.List;
public class ErrorMessages {

    public static String inputError() {
        return "Error - check input";
    }
    public static String columnHeadingNotFoundError(String heading) {
        return String.format("\nERROR: \"%s\" column heading not found", heading);
    }

    public static String fileNotFoundError(String filePath) {
        return String.format("ERROR: Cannot find file \"%s\"", filePath);
    }

    public static String negativePopulationError(List<String> fileLines, int lineNumber) {
        return String.format("Line %d ignored because population must be positive integer value: \"%s\"", lineNumber + 2, fileLines.get(lineNumber));
    }

    public static String badFileFormatError(String fileType) {
        return String.format("\nERROR: %s file is empty or no lines are properly formatted", fileType);
    }

    public static String badLineFormatError(List<String> fileLines, int lineNumber) {
        return String.format("Line %d ignored because of bad format: \"%s\"", lineNumber + 2, fileLines.get(lineNumber));
    }

    public static String noFileGivenError() {
        return "ERROR: No file given";
    }
}
