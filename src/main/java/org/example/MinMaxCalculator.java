package org.example;

import org.apache.commons.math3.stat.StatUtils;

public class MinMaxCalculator {
    public double[] calculateMinMax(double[] data) {
        double[] result = new double[2];
        result[0] = StatUtils.max(data);
        result[1] = StatUtils.min(data);
        return result;
    }
}