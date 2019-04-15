package pascalTriangleCalculator;

public class PascalTriangleCalculatorRecursive implements PascalTriangleCalculator {
    private long counter;

    public PascalTriangleCalculatorRecursive() {
        counter = 0;
    }

    @Override
    public int[] calculateRow(int rowNum) {
        if (rowNum < 1) {
            return null;
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
        if (colNum == 0 || colNum == rowNum- 1) {
            return 1;
        } else {
            return calculateNum(rowNum - 1, colNum - 1) + calculateNum(rowNum - 1, colNum);
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
