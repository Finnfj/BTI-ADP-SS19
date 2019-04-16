package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import pascalTriangleCalculator.PascalTriangleCalculator;
import pascalTriangleCalculator.PascalTriangleCalculatorBinoCoeff;

public class JUnitTestFrameFunctionalityBinoCoeff {
    final private int timelimit = 1_000; // ms

    @Test(timeout = timelimit)
    public void rowMinusOne() {
        PascalTriangleCalculator ptc = new PascalTriangleCalculatorBinoCoeff();
        assertNull(ptc.calculateRow(-1));
    }

    @Test(timeout = timelimit)
    public void rowZero() {
        PascalTriangleCalculator ptc = new PascalTriangleCalculatorBinoCoeff();
        assertNull(ptc.calculateRow(0));
    }

    @Test(timeout = timelimit)
    public void rowOne() {
        PascalTriangleCalculator ptc = new PascalTriangleCalculatorBinoCoeff();
        int[] expectedRow = {1};
        int[] actualRow = ptc.calculateRow(1);

        assertEquals(expectedRow.length, actualRow.length);
        for (int i = 0; i < actualRow.length; i++) {
            assertEquals(expectedRow[i], actualRow[i]);
        }
    }

    @Test(timeout = timelimit)
    public void rowTwo() {
        PascalTriangleCalculator ptc = new PascalTriangleCalculatorBinoCoeff();
        int[] expectedRow = {1, 1};
        int[] actualRow = ptc.calculateRow(2);

        assertEquals(expectedRow.length, actualRow.length);
        for (int i = 0; i < actualRow.length; i++) {
            assertEquals(expectedRow[i], actualRow[i]);
        }
    }

    @Test(timeout = timelimit)
    public void rowThree() {
        PascalTriangleCalculator ptc = new PascalTriangleCalculatorBinoCoeff();
        int[] expectedRow = {1, 2, 1};
        int[] actualRow = ptc.calculateRow(3);
        
        assertEquals(expectedRow.length, actualRow.length);
        for (int i = 0; i < actualRow.length; i++) {
            assertEquals(expectedRow[i], actualRow[i]);
        }
    }

    @Test(timeout = timelimit)
    public void rowSixteen() {
        PascalTriangleCalculator ptc = new PascalTriangleCalculatorBinoCoeff();
        int[] expectedRow = {1, 15, 105, 455, 1365, 3003, 5005, 6435, 6435, 5005, 3003, 1365, 455, 105, 15, 1};
        int[] actualRow = ptc.calculateRow(16);
        
        assertEquals(expectedRow.length, actualRow.length);
        for (int i = 0; i < actualRow.length; i++) {
            assertEquals(expectedRow[i], actualRow[i]);
        }
    }

    @Test(timeout = timelimit)
    public void resetGetCounter() {
        PascalTriangleCalculator ptc = new PascalTriangleCalculatorBinoCoeff();
        ptc.resetCounter();
        assertEquals(0, ptc.getCounter());
    }
}
