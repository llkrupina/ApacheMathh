package org.example;


public class Main {
    public static void main(String[] args) {
        double[] numbers = {2, 4, 8, 16, 87, 4, 9}; // пример массива чисел

        double product = 1.0;
        for (double number : numbers) {
            product *= number;
        }

        double geometricMean = Math.pow(product, 1.0 / numbers.length);

        System.out.println("Среднее геометрическое: " + geometricMean);
    }
}