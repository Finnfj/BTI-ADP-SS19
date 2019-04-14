package tests;

import pascalTriangleCalculator.*;

public class QuantitativeAnalysis {
    public static void  main(String... args) {
        PascalTriangleCalculator[] ptc = {new PascalTriangleCalculatorIterative(), new PascalTriangleCalculatorRecursive(), new PascalTriangleCalculatorBinoCoeff()};

        for (int i = 1; i <= 100000; i *= 10) {
            System.out.println("Rows: " + i);
            for (int j = 0; j < ptc.length; j++) {
                ptc[j].calculateRow(i);
                System.out.println(ptc[j].getClass().getSimpleName() + ": " + ptc[j].getCounter());
                ptc[j].resetCounter();
            }
            System.out.println("---------------------------------------------------------");
        }
    }
}
