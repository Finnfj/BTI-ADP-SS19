package fasterQuicksort;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**<br>
 * Kurs: BTI3-ADP <br>
 * Aufgabe: 5 FasterQuicksortI Algorithm<br>
 * A FasterQuicksortI Algorithm that with three different Pivots<br>
 * @author Finn Jannsen
 * @author Philipp Schwarz
 * @version 1.0, 22.04.19
 *
 */
public class FasterQuicksort implements FasterQuicksortI {
    private long counter;
    private ThreadPoolExecutor threadPool; //TODO: also try out forjoinpool

    public FasterQuicksort() {
        counter = 0;
        // create thread pool with logical cpu core amount of threads
        int numThreads = Runtime.getRuntime().availableProcessors();
        threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(numThreads);
    }


    /**
     * A method that sorts a list of Nodes
     * @param list The list that is going to be sorted by the Nodes keys
     */
    @Override
    public void sort(Node[] list) {
        sort(list, 0, list.length-1);
    }

    /**
     * A method that sorts a part of a list
     * @param list The list that is going to be sorted by the Nodes keys
     * @param left The smallest index of the part that is going to be sorted
     * @param right The highest index of the part that is going to be sorted
     */
    @Override
    public void sort(Node[] list, int left, int right) {
        if (right > left) {
            // insertion sort
            if(right - left < 10) {
                // start at the second element, since there is no element left to it
                for(int i = left + 1; i <= right; i++) {
                    int j = i;
                    // swap the jth element with the one left and repeat
                    while (j > left && list[j-1].getKey() > list[j].getKey()) {
                        increaseCounter();
                        swap(list, j, j - 1);
                        j--;
                    }
                }
            // fasterQuicksort
            } else {
                int i = left;
                int j = right - 1;
                int pivot = right;

                //find pivot with median of X TODO: change to X
                //swap the selected pivot to the last part of the list
                int center = (left + right) / 2;

                if (list[left].getKey() > list[center].getKey()) {
                    increaseCounter();
                    swap(list, left, center);
                }
                if (list[left].getKey() > list[right].getKey()) {
                    increaseCounter();
                    swap(list, left, right);
                }
                if (list[center].getKey() > list[right].getKey()) {
                    increaseCounter();
                    swap(list, center, right);
                }
                swap(list, center, right);

                iterationLoop:
                while (true) {
                    // increase i, which starts as the smallest index, until the Node it indexes is equal or larger than the pivot
                    while (list[i].getKey() < list[pivot].getKey()) {
                        increaseCounter();
                        i++;
                    }

                    // decrease j, which starts as the largest index, until the Node it indexes is smaller than the pivot
                    while (j > i && list[j].getKey() >= list[pivot].getKey()) {
                        increaseCounter();
                        j--;
                    }

                    // break out of the loop when we iterated over every entry
                    if (i >= j) {
                        break iterationLoop;
                    }
                    // swap i, which points at an element larger than the pivot with j, which points at an element smaller than the pivot
                    swap(list, i, j);
                }

                // swap the pivot to the middle
                swap(list, i, right);
                // sort everything right and left of the pivot
                // create new threads
                final int iMinusOne = i - 1;
                final int iPlusOne = i + 1;
                threadPool.submit(() -> sort(list, left, iMinusOne));
                threadPool.submit(() -> sort(list, iPlusOne, right));
            }
        }
    }

	private void swap(Node[] list, int i, int j) {
    	if(i != j) {
            Node iOld = list[i];
            list[i] = list[j];
            list[j] = iOld;
    	}
    }

    private synchronized void increaseCounter() {
        counter++;
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