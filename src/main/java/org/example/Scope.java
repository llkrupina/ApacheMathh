package org.example;

import org.apache.commons.math3.stat.StatUtils;

public class Scope {
    public double calculateRange(double[] numbers) {
        double max = StatUtils.max(numbers);
        double min = StatUtils.min(numbers);
        return max - min;
    }
}