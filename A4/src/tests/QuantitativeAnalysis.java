package tests;

import pascalTriangleCalculator.*;

public class QuantitativeAnalysis {
    public static void  main(String... args) {
        PascalTriangleCalculator[] ptcArray = {new PascalTriangleCalculatorIterative(), new PascalTriangleCalculatorRecursive(), new PascalTriangleCalculatorBinoCoeff()};

        for (int i = 1; i <= 100000; i *= 10) {
            System.out.println("Rows: " + i);
            for (PascalTriangleCalculator ptc : ptcArray) {
                ptc.calculateRow(i);
                System.out.println(ptc.getClass().getSimpleName() + ": " + ptc.getCounter());
                ptc.resetCounter();
            }
            System.out.println("---------------------------------------------------------");
        }
    }
}
