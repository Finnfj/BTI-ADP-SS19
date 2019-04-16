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

        int[] pre = null;
        if (rowNum > 1) {
        	pre = calculateRow(rowNum - 1);
        } else if (rowNum == 1) {
        	pre = new int[]{1};
        }

        int[] row = new int[rowNum];
        
        
        for (int i = 0; i < rowNum; i++) {
        	if (i == 0 || i == rowNum - 1) {
        		row[i] = pre[0];
        	} else if (i == 1 || i == rowNum -2) {
        		row[i] = rowNum - 1;
        	} else {
        		row[i] = pre[i-1] + pre[i];
        	}
            counter++;
        }
        return row;
    }

    public int calculateNum(int rowNum, int colNum) {
    	int len = this.triangle.length;
        if (this.triangle != null && len < rowNum) {	// Allocate a bigger array for our needs
        	int[][] tmp = new int[rowNum][];
        	System.arraycopy(this.triangle, 0, tmp, 0, len);
        	this.triangle = tmp;

            for (int i = len-1; i < triangle.length; i++) {
            	if (triangle[i] == null) {
                    triangle[i] = new int[i+1];			// fill in null spaces
            	}
            }
            
        } else if (this.triangle == null) {				// There is no memory array yet, create one
        	triangle = new int[rowNum][];
            for (int i = 0; i < triangle.length; i++) {
                triangle[i] = new int[i+1];				// fill in null spaces
            }
        }
        
    	// Actual calculation
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
