package org.example;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Dispersion {
    public double calculateDispersion(double[] data) {
        DescriptiveStatistics stats = new DescriptiveStatistics(data);
        return stats.getVariance();
    }
}
