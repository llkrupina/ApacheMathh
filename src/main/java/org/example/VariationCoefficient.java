package org.example;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import java.util.HashMap;

public class VariationCoefficient implements Calculator{
    public HashMap<String, Double> calculate(double [][] numbers){
        HashMap<String, Double> resulMap = new HashMap<>();
        for (int i = 0; i< numbers.length; i++){
            double[] elementsArray = numbers[i];
            DescriptiveStatistics stats = new DescriptiveStatistics(elementsArray);
            double mean = stats.getMean();
            double stdDev = stats.getStandardDeviation();

            resulMap.put("Коэф. вариации для выборки " + (i+1), (stdDev / mean) * 100);
        }
        return resulMap;
    }
}