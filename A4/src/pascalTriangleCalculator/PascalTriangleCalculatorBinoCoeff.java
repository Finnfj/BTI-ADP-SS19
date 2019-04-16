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
            // formula: n! / (r! * (n-r)!)
            // long top = getFactorial(rowNum-1);
            // long bottom = (getFactorial(i) * getFactorial(rowNum-1-i));

            if (i == 0 || i == rowNum - 1) {
                row[i] = 1;
                counter++;
                // if we are at the second or second to last column, the answer is always the row size - 1
            } else if (i == 1 || i == rowNum - 2) {
                row[i] = rowNum - 1;
                counter++;
            } else {
                long top = rowNum - i;
                for (long j = top+1; j < rowNum; j++) {
                    top *= j;
                    counter++;
                }
                long bottom = getFactorial(i);

                row[i] = (int) (top / bottom);
                counter++;
            }
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
            counter++;
            fact *= i;
        }

        if (fact < 1) {
            counter++;
            fact = 1;
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
