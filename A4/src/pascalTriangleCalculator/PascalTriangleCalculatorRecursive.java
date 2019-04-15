package pascalTriangleCalculator;

public class PascalTriangleCalculatorRecursive implements PascalTriangleCalculator {
    private long counter;
    private int[][] triangle;
    private boolean[][] triangleCalced;

    public PascalTriangleCalculatorRecursive() {
        counter = 0;
        triangle = null;
        triangleCalced = null;
    }

    @Override
    public int[] calculateRow(int rowNum) {
        if (rowNum < 1) {
            return null;
        }

        triangle = new int[rowNum][];
        triangleCalced = new boolean[rowNum][];
        for (int i = 0; i < triangle.length; i++) {
            triangle[i] = new int[i+1];
            triangleCalced[i] = new boolean[i+1];
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
        } else {
            if (triangleCalced[rowNum-1][colNum]) {
                return triangle[rowNum-1][colNum];
            } else {
                int num = calculateNum(rowNum - 1, colNum - 1) + calculateNum(rowNum - 1, colNum);
                triangle[rowNum-1][colNum] = num;
                triangleCalced[rowNum-1][colNum] = true;
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
