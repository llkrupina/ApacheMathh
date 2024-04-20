package org.example;

public class Covariance {
    public double calculateCovariance(double[][] numbers) {
        int numLength = numbers.length;
        int numSize = numbers[0].length;

        ArithmeticMean arithmeticMeanCalculator = new ArithmeticMean();

        double[] means = new double[numLength];
        for (int i = 0; i < numLength; i++) {
            means[i] = arithmeticMeanCalculator.calculateArithmeticMean(numbers[i]);
        }

        double covariance = 0;
        for (int j = 0; j < numSize; j++) {
            double pair = 1;
            for (int i = 0; i < numLength; i++) {
                pair *= (numbers[i][j] - means[i]);
            }
            covariance += pair;
        }
        covariance /= numSize -1;

        return covariance;
    }

}
