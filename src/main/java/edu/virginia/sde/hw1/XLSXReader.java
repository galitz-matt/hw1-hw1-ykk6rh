package main.java.edu.virginia.sde.hw1;

import java.util.*;
import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSXReader implements Reader {

    private final Map<String, Integer> statePopulations;
    private static int STATE, POPULATION;

    public XLSXReader(String filePath) {
        statePopulations = setStatePopulations(filePath);
    }

    private void findStateAndPopulationHeading(Row headings) {
        for (Cell cell : headings) {
            String heading = cell.getStringCellValue().trim();
            if (heading.equalsIgnoreCase("state")) {
                STATE = cell.getColumnIndex();
            }
            else if (heading.equalsIgnoreCase("population")) {
                POPULATION = cell.getColumnIndex();
            }
        }
        checkStateOrPopulationHeadingExists();
    }

    private void checkStateOrPopulationHeadingExists() {
        if (STATE == -1 || POPULATION == -1) {
            String missingHeading = STATE == -1 ? "State" : "Population";
            throw new RuntimeException(ErrorMessages.columnHeadingNotFoundError(missingHeading));
        }
    }

    private String getStateFromRow(Row row) {
        return row.getCell(STATE).getStringCellValue().trim();
    }

    private int getPopulationFromRow(Row row) {
        return (int) row.getCell(POPULATION).getNumericCellValue();
    }

    private Map<String, Integer> setStatePopulations(String filePath) {
        var statePopulationsBuild = new HashMap<String, Integer>();
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Row headings = sheet.getRow(0);
            findStateAndPopulationHeading(headings);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String state = getStateFromRow(row);
                int population = getPopulationFromRow(row);
                statePopulationsBuild.put(state, population);
            }
        } catch (IOException e) {
            throw new RuntimeException(ErrorMessages.inputError());
        }
        return statePopulationsBuild;
    }

    public Map<String, Integer> getStatePopulations() {
        return statePopulations;
    }
}
