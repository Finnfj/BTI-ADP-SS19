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

        for (int curRowNum = 0; curRowNum < rowNum; curRowNum++) {
            counter++;
            row = new int[curRowNum+1];

            for (int curColNum = 0; curColNum < curRowNum+1; curColNum++) {
                counter++;
                if (curColNum == 0 || curColNum == curRowNum) {
                    row[curColNum] = 1;
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
