package org.example;

import org.apache.commons.math3.stat.StatUtils;
import java.util.HashMap;

public class GeometricMean implements Calculator{


    public HashMap<String, Double> calculate (double [][] numbers){
        HashMap<String, Double> resulMap = new HashMap<>();
        for (int i = 0; i< numbers.length; i++){
            double[] elementsArray = numbers[i];
            double geometricMean = StatUtils.mean(elementsArray);
            resulMap.put("Ср геометрическое для выборки " + (i+1),  Math.pow(geometricMean, numbers.length));
        }
        return resulMap;
    }
}