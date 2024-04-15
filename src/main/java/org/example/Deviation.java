package org.example;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Deviation {
    public double calculateStandardDeviation(double[] numbers) {
        DescriptiveStatistics stats = new DescriptiveStatistics(numbers);
        return stats.getStandardDeviation();
    }
}

