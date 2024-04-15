package org.example;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class СonfidenceInterval {

    public double[] calculateInterval(double[] data, double confidenceLevel) {
        DescriptiveStatistics stats = new DescriptiveStatistics(data);
        double mean = stats.getMean();
        double stdDev = stats.getStandardDeviation();
        int n = data.length;
        TDistribution tDist = new TDistribution(n - 1);
        double criticalValue = tDist.inverseCumulativeProbability(1 - (1 - confidenceLevel) / 2);
        double marginOfError = criticalValue * stdDev / Math.sqrt(n);
        double lowerBound = mean - marginOfError;
        double upperBound = mean + marginOfError;
        // Округляем значения до 5 знаков после запятой
        lowerBound = Math.round(lowerBound * 100000.0) / 100000.0;
        upperBound = Math.round(upperBound * 100000.0) / 100000.0;
        return new double[]{lowerBound, upperBound};
    }
}