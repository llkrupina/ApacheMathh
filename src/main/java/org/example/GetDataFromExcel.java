package org.example;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetDataFromExcel {

    public double[][] getDataFromSheet(String filePath, String sheetName) {
        try (FileInputStream file = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                System.out.println("Лист не найден.");
                return null;
            }

            int rows = sheet.getPhysicalNumberOfRows();
            if (rows < 2) {
                System.out.println("Лист пустой или содержит только заголовок.");
                return null;
            }

            Row headerRow = sheet.getRow(0);
            int cols = headerRow.getPhysicalNumberOfCells();

            double[][] data = new double[cols][rows - 1]; // Уменьшаем количество строк на 1, чтобы пропустить заголовок

            for (int i = 0; i < cols; i++) {
                for (int j = 1; j < rows; j++) { // Начинаем считывание с первой строки после заголовка
                    Row row = sheet.getRow(j);
                    if (row != null) {
                        Cell cell = row.getCell(i);
                        if (cell != null) {
                            if (cell.getCellType() == CellType.NUMERIC) {
                                data[i][j-1] = cell.getNumericCellValue();
                            } else {
                                System.out.println("Строка " + (j+1) + ", колонка " + (i+1) + " содержит не число.");
                                data[i][j-1] = Double.NaN; // Записываем NaN вместо числа
                            }
                        }
                    }
                }
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}

