package pascalTriangleCalculator;

public class PascalTriangleCalculatorBinoCoeff implements PascalTriangleCalculator {
    private long counter;

    public PascalTriangleCalculatorBinoCoeff() {
        counter = 0;
    }

    @Override
    public int[] calculateRow(int rowNum) {
        if (rowNum < 1) {
            return null;
        }

        int[] row = new int[rowNum];

        for (int i = 0; i < row.length; i++) {
            counter++;
            long top = getFactorial(rowNum-1);
            long bottom = (getFactorial(i) * getFactorial(rowNum-1-i));
            if (bottom == 0) {
                bottom = 1;
            }
            row[i] = (int) (top / bottom);
        }
        return row;
    }

    private long getFactorial(int num) {
        long fact = 1;

        for (int i = 1; i <= num; i++) {
            counter++;
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
