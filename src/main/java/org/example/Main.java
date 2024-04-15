package org.example;
public class Main {
    public static void main(String[] args) {

       GUI gui = new GUI();

        double[] numbers = {2, 4, 8, 16, 1, 8, 3, 5}; // пример массива чисел
        GeometricMean geometricMeanCalculator = new GeometricMean();
        ArithmeticMean arithmeticMeanCalculator = new ArithmeticMean();
        Deviation deviationCalculator = new Deviation();
        Scope scopeCalculator = new Scope();
        Covariance covarianceCalculator = new Covariance();
        Quantity quantityCalculator = new Quantity();
        Dispersion dispersionCalculator = new Dispersion();
        MinMaxCalculator minMaxCalculator = new MinMaxCalculator();


        GetDataFromExcel excelReader = new GetDataFromExcel();
        // String filePath = gui.getSheetName();
        // tring sheetName = gui.getSheetName();

        // double[][] data = excelReader.getDataFromSheet(gui.getSheetName(), gui.getSheetName());

       /* if (data != null) {
            for (double[] column : data) {
                for (double value : column) {
                    System.out.print(value + " ");
                }
                System.out.println();
            }
        }*/



        double result1 = geometricMeanCalculator.calculateGeometricMean(numbers);
        double result2 = arithmeticMeanCalculator.calculateArithmeticMean(numbers);
        double result3 = deviationCalculator.calculateStandardDeviation(numbers);
        double result4 = scopeCalculator.calculateRange(numbers);
        int result6 = quantityCalculator.calculateQuantity(numbers);
        double result8 = dispersionCalculator.calculateDispersion(numbers);
        double[] result9 = minMaxCalculator.calculateMinMax(numbers);



        double[] sample1 = {1.0, 2.0, 3.0, 4.0, 5.0};
        double[] sample2 = {1.0, 2.0, 3.0, 4.0, 5.0};
        //double[] sample3 = {3.0, 6.0, 9.0, 12.0, 15.0};
        //double[] sample4 = {3.0, 6.0, 9.0, 12.0, 15.0};

        double[][] samples = {sample1, sample2};
        double result5 = covarianceCalculator.calculateCovariance(samples);


        System.out.println("Среднее геометрическое: " + result1);
        System.out.println("Среднее арифметическое: " + result2);
        System.out.println("Оценка стандартного отклонения: " + result3);
        System.out.println("Размах: " + result4);
        System.out.println("Коэффициент ковариации: " + result5);
        System.out.println("Количество элементов: " + result6);
        System.out.println("Доверительный интервал: " );
        System.out.println("Дисперсия: " + result8);
        System.out.println("Минимум: " + result9[0]);
        System.out.println("Максимум: " + result9[1]);


    }

}
