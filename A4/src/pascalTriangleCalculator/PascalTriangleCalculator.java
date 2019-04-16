package pascalTriangleCalculator;

/**<br>
 * Kurs: BTI3-ADP <br>
 * Aufgabe: 4 Pascal Triangle Calculator<br>
 * An Interface ofr a Pascal Triagnle Calculator<br>
 * @author Finn Jannsen
 * @author Philipp Schwarz
 * @version 1.0, 16.04.19
 *
 */
public interface PascalTriangleCalculator {
    /**
     * Calculates a specific row of a Pascal Triangle
     * @param rowNum The row you want to be calculated. The first one is 1.
     * @return Int Array with the numbers of the row or null if the paramter was not correct
     */
    int[] calculateRow(int rowNum);
    long getCounter();
    void resetCounter();
}
