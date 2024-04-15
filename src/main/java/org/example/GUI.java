package org.example;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;

public class GUI {
    private String filePath;
    private String sheetName;
    private double[][] data;
    private DefaultTableModel resultTableModel;
    private boolean arithmeticMeanCalculated = false;
    private boolean geometricMeanCalculated = false;
    private boolean standardDeviationCalculated = false;
    private boolean rangeCalculated = false;
    private boolean covarianceCalculated = false;
    private boolean quantityCalculated = false;
    private boolean variationCoefficientCalculated = false;
    private boolean dispersionCalculated = false;
    private boolean minMaxCalculated = false;
    private boolean confidenceIntervalCalculated = false;



    public GUI() {
        JFrame frame = new JFrame("Выбор файла Excel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension( 1100, 450));

        JButton selectFileButton = new JButton("Выбрать файл Excel");
        JTextField sheetNameField = new JTextField(20);
        JButton submitButton = new JButton("Отправить");
        JButton calculateArithmeticMeanButton = new JButton("Рассчитать среднее арифметическое");
        JButton calculateGeometricMeanButton = new JButton("Рассчитать среднее геометрическое");
        JButton calculateStandardDeviationButton = new JButton("Рассчитать оценку стандартного отклонения");
        JButton calculateRangeButton = new JButton("Рассчитать размах");
        JButton calculateCovarianceButton = new JButton("Рассчитать коэффициенты ковариации");
        JButton calculateQuantityButton = new JButton("Рассчитать количество элементов");
        JButton calculateVariationCoefficientButton = new JButton("Рассчитать коэффициент вариации");
        JButton calculateConfidenceIntervalButton = new JButton("Рассчитать доверительный интервал");
        JButton calculateDispersionButton = new JButton("Рассчитать оценку дисперсии");
        JButton calculateMinMaxButton = new JButton("Рассчитать максимум и минимум");

        JButton saveToFileButton = new JButton("Сохранить в файл");

        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xls", "xlsx");
                fileChooser.setFileFilter(filter);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    filePath = selectedFile.getAbsolutePath();

                }
            }
        });


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearResultTable();
                sheetName = sheetNameField.getText();
                System.out.println("Выбранный путь к файлу: " + filePath);
                System.out.println("Выбранное имя листа: " + sheetName);

                // Создаем экземпляр класса GetDataFromExcel
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
                    JOptionPane.showMessageDialog(null, "Не удалось загрузить данные из таблицы.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
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
        });

        calculateArithmeticMeanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!arithmeticMeanCalculated) {
                    arithmeticMeanCalculated = true;

                    if (data != null) {
                        // Создаем экземпляр класса ArithmeticMean
                        ArithmeticMean arithmeticMeanCalculator = new ArithmeticMean();


                        // Перебираем каждую выборку из двумерного массива data
                        for (int i = 0; i < data.length; i++) {
                            // Вычисляем среднее арифметическое для текущей выборки
                            double arithmeticMean = arithmeticMeanCalculator.calculateArithmeticMean(data[i]);
                            // Добавляем результат в таблицу
                            setResultTableData("Среднее арифметическое для выборки " + (i + 1), arithmeticMean);
                        }

                        Object[] line = {"--------", ""};
                        resultTableModel.addRow(line);
                    } else {
                        System.out.println("Данные из таблицы не загружены.");
                    }
                }
            }
        });

        calculateGeometricMeanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!geometricMeanCalculated) {
                    geometricMeanCalculated = true;

                    if (data != null) {
                        GeometricMean geometricMeanCalculator = new GeometricMean();

                        // Перебираем каждую выборку из двумерного массива data
                        for (int i = 0; i < data.length; i++) {
                            // Вычисляем среднее геометрическое для текущей выборки
                            double geometricMean = geometricMeanCalculator.calculateGeometricMean(data[i]);
                            // Добавляем результат в таблицу
                            setResultTableData("Среднее геометрическое для выборки " + (i + 1), geometricMean);
                        }

                    } else {
                        System.out.println("Данные из таблицы не загружены.");
                    }
                }
            }
        });

        calculateStandardDeviationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!standardDeviationCalculated) {
                    standardDeviationCalculated = true;

                    if (data != null) {
                        Deviation deviationCalculator = new Deviation();

                        // Перебираем каждую выборку из двумерного массива data
                        for (int i = 0; i < data.length; i++) {
                            // Вычисляем оценку стандартного отклонения для текущей выборки
                            double standardDeviation = deviationCalculator.calculateStandardDeviation(data[i]);
                            // Добавляем результат в таблицу
                            setResultTableData("Оценка стандартного отклонения для выборки " + (i + 1), standardDeviation);
                        }
                    } else {
                        System.out.println("Данные из таблицы не загружены.");
                    }
                }
            }
        });

        calculateRangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!rangeCalculated) {
                    rangeCalculated = true;

                    if (data != null) {
                        Scope scopeCalculator = new Scope();

                        // Перебираем каждую выборку из двумерного массива data
                        for (int i = 0; i < data.length; i++) {
                            // Вычисляем размах для текущей выборки
                            double range = scopeCalculator.calculateRange(data[i]);
                            // Добавляем результат в таблицу
                            setResultTableData("Размах для выборки " + (i + 1), range);
                        }
                    } else {
                        System.out.println("Данные из таблицы не загружены.");
                    }
                }
            }
        });
        calculateCovarianceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!covarianceCalculated) {
                    covarianceCalculated = true;

                    if (data != null) {
                    Covariance covarianceCalculator = new Covariance();
                    // Вычисляем коэффициент ковариации
                    double covariance = covarianceCalculator.calculateCovariance(data);

                    // Добавляем результат в таблицу
                    setResultTableData("Коэффициент ковариации", covariance);
                } else {
                    System.out.println("Данные из таблицы не загружены.");
                }
            }
            }
        });

        calculateQuantityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!quantityCalculated) {
                    quantityCalculated = true;

                    if (data != null) {
                    Quantity quantityCalculator = new Quantity();

                    // Перебираем каждую выборку из двумерного массива data
                    for (int i = 0; i < data.length; i++) {
                        // Вычисляем количество элементов в текущей выборке
                        int quantity = quantityCalculator.calculateQuantity(data[i]);
                        // Добавляем результат в таблицу
                        setResultTableData("Количество элементов в выборке " + (i + 1), quantity);
                    }
                } else {
                    System.out.println("Данные из таблицы не загружены.");
                }
            }
            }
        });
        calculateVariationCoefficientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                        setResultTableData("Коэффициент вариации для выборки " + (i + 1), variationCoefficient);
                    }
                } else {
                    System.out.println("Данные из таблицы не загружены.");
                }
            }
            }
        });

        calculateConfidenceIntervalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                            setResultTableData("Доверительный интервал для выборки " + (i + 1), "[" + lowerBoundStr + ", " + upperBoundStr + "]");
                        }

                    } else {
                        System.out.println("Данные из таблицы не загружены.");
                    }
                }
            }
        });
        calculateDispersionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                        setResultTableData("Оценка дисперсии для выборки " + (i + 1), dispersion);
                    }
                } else {
                    System.out.println("Данные из таблицы не загружены.");
                }
            }
            }
        });

        calculateMinMaxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                            setResultTableData("Максимум для выборки " + (i + 1), minMax[0]);
                            setResultTableData("Минимум для выборки " + (i + 1), minMax[1]);
                        }
                    } else {
                        System.out.println("Данные из таблицы не загружены.");
                    }
                }
            }
        });

        saveToFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    SaveDataInExcel.saveToExcel(resultTableModel, selectedFile);
                }
            }
        });


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(selectFileButton);
        panel.add(new JLabel("Sheet Name:"));
        panel.add(sheetNameField);
        panel.add(submitButton);
        panel.add(calculateArithmeticMeanButton);
        panel.add(calculateGeometricMeanButton);
        panel.add(calculateStandardDeviationButton);
        panel.add(calculateRangeButton);
        panel.add(calculateCovarianceButton);
        panel.add(calculateQuantityButton);
        panel.add(calculateVariationCoefficientButton);
        panel.add(calculateConfidenceIntervalButton);
        panel.add(calculateDispersionButton);
        panel.add(calculateMinMaxButton);
        panel.add(saveToFileButton);


        // Создаем модель для таблицы результатов
        resultTableModel = new DefaultTableModel();
        JTable resultTable = new JTable(resultTableModel);
        JScrollPane resultScrollPane = new JScrollPane(resultTable);

        // Добавляем таблицу в отдельную панель
        JPanel tablePanel = new JPanel();
        tablePanel.add(resultScrollPane);

        // Добавляем панель с кнопками и панель с таблицей на основную панель
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel, tablePanel);
        splitPane.setDividerLocation(200); // Задаем начальное расположение делителя

        // Добавляем основную панель на фрейм
        frame.add(splitPane);
        frame.pack();
        frame.setVisible(true);
    }

    private void clearResultTable() {
        // Очищаем таблицу результатов
        resultTableModel.setRowCount(0);
        resultTableModel.setColumnCount(0);
    }

    private void setResultTableData(String label, double value) {
        // Устанавливаем заголовок, если таблица пустая
        if (resultTableModel.getColumnCount() == 0) {
            resultTableModel.addColumn("Описание");
            resultTableModel.addColumn("Значение");
        }
        // Добавляем значение в таблицу
        resultTableModel.addRow(new Object[]{label, value});
    }

    private void setResultTableData(String label, String value) {
        // Устанавливаем заголовок, если таблица пустая
        if (resultTableModel.getColumnCount() == 0) {
            resultTableModel.addColumn("Описание");
            resultTableModel.addColumn("Значение");
        }
        // Добавляем значение в таблицу
        resultTableModel.addRow(new Object[]{label, value});
    }

}