package quicksort;

/**<br>
 * Kurs: BTI3-ADP <br>
 * Aufgabe: 5 Quicksort Algorithm<br>
 * A Quicksort Algorithm that with three different Pivots<br>
 * @author Finn Jannsen
 * @author Philipp Schwarz
 * @version 1.0, 22.04.19
 *
 */
public class QuicksortOne implements Quicksort {
    private long counter;
    public QuicksortOne() {counter = 0;}

    /**
     * A method that sorts a list of Nodes
     * @param list The list that is going to be sorted by the Nodes keys
     * @param pivotType The pivot that is going to be used
     */
    @Override
    public void sort(Node[] list, PivotType pivotType) {
        sort(list, 0, list.length-1, pivotType);
    }

    /**
     * A method that sorts a part of a list
     * @param list The list that is going to be sorted by the Nodes keys
     * @param left The smallest index of the part that is going to be sorted
     * @param right The highest index of the part that is going to be sorted
     * @param pivotType The pivot that is going to be used
     */
    @Override
    public void sort(Node[] list, int left, int right, PivotType pivotType) {
        if (right > left) {
            int i = left;
            int j = right;
            int pivot = right;
            // swap the selected pivot to the last part of the list
            switch (pivotType) {
                case RIGHT:
                    break;
                case MEDIANOFTHREE:
                    swap(list, (left + (right-left)/2 + right) / 3, right);
                    break;
                case RANDOM:
                    double index = Math.random() * right;
                    swap(list, (int) index, right);
                    break;
            }

            iterationLoop:
            while(true) {
                // increase i, which starts as the smallest index, until the Node it indexes is equal or larger than the pivot
                while(i < right && list[i].getKey() < pivot) {
                    i++;
                }

                // decrease j, which starts as the largest index, until the Node it indexes is smaller than the pivot
                while(j > 0 && list[j].getKey() >= pivot) {
                    j--;
                }

                // break out of the loop when we iterated over every entry
                if (i >= j) {
                    break iterationLoop;
                }
                // swap i, which points at an element larger than the pivot with j, which points at an element smaller than the pivot
                swap(list, i, j);
            }

            // swap the pivot to the right part
            swap(list, i, right);
            // sort everything right and left of the pivot
            sort(list, left, i-1, pivotType);
            sort(list, i+1, right, pivotType);
        }
    }

    private void swap(Node[] list, int i, int j) {
        counter++;
        Node iOld = list[i];
        list[i] = list[j];
        list[j] = iOld;
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