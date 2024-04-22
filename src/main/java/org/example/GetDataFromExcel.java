package org.example;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.*;

public class GetDataFromExcel {

    public double[][] getDataFromSheet(String filePath, String sheetName) {
        try (FileInputStream file = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                JOptionPane.showMessageDialog(null, "Лист не найден", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            int rows = sheet.getPhysicalNumberOfRows();
            if (rows < 2) {
                JOptionPane.showMessageDialog(null, "Лист пустой или содержит только заголовок", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            Row headerRow = sheet.getRow(0);
            int cols = headerRow.getPhysicalNumberOfCells();

            double[][] data = new double[cols][rows - 1]; // Уменьшаем количество строк на 1, чтобы пропустить заголовок

            // Считывание данных из файла
            for (int i = 0; i < cols; i++) {
                int columnRowCount = 0;
                for (int j = 1; j < rows; j++) { // Начинаем считывание с первой строки после заголовка
                    Row row = sheet.getRow(j);
                    if (row != null) {
                        Cell cell = row.getCell(i);
                        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                            data[i][j - 1] = cell.getNumericCellValue();
                            columnRowCount++;
                        } else {
                            // Если ячейка не содержит числового значения или пустая, записываем NaN
                            data[i][j - 1] = Double.NaN;
                        }
                    } else {
                        // Если строка отсутствует, записываем NaN
                        data[i][j - 1] = Double.NaN;
                    }
                }
                // Проверка на одинаковое количество строк в каждом столбце
                if (columnRowCount != rows - 1) {
                    JOptionPane.showMessageDialog(null, "В столбце " + (i + 1) + " меньшее кол-во строк", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return null;
                }
            }

            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}