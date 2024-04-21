package org.example;
import org.apache.commons.math3.stat.StatUtils;
import java.util.HashMap;

public class MinMaxCalculator implements Calculator{
    public HashMap<String, Double> calculate(double [][] numbers){
        HashMap<String, Double> resulMap = new HashMap<>();
        for (int i = 0; i< numbers.length; i++){
            double[] elementsArray = numbers[i];

            resulMap.put("Мин. значение для выборки " + (i+1), StatUtils.min(elementsArray));
            resulMap.put("Макс. значение для выборки " + (i+1), StatUtils.max(elementsArray));
        }
        return resulMap;
    }
}