package pascalTriangleCalculator;

/**<br>
 * Kurs: BTI3-ADP <br>
 * Aufgabe: 4a Pascal Triagnle Calculator with recursive solution<br>
 * A Pascal Triangle Calculator that uses recursion<br>
 * @author Finn Jannsen
 * @author Philipp Schwarz
 * @version 1.0, 16.04.19
 *
 */
public class PascalTriangleCalculatorRecursive implements PascalTriangleCalculator {
    private long counter;
    private int[][] triangle;

    /**
     * Constructor for the recursive Pascal Triangle Calculator
     */
    public PascalTriangleCalculatorRecursive() {
        counter = 0;
        triangle = null;
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

        if (this.triangle != null && this.triangle.length < rowNum) {
        	int[][] tmp = new int[rowNum][];
        	System.arraycopy(this.triangle, 0, tmp, 0, this.triangle.length);
        	this.triangle = tmp;

        	// create a row with the right size for each row in the main array
            for (int i = 0; i < triangle.length; i++) {
            	if (triangle[i] == null) {
                    triangle[i] = new int[i+1];
            	}
            }
            
        } else if (this.triangle == null) {
        	triangle = new int[rowNum][];

            for (int i = 0; i < triangle.length; i++) {
                triangle[i] = new int[i+1];
            }
        }
        
        if (rowNum > 1) {
        	calculateRow(rowNum - 1);
        }
        
        int[] row = new int[rowNum];
        
        // calcuate each row
        for (int i = 0; i < rowNum; i++) {
            row[i] = calculateNum(rowNum, i);
        }

        return row;
    }

    /**
     * Calculates a number in a pascal triangle
     * @param rowNum The row of the number
     * @param colNum The column of the number
     * @return The number as int
     */
    private int calculateNum(int rowNum, int colNum) {
        // first and last number in a row is alway 1
        if (colNum == 0 || colNum == rowNum - 1) {
            return 1;
        // second and second to last number is always row num
        } else if (colNum == 1 || colNum == rowNum - 2){
        	return rowNum-1;
        } else {
            // check if the num was already calculated
            if (triangle[rowNum-1][colNum] != 0) {
                return triangle[rowNum-1][colNum];
            } else {
                // calculate the number by recursively calling this function
                counter++;
                int num = calculateNum(rowNum - 1, colNum - 1) + calculateNum(rowNum - 1, colNum);
                triangle[rowNum-1][colNum] = num;
                return num;
            }
        }
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
