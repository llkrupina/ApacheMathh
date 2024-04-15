package org.example;

import org.apache.commons.math3.stat.StatUtils;

public class GeometricMean {
    public double calculateGeometricMean(double[] numbers) {
        return Math.pow(StatUtils.geometricMean(numbers), numbers.length);
    }
}