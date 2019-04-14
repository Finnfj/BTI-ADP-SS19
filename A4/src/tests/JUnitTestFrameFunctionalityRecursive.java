package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import pascalTriangleCalculator.PascalTriangleCalculator;
import pascalTriangleCalculator.PascalTriangleCalculatorRecursive;

public class JUnitTestFrameFunctionalityRecursive {
    final private int timelimit = 1_000; // ms

    @Test(timeout = timelimit)
    public void rowMinusOne() {
        PascalTriangleCalculator ptc = new PascalTriangleCalculatorRecursive();
        assertEquals(null, ptc.calculateRow(-1));
    }

    @Test(timeout = timelimit)
    public void rowZero() {
        PascalTriangleCalculator ptc = new PascalTriangleCalculatorRecursive();
        assertEquals(null, ptc.calculateRow(0));
    }

    @Test(timeout = timelimit)
    public void rowOne() {
        PascalTriangleCalculator ptc = new PascalTriangleCalculatorRecursive();
        int[] expectedRow = {1};
        int[] actualRow = ptc.calculateRow(1);

        assertEquals(expectedRow.length, actualRow.length);
        for (int i = 0; i < actualRow.length; i++) {
            assertEquals(expectedRow[i], actualRow[i]);
        }
    }

    @Test(timeout = timelimit)
    public void rowTwo() {
        PascalTriangleCalculator ptc = new PascalTriangleCalculatorRecursive();
        int[] expectedRow = {1, 1};
        int[] actualRow = ptc.calculateRow(2);

        assertEquals(expectedRow.length, actualRow.length);
        for (int i = 0; i < actualRow.length; i++) {
            assertEquals(expectedRow[i], actualRow[i]);
        }
    }

    @Test(timeout = timelimit)
    public void rowThree() {
        PascalTriangleCalculator ptc = new PascalTriangleCalculatorRecursive();
        int[] expectedRow = {1, 2, 1};
        int[] actualRow = ptc.calculateRow(3);

        assertEquals(expectedRow.length, actualRow.length);
        for (int i = 0; i < actualRow.length; i++) {
            assertEquals(expectedRow[i], actualRow[i]);
        }
    }

    @Test(timeout = timelimit)
    public void rowSixteen() {
        PascalTriangleCalculator ptc = new PascalTriangleCalculatorRecursive();
        int[] expectedRow = {1, 15, 105, 455, 1365, 3003, 5005, 6435, 6435, 5005, 3003, 1365, 455, 105, 15, 1};
        int[] actualRow = ptc.calculateRow(16);

        assertEquals(expectedRow.length, actualRow.length);
        for (int i = 0; i < actualRow.length; i++) {
            assertEquals(expectedRow[i], actualRow[i]);
        }
    }
}
