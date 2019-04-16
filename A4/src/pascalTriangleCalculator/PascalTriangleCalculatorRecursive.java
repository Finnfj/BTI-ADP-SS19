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

        triangle = new int[rowNum][];
        for (int i = 0; i < triangle.length; i++) {
            triangle[i] = new int[i+1];
        }

        int[] row = new int[rowNum];

        for (int i = 0; i < rowNum; i++) {
            counter++;
            row[i] = calculateNum(rowNum, i);
        }

        return row;
    }

    private int calculateNum(int rowNum, int colNum) {
        counter++;
        if (colNum == 0 || colNum == rowNum - 1) {
            return 1;
        } else if (colNum == 1 || colNum == rowNum -2){
        	return rowNum-1;
        } else {
            if (triangle[rowNum-1][colNum] != 0) {
                return triangle[rowNum-1][colNum];
            } else {
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
