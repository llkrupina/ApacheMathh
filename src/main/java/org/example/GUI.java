package org.example;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;

public class GUI {
    private String filePath;
    private DefaultTableModel resultTableModel;
    private Manager manager;
    public GUI() {
        JFrame frame = new JFrame("Выбор файла Excel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension( 1100, 450));

        manager = new Manager();

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

                // Установка текущей директории
                String currentDirectory = System.getProperty("user.dir");
                fileChooser.setCurrentDirectory(new File(currentDirectory));

                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    filePath = selectedFile.getAbsolutePath();

                    // Открыть директорию, содержащую jar файл
                    try {
                        Desktop.getDesktop().open(new File(currentDirectory));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sheetName = sheetNameField.getText();
                System.out.println("Выбранный путь к файлу: " + filePath);
                System.out.println("Выбранное имя листа: " + sheetName);

                manager.submit(resultTableModel, filePath, sheetName);
            }
        });

        calculateArithmeticMeanButton.addActionListener(e -> {
            manager.calculateArithmeticMean(resultTableModel);
        });

        calculateGeometricMeanButton.addActionListener(e -> {
            manager.calculateGeometricMean( resultTableModel);
        });

        calculateStandardDeviationButton.addActionListener(e -> {
            manager.calculateStandardDeviation( resultTableModel);
        });

        calculateRangeButton.addActionListener(e -> {
            manager.calculateRange( resultTableModel);
        });

        calculateCovarianceButton.addActionListener(e -> {
            manager.calculateCovariance( resultTableModel);
        });

        calculateQuantityButton.addActionListener(e -> {
            manager.calculateQuantity( resultTableModel);
        });

        calculateVariationCoefficientButton.addActionListener(e -> {
            manager.calculateVariationCoefficient( resultTableModel);
        });

        calculateConfidenceIntervalButton.addActionListener(e -> {
            manager.calculateConfidenceInterval( resultTableModel);
        });

        calculateDispersionButton.addActionListener(e -> {
            manager.calculateDispersion(resultTableModel);
        });

        calculateMinMaxButton.addActionListener(e -> {
            manager.calculateMinMax( resultTableModel);
        });

        saveToFileButton.addActionListener(e -> {
            manager.saveToFile(resultTableModel);
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
}