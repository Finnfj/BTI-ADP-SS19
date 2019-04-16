package pascalTriangleCalculator;

/**<br>
 * Kurs: BTI3-ADP <br>
 * Aufgabe: 4b Pascal Triagnle Calculator with iterative solution<br>
 * A Pascal Triangle Calculator that uses an iteratrive approach<br>
 * @author Finn Jannsen
 * @author Philipp Schwarz
 * @version 1.0, 16.04.19
 *
 */
public class PascalTriangleCalculatorIterative implements PascalTriangleCalculator {
    private long counter;

    /**
     * Constructor for the iterative Pascal Triangle Calculator
     */
    public PascalTriangleCalculatorIterative() {
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

        int[] oldRow = null;
        int[] row = null;

        // calculate every row, from the first to the one we want
        for (int curRowNum = 0; curRowNum < rowNum; curRowNum++) {
            counter++;
            row = new int[curRowNum+1];

            // calculate every column of the current row
            for (int curColNum = 0; curColNum < curRowNum+1; curColNum++) {
                counter++;
                // if we are at the first or last column, the answer is always 1
                if (curColNum == 0 || curColNum == curRowNum) {
                    row[curColNum] = 1;
                // if we are at the second or second to last column, the answer is always the row size
                } else if (curColNum == 1 || curColNum == curRowNum - 1) {
                    row[curColNum] = curRowNum;
                } else {
                    row[curColNum] = oldRow[curColNum-1] + oldRow[curColNum];
                }
            }
            oldRow = row;
        }
        return oldRow;
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
