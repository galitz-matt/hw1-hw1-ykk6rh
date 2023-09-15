package main.java.edu.virginia.sde.hw1;

import java.util.*;
import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSXReader implements Reader {

    private Map<String, Integer> statePopulations;
    private static int STATE, POPULATION;

    public XLSXReader(String filePath) {

    }

    private Map<String, Integer> setStatePopulations(String excelFilePath) {
        var statePopulationsBuild = new HashMap<String, Integer>();
        try (FileInputStream inputStream = new FileInputStream(excelFilePath)) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Row headings = sheet.getRow(0);
            for (Cell cell : headings) {
                String heading = cell.getStringCellValue().trim();
                if (heading.equalsIgnoreCase("state")) {
                    STATE = cell.getColumnIndex();
                }
                else if (heading.equalsIgnoreCase("population")) {
                    POPULATION = cell.getColumnIndex();
                }
            }
            if (STATE == -1 || POPULATION == -1) {
                throw new RuntimeException(ErrorMessages.columnHeadingNotFoundError(STATE == -1 ? "State" : "Population"));
            }
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String state = row.getCell(STATE).getStringCellValue().trim();
                int population = (int) row.getCell(POPULATION).getNumericCellValue();
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
