package org.example;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import java.util.HashMap;

public class Deviation implements Calculator{

    public HashMap<String, Double> calculate (double [][] numbers){
        HashMap<String, Double> resulMap = new HashMap<>();
        for (int i = 0; i< numbers.length; i++){
            double[] elementsArray = numbers[i];

            DescriptiveStatistics stats = new DescriptiveStatistics(elementsArray);

            resulMap.put("Отклонение для выборки " + (i+1), stats.getStandardDeviation());
        }
        return resulMap;
    }
}

