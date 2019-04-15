package pascalTriangleCalculator;

public class PascalTriangleCalculatorIterative implements PascalTriangleCalculator {
    private long counter;

    public PascalTriangleCalculatorIterative() {
        counter = 0;
    }

    @Override
    public int[] calculateRow(int rowNum) {
        if (rowNum < 1) {
            return null;
        }

        int[] oldRow = null;
        int[] row = null;

        for (int i = 0; i < rowNum; i++) {
            counter++;
            row = new int[i+1];

            for (int j = 0; j < i+1; j++) {
                counter++;
                if (j == 0 || j == i) {
                    row[j] = 1;
                } else {
                    row[j] = oldRow[j-1] + oldRow[j];
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
