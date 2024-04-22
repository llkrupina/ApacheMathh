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
                for (int j = 1; j < rows; j++) { // Начинаем считывание с первой строки после заголовка
                    Row row = sheet.getRow(j);
                    if (row != null) {
                        Cell cell = row.getCell(i);
                        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                            data[i][j - 1] = cell.getNumericCellValue();
                        } else {
                            // Если ячейка не содержит числового значения или пустая, записываем NaN
                            data[i][j - 1] = Double.NaN;
                        }
                    } else {
                        // Если строка отсутствует, записываем NaN
                        data[i][j - 1] = Double.NaN;
                    }
                }
            }

            // Проверка на одинаковое количество элементов в каждом столбце
            int expectedRowCount = rows - 1;
            for (int i = 0; i < cols; i++) {
                int actualRowCount = 0;
                for (int j = 0; j < rows - 1; j++) {
                    if (!Double.isNaN(data[i][j])) {
                        actualRowCount++;
                    }
                }
                if (actualRowCount != expectedRowCount) {
                    JOptionPane.showMessageDialog(null, "В столбце " + (i + 1) + " разное количество элементов", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return null;
                }
            }

            // Вывод данных
            for (int i = 0; i < cols; i++) {
                System.out.print("Столбец " + (i + 1) + ": ");
                for (int j = 0; j < rows - 1; j++) {
                    System.out.print(data[i][j] + " ");
                }
                System.out.println();
            }

            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}