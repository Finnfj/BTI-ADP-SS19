package pascalTriangleCalculator;

public class PascalTriangleCalculatorBinoCoeff implements PascalTriangleCalculator {
    private int counter;

    public PascalTriangleCalculatorBinoCoeff() {
        counter = 0;
    }

    @Override
    public int[] calculateRow(int rowNum) {
        return new int[0];
    }

    @Override
    public int getCounter() {
        return 0;
    }

    @Override
    public void resetCounter() {
        counter = 0;
    }
}
