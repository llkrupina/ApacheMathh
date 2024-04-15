package org.example;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class VariationCoefficient {
    public double calculateVariationCoefficient(double[] numbers) {
        DescriptiveStatistics stats = new DescriptiveStatistics(numbers);
        double mean = stats.getMean();
        double stdDev = stats.getStandardDeviation();
        return (stdDev / mean) * 100;
    }
}