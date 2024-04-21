package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

public class Manager {
    private double[][] data;
    private HashMap<String, Calculator> calculatorMap;
    private HashMap<String, Boolean> buttonflags;

    public Manager() {
        buttonflags = new HashMap<>();
        buttonflags.put("ArithmeticMean", false);
        buttonflags.put("GeometricMean", false);
        buttonflags.put("StandardDeviation", false);
        buttonflags.put("Scope", false);
        buttonflags.put("Quantity", false);
        buttonflags.put("VariationCoefficient", false);
        buttonflags.put("СonfidenceInterval", false);
        buttonflags.put("Dispersion", false);
        buttonflags.put("MinMax", false);
        buttonflags.put("CovarianceCalculator", false);

        calculatorMap = new HashMap<>();
        calculatorMap.put("ArithmeticMean", new ArithmeticMean());
        calculatorMap.put("GeometricMean", new GeometricMean());
        calculatorMap.put("StandardDeviation", new Deviation());
        calculatorMap.put("Scope", new Scope());
        calculatorMap.put("CovarianceCalculator", new CovarianceCalculator());
        calculatorMap.put("Quantity", new Quantity());
        calculatorMap.put("VariationCoefficient", new VariationCoefficient());
        calculatorMap.put("СonfidenceInterval", new СonfidenceInterval());
        calculatorMap.put("Dispersion", new Dispersion());
        calculatorMap.put("MinMax", new MinMaxCalculator());

    }

    public void submit(DefaultTableModel resultTableModel,String filePath, String sheetName){
        GetDataFromExcel dataGetter = new GetDataFromExcel();

        data = dataGetter.getDataFromSheet(filePath, sheetName);

        // Выводим данные в консоль
        if (data != null) {
            System.out.println("Данные из таблицы:");
            for (int i = 0; i < data.length; i++) {
                System.out.println(Arrays.toString(data[i]));
            }
        } else {
            JOptionPane.showMessageDialog(null, "Не удалось загрузить данные из таблицы.", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        clearResultTable(resultTableModel);

        for (String key : buttonflags.keySet()){
            buttonflags.put(key, false);
        }
    }

    public void calculate(String ID, DefaultTableModel resultTableModel){
        for (String key : calculatorMap.keySet()) {
            if (key.equals(ID)) {
                if (!buttonflags.get(key)) {
                    if (data != null) {
                        HashMap<String, Double> resultMap = calculatorMap.get(key).calculate(data);
                            if (key.equals("СonfidenceInterval")){
                                for (int i = 0; i < resultMap.size()/2; i++) {
                                    Double start = resultMap.get("Начало интервала для выборки " + (i + 1));
                                    Double end = resultMap.get("Конец интервала для выборки " + (i + 1));

                                    setResultTableData(resultTableModel, "Дов. интервал для выборки " + (i + 1), "[" + start + "; " + end + "]");
                                }
                            } else {
                                for (String resultKey : resultMap.keySet()) {
                                setResultTableData(resultTableModel, resultKey, resultMap.get(resultKey));
                            }
                        }
                        buttonflags.put(key, true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Данные из таблицы не загружены.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    public void saveToFile(DefaultTableModel resultTableModel){
        JFileChooser fileChooser = new JFileChooser();

        // Установка текущей директории
        String currentDirectory = System.getProperty("user.dir");
        fileChooser.setCurrentDirectory(new File(currentDirectory));

        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            SaveDataInExcel.saveToExcel(resultTableModel, selectedFile);
        }
    }

    private void setResultTableData(DefaultTableModel resultTableModel, String label, double value) {
        // Устанавливаем заголовок, если таблица пустая
        if (resultTableModel.getColumnCount() == 0) {
            resultTableModel.addColumn("Описание");
            resultTableModel.addColumn("Значение");
        }
        resultTableModel.addRow(new Object[]{label, value});
    }

    private void setResultTableData(DefaultTableModel resultTableModel, String label, String value) {
        // Устанавливаем заголовок, если таблица пустая
        if (resultTableModel.getColumnCount() == 0) {
            resultTableModel.addColumn("Описание");
            resultTableModel.addColumn("Значение");
        }
        resultTableModel.addRow(new Object[]{label, value});
    }

    private void clearResultTable(DefaultTableModel resultTableModel) {
        resultTableModel.setRowCount(0);
        resultTableModel.setColumnCount(0);
    }
}