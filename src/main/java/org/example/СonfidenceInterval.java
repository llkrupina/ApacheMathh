package org.example;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import java.util.HashMap;

public class СonfidenceInterval  implements Calculator {
    public HashMap<String, Double> calculate(double [][] numbers){
        HashMap<String, Double> resulMap = new HashMap<>();
        for (int i = 0; i< numbers.length; i++){
            double[] elementsArray = numbers[i];

            DescriptiveStatistics stats = new DescriptiveStatistics(elementsArray);
            double mean = stats.getMean();
            double stdDev = stats.getStandardDeviation();
            int n = elementsArray.length;
            TDistribution tDist = new TDistribution(n - 1);
            double criticalValue = tDist.inverseCumulativeProbability(1 - (1 - 0.95) / 2);
            double marginOfError = criticalValue * stdDev / Math.sqrt(n);
            double lowerBound = mean - marginOfError;
            double upperBound = mean + marginOfError;
            // Округляем значения до 5 знаков после запятой
            lowerBound = Math.round(lowerBound * 100000.0) / 100000.0;
            upperBound = Math.round(upperBound * 100000.0) / 100000.0;

            resulMap.put("Начало интервала для выборки " + (i+1), lowerBound);
            resulMap.put("Конец интервала для выборки " + (i+1), upperBound);

        }
        return resulMap;
    }
}