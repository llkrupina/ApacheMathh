package org.example;
import org.apache.commons.math3.stat.StatUtils;
import java.util.HashMap;

public class Scope implements Calculator{
    public HashMap<String, Double> calculate(double [][] numbers){
        HashMap<String, Double> resulMap = new HashMap<>();
        for (int i = 0; i< numbers.length; i++){
            double[] elementsArray = numbers[i];
            double max = StatUtils.max(elementsArray);
            double min = StatUtils.min(elementsArray);
            resulMap.put("Размах для выборки " + (i+1), max - min);
        }
        return resulMap;
    }
}