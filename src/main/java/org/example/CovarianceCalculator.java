package org.example;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.linear.MatrixUtils;
import java.util.HashMap;

public class CovarianceCalculator implements Calculator{

    public HashMap<String, Double> calculate(double[][] numbers){
        double[][] transposedNumbers = transpose(numbers);
        // Инициализация объекта Covariance
        Covariance covariance = new Covariance(transposedNumbers);

        // Расчет ковариаций
        double[][] covariances = covariance.getCovarianceMatrix().getData();

        // Создание HashMap для хранения результатов
        HashMap<String, Double> covarianceMap = new HashMap<>();

        // Заполнение HashMap результатами
        for (int i = 0; i < covariances.length; i++) {
            for (int j = i; j < covariances[i].length; j++) { // Начинаем с i, чтобы исключить повторение пар
                if (i != j) {
                    String key = "Выборки " + (i+1) + "-" + (j+1);
                    double value = covariances[i][j];
                    covarianceMap.put(key, value);
                }
            }
        }
        return covarianceMap;
    }

    private double[][] transpose(double[][] matrix) {
        return MatrixUtils.createRealMatrix(matrix).transpose().getData();
    }

}
