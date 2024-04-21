package org.example;

import org.apache.commons.math3.stat.StatUtils;
import java.util.HashMap;

public class ArithmeticMean implements Calculator{

    public HashMap<String, Double> calculate (double [][] numbers){
        HashMap<String, Double> resulMap = new HashMap<>();
        for (int i = 0; i< numbers.length; i++){
            double[] elementsArray = numbers[i];
            double mean = StatUtils.mean(elementsArray);
            resulMap.put("Ср арифметическое для выборки " + (i+1), mean);

        }
        return resulMap;
    }
}
