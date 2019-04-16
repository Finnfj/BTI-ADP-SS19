package pascalTriangleCalculator;

/**<br>
 * Kurs: BTI3-ADP <br>
 * Aufgabe: 4c Pascal Triagnle Calculator with binomical coefficent<br>
 * A Pascal Triangle Calculator that uses the binomical coefficent formula<br>
 * @author Finn Jannsen
 * @author Philipp Schwarz
 * @version 1.0, 16.04.19
 *
 */
public class PascalTriangleCalculatorBinoCoeff implements PascalTriangleCalculator {
    private long counter;

    /**
     * Constructor for the binocoeff Pascal Triangle Calculator
     */
    public PascalTriangleCalculatorBinoCoeff() {
        counter = 0;
    }

    /**
     * Calculates a specific row of a Pascal Triangle
     * @param rowNum The row you want to be calculated. The first one is 1.
     * @return Int Array with the numbers of the row or null if the paramter was not correct
     */
    @Override
    public int[] calculateRow(int rowNum) {
        if (rowNum < 1) {
            return null;
        }

        int[] row = new int[rowNum];

        for (int i = 0; i < row.length; i++) {
            counter++;
            // formula: n! / (r! * (n-r)!)
            long top = getFactorial(rowNum-1);
            long bottom = (getFactorial(i) * getFactorial(rowNum-1-i));
            row[i] = (int) (top / bottom);
        }
        return row;
    }

    /**
     * A method that calculates the factorial of a number
     * @param num The number the factorial gets calculated of
     * @return The factorial as long
     */
    private long getFactorial(int num) {
        long fact = 1;

        for (int i = 1; i <= num; i++) {
            fact *= i;
        }

        return fact;
    }

    @Override
    public long getCounter() {
        return counter;
    }

    @Override
    public void resetCounter() {
        counter = 0;
    }
}
