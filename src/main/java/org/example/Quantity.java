package org.example;

import java.util.HashMap;

public class Quantity implements Calculator{
    public HashMap<String, Double> calculate(double [][] numbers){
        HashMap<String, Double> resulMap = new HashMap<>();
        for (int i = 0; i< numbers.length; i++){
            double[] elementsArray = numbers[i];
            resulMap.put("Кол-во элементов для выборки " + (i+1), (double)elementsArray.length);
        }
        return resulMap;
    }
}
