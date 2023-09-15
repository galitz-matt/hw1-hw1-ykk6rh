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

    public static String fileContentFormatError() {
        return "ERROR: input file is empty or content not properly formatted";
    }

    public static String lineFormatError(List<String> fileLines, int lineNumber) {
        return String.format("Line %d ignored because of bad format: \"%s\"", lineNumber + 2, fileLines.get(lineNumber));
    }

    public static String noFileGivenError() {
        return "ERROR: No file given";
    }

    public static String filePathFormatError(String filePath) {
        return String.format("ERROR: File path, \"%s\" has bad format", filePath);
    }

    public static String invalidFileExtensionError() {
        return "ERROR: File extension is not .csv or .xlsx";
    }

}
