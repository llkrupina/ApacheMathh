package org.example;

import org.apache.commons.math3.stat.StatUtils;

public class ArithmeticMean {
    public double calculateArithmeticMean(double[] numbers) {
        return StatUtils.mean(numbers);
    }
}
