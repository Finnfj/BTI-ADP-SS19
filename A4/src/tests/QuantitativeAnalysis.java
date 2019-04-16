package tests;

import pascalTriangleCalculator.*;

public class QuantitativeAnalysis {
    public static void  main(String... args) {
        PascalTriangleCalculator[] ptcArray = {new PascalTriangleCalculatorIterative(), new PascalTriangleCalculatorRecursive(), new PascalTriangleCalculatorBinoCoeff()};

        for (int i = 1; i <= 10000; i *= 10) {
            System.out.println("Rows: " + i);
            for (PascalTriangleCalculator ptc : ptcArray) {
                long startTime = System.currentTimeMillis();
                ptc.calculateRow(i);
                long endTime   = System.currentTimeMillis();
                long totalTime = endTime - startTime;
                System.out.println(ptc.getClass().getSimpleName() + ": " + ptc.getCounter() + ", Time: " + totalTime + " ms");
                ptc.resetCounter();
            }
            System.out.println("---------------------------------------------------------");
        }
    }
}
