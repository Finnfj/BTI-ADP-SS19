package pascalTriangleCalculator;

public class PascalTriangleCalculatorRecursive implements PascalTriangleCalculator {
    private long counter;
    private int[][] triangle;

    public PascalTriangleCalculatorRecursive() {
        counter = 0;
        triangle = null;
    }

    @Override
    public int[] calculateRow(int rowNum) {
        if (rowNum < 1) {
            return null;
        }

        if (this.triangle != null && this.triangle.length < rowNum) {
        	int[][] tmp = new int[rowNum][];
        	System.arraycopy(this.triangle, 0, tmp, 0, this.triangle.length);
        	this.triangle = tmp;

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
        
        
        for (int i = 0; i < rowNum; i++) {
            row[i] = calculateNum(rowNum, i);
        }

        return row;
    }

    private int calculateNum(int rowNum, int colNum) {
        if (colNum == 0 || colNum == rowNum - 1) {
            return 1;
        } else if (colNum == 1 || colNum == rowNum -2){
        	return rowNum-1;
        } else {
            if (triangle[rowNum-1][colNum] != 0) {
                return triangle[rowNum-1][colNum];
            } else {
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
