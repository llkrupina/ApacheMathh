package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.Arrays;

public class Manager {
    private double[][] data;
    private boolean arithmeticMeanCalculated;
    private boolean geometricMeanCalculated;
    private boolean standardDeviationCalculated;
    private boolean rangeCalculated;
    private boolean covarianceCalculated;
    private boolean quantityCalculated;
    private boolean variationCoefficientCalculated;
    private boolean dispersionCalculated;
    private boolean minMaxCalculated;
    private boolean confidenceIntervalCalculated;

    public Manager() {
        arithmeticMeanCalculated = false;
        geometricMeanCalculated = false;
        standardDeviationCalculated = false;
        rangeCalculated = false;
        covarianceCalculated = false;
        quantityCalculated = false;
        variationCoefficientCalculated = false;
        dispersionCalculated = false;
        minMaxCalculated = false;
    }


    public void submit(DefaultTableModel resultTableModel,String filePath, String sheetName){
        GetDataFromExcel dataGetter = new GetDataFromExcel();
        // Вызываем метод getDataFromSheet
        data = dataGetter.getDataFromSheet(filePath, sheetName);

        // Выводим данные в консоль
        if (data != null) {
            System.out.println("Данные из таблицы:");
            for (int i = 0; i < data.length; i++) {
                System.out.println(Arrays.toString(data[i]));
            }
        } else {
            //JOptionPane.showMessageDialog(null, "Не удалось загрузить данные из таблицы.", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        clearResultTable(resultTableModel);
        arithmeticMeanCalculated = false;
        geometricMeanCalculated = false;
        standardDeviationCalculated = false;
        rangeCalculated = false;
        covarianceCalculated = false;
        quantityCalculated = false;
        variationCoefficientCalculated = false;
        dispersionCalculated = false;
        minMaxCalculated = false;
    }

    public void calculateArithmeticMean(DefaultTableModel resultTableModel) {
        if (!arithmeticMeanCalculated) {
            arithmeticMeanCalculated = true;

            if (data != null) {
                ArithmeticMean arithmeticMeanCalculator = new ArithmeticMean();

                for (int i = 0; i < data.length; i++) {
                    double arithmeticMean = arithmeticMeanCalculator.calculateArithmeticMean(data[i]);
                    setResultTableData(resultTableModel, "Среднее арифметическое для выборки " + (i + 1), arithmeticMean);
                }

            } else {
               // System.out.println("Данные из таблицы не загружены.");
                JOptionPane.showMessageDialog(null, "Данные из таблицы не загружены.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void calculateGeometricMean( DefaultTableModel resultTableModel) {
        if (!geometricMeanCalculated) {
            geometricMeanCalculated = true;

            if (data != null) {
                GeometricMean geometricMeanCalculator = new GeometricMean();

                // Перебираем каждую выборку из двумерного массива data
                for (int i = 0; i < data.length; i++) {
                    // Вычисляем среднее геометрическое для текущей выборки
                    double geometricMean = geometricMeanCalculator.calculateGeometricMean(data[i]);
                    // Добавляем результат в таблицу
                    setResultTableData(resultTableModel, "Среднее геометрическое для выборки " + (i + 1), geometricMean);
                }

            } else {
               // System.out.println("Данные из таблицы не загружены.");
                JOptionPane.showMessageDialog(null, "Данные из таблицы не загружены.", "Ошибка", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    public void calculateStandardDeviation( DefaultTableModel resultTableModel) {
        if (!standardDeviationCalculated) {
            standardDeviationCalculated = true;

            if (data != null) {
                Deviation deviationCalculator = new Deviation();

                // Перебираем каждую выборку из двумерного массива data
                for (int i = 0; i < data.length; i++) {
                    // Вычисляем оценку стандартного отклонения для текущей выборки
                    double standardDeviation = deviationCalculator.calculateStandardDeviation(data[i]);
                    // Добавляем результат в таблицу
                    setResultTableData(resultTableModel,"Оценка стандартного отклонения для выборки " + (i + 1), standardDeviation);
                }
            } else {
                // System.out.println("Данные из таблицы не загружены.");
                JOptionPane.showMessageDialog(null, "Данные из таблицы не загружены.", "Ошибка", JOptionPane.ERROR_MESSAGE);

            }
        }
    }
    public void calculateRange( DefaultTableModel resultTableModel) {
        if (!rangeCalculated) {
            rangeCalculated = true;

            if (data != null) {
                Scope scopeCalculator = new Scope();

                // Перебираем каждую выборку из двумерного массива data
                for (int i = 0; i < data.length; i++) {
                    // Вычисляем размах для текущей выборки
                    double range = scopeCalculator.calculateRange(data[i]);
                    // Добавляем результат в таблицу
                    setResultTableData(resultTableModel, "Размах для выборки " + (i + 1), range);
                }
            } else {
                // System.out.println("Данные из таблицы не загружены.");
                JOptionPane.showMessageDialog(null, "Данные из таблицы не загружены.", "Ошибка", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    public void calculateCovariance( DefaultTableModel resultTableModel) {
        if (!covarianceCalculated) {
            covarianceCalculated = true;

            if (data != null) {
                Covariance covarianceCalculator = new Covariance();
                // Вычисляем коэффициент ковариации
                double covariance = covarianceCalculator.calculateCovariance(data);

                // Добавляем результат в таблицу
                setResultTableData(resultTableModel,"Коэффициент ковариации", covariance);
            } else {
                // System.out.println("Данные из таблицы не загружены.");
                JOptionPane.showMessageDialog(null, "Данные из таблицы не загружены.", "Ошибка", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    public void calculateQuantity(DefaultTableModel resultTableModel) {
        if (!quantityCalculated) {
            quantityCalculated = true;

            if (data != null) {
                Quantity quantityCalculator = new Quantity();

                // Перебираем каждую выборку из двумерного массива data
                for (int i = 0; i < data.length; i++) {
                    // Вычисляем количество элементов в текущей выборке
                    int quantity = quantityCalculator.calculateQuantity(data[i]);
                    // Добавляем результат в таблицу
                    setResultTableData(resultTableModel,"Количество элементов в выборке " + (i + 1), quantity);
                }
            } else {
                // System.out.println("Данные из таблицы не загружены.");
                JOptionPane.showMessageDialog(null, "Данные из таблицы не загружены.", "Ошибка", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    public void  calculateVariationCoefficient( DefaultTableModel resultTableModel) {
        if (!variationCoefficientCalculated) {
            variationCoefficientCalculated = true;

            if (data != null) {
                // Создаем экземпляр класса VariationCoefficient
                VariationCoefficient variationCoefficientCalculator = new VariationCoefficient();

                // Перебираем каждую выборку из двумерного массива data
                for (int i = 0; i < data.length; i++) {
                    // Вычисляем коэффициент вариации для текущей выборки
                    double variationCoefficient = variationCoefficientCalculator.calculateVariationCoefficient(data[i]);
                    // Добавляем результат в таблицу
                    setResultTableData(resultTableModel,"Коэффициент вариации для выборки " + (i + 1), variationCoefficient);
                }
            } else {
                // System.out.println("Данные из таблицы не загружены.");
                JOptionPane.showMessageDialog(null, "Данные из таблицы не загружены.", "Ошибка", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    public void calculateConfidenceInterval( DefaultTableModel resultTableModel) {
        if (!confidenceIntervalCalculated) {
            confidenceIntervalCalculated = true;

            if (data != null) {
                СonfidenceInterval confidenceIntervalCalculator = new СonfidenceInterval();

                // Перебираем каждую выборку из двумерного массива data
                for (int i = 0; i < data.length; i++) {
                    // Вычисляем доверительный интервал для текущей выборки
                    double[] interval = confidenceIntervalCalculator.calculateInterval(data[i], 0.95);
                    // Преобразуем результаты в строки
                    String lowerBoundStr = String.valueOf(interval[0]);
                    String upperBoundStr = String.valueOf(interval[1]);
                    // Добавляем результат в таблицу
                    setResultTableData(resultTableModel,"Доверительный интервал для выборки " + (i + 1), "[" + lowerBoundStr + ", " + upperBoundStr + "]");
                }

            } else {
                // System.out.println("Данные из таблицы не загружены.");
                JOptionPane.showMessageDialog(null, "Данные из таблицы не загружены.", "Ошибка", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    public void calculateDispersion( DefaultTableModel resultTableModel) {
        if (!dispersionCalculated) {
            dispersionCalculated = true;

            if (data != null) {
                // Создаем экземпляр класса Dispersion
                Dispersion dispersionCalculator = new Dispersion();

                // Перебираем каждую выборку из двумерного массива data
                for (int i = 0; i < data.length; i++) {
                    // Вычисляем оценку дисперсии для текущей выборки
                    double dispersion = dispersionCalculator.calculateDispersion(data[i]);
                    // Добавляем результат в таблицу
                    setResultTableData(resultTableModel,"Оценка дисперсии для выборки " + (i + 1), dispersion);
                }
            } else {
                // System.out.println("Данные из таблицы не загружены.");
                JOptionPane.showMessageDialog(null, "Данные из таблицы не загружены.", "Ошибка", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    public void calculateMinMax(DefaultTableModel resultTableModel) {
        if (!minMaxCalculated) {
            minMaxCalculated = true;

            if (data != null) {
                // Создаем экземпляр класса MinMaxCalculator
                MinMaxCalculator minMaxCalculator = new MinMaxCalculator();

                // Перебираем каждую выборку из двумерного массива data
                for (int i = 0; i < data.length; i++) {
                    // Вычисляем максимум и минимум для текущей выборки
                    double[] minMax = minMaxCalculator.calculateMinMax(data[i]);
                    // Добавляем результаты в таблицу
                    setResultTableData(resultTableModel,"Максимум для выборки " + (i + 1), minMax[0]);
                    setResultTableData(resultTableModel,"Минимум для выборки " + (i + 1), minMax[1]);
                }
            } else {
                // System.out.println("Данные из таблицы не загружены.");
                JOptionPane.showMessageDialog(null, "Данные из таблицы не загружены.", "Ошибка", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    public void saveToFile(DefaultTableModel resultTableModel){
        JFileChooser fileChooser = new JFileChooser();
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
        // Добавляем значение в таблицу
        resultTableModel.addRow(new Object[]{label, value});
    }
    private void setResultTableData(DefaultTableModel resultTableModel, String label, String value) {
        // Устанавливаем заголовок, если таблица пустая
        if (resultTableModel.getColumnCount() == 0) {
            resultTableModel.addColumn("Описание");
            resultTableModel.addColumn("Значение");
        }
        // Добавляем значение в таблицу
        resultTableModel.addRow(new Object[]{label, value});
    }

    private void clearResultTable(DefaultTableModel resultTableModel) {
        // Очищаем таблицу результатов
        resultTableModel.setRowCount(0);
        resultTableModel.setColumnCount(0);
    }

}