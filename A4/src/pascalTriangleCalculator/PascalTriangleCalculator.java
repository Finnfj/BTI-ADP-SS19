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
    int[] calculateRow(int rowNum);
    long getCounter();
    void resetCounter();
}
