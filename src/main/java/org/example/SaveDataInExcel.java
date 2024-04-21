package org.example;

import org.apache.poi.xssf.usermodel.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileOutputStream;

public class SaveDataInExcel {
    public static void saveToExcel(DefaultTableModel tableModel, File file) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Data");

            // Получаем количество строк и столбцов в таблице
            int rowCount = tableModel.getRowCount();
            int columnCount = tableModel.getColumnCount();

            // Создаем заголовки столбцов
            XSSFRow headerRow = sheet.createRow(0);
            for (int col = 0; col < columnCount; col++) {
                XSSFCell cell = headerRow.createCell(col);
                cell.setCellValue(tableModel.getColumnName(col));
            }

            // Заполняем ячейки данными из таблицы
            for (int row = 0; row < rowCount; row++) {
                XSSFRow excelRow = sheet.createRow(row + 1);
                for (int col = 0; col < columnCount; col++) {
                    XSSFCell cell = excelRow.createCell(col);
                    cell.setCellValue(String.valueOf(tableModel.getValueAt(row, col)));
                }
            }
            File xlsxFile = new File(file.getAbsolutePath() + ".xlsx");


            // Сохраняем workbook в файл
            FileOutputStream outputStream = new FileOutputStream(xlsxFile);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

            System.out.println("Данные сохранены в файл: " + xlsxFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ошибка при сохранении данных в файл.");
        }
    }

}
