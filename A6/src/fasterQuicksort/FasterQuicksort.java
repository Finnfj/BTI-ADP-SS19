package fasterQuicksort;

import java.util.ArrayList;
import java.util.concurrent.*;

/**<br>
 * Kurs: BTI3-ADP <br>
 * Aufgabe: 5 FasterQuicksortI Algorithm<br>
 * A Quicksort Algorithm that uses InsertionSort for list sizes smaller than 10, multithreading and median of X as Pivot<br>
 * @author Finn Jannsen
 * @author Philipp Schwarz
 * @version 1.0, 07.05.19
 *
 */
public class FasterQuicksort implements FasterQuicksortI {
    private long counter;
    private ThreadPoolExecutor threadPool; //TODO: also try out forjoinpool
    private ConcurrentLinkedQueue<CompletableFuture> completableFutureArrayList;
    //private CompletableFuture rootCompletableFuture;

    public FasterQuicksort() {
        counter = 0;
        completableFutureArrayList = new ConcurrentLinkedQueue<>();
    }

    /**
     * A method that sorts a list of Nodes
     * @param list The list that is going to be sorted by the Nodes keys
     */
    @Override
    public void sort(Node[] list) {
        int numThreads = Runtime.getRuntime().availableProcessors(); // get amount of cpu cores on pc
        threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(numThreads); // create thread pool with amount of cpu cores threads
        completableFutureArrayList.add(CompletableFuture.runAsync(() -> sort(list, 0, list.length-1), threadPool)); // add completablefuture of the runnable to arraylist
        while(!CompletableFuture.allOf(completableFutureArrayList.toArray(new CompletableFuture[completableFutureArrayList.size()])).isDone()) {  // check if every completablefuture is done
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threadPool.shutdown(); // shutdown the thread pool, it's threads are still running
        while (true) {
            try {
                if (threadPool.awaitTermination(1L, TimeUnit.HOURS)) break; // only break if every thread is done
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * A method that sorts a part of a list
     * @param list The list that is going to be sorted by the Nodes keys
     * @param left The smallest index of the part that is going to be sorted
     * @param right The highest index of the part that is going to be sorted
     */
    private void sort(Node[] list, int left, int right) {
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
            // Quicksort
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
                completableFutureArrayList.add(CompletableFuture.runAsync(() -> sort(list, left, iMinusOne), threadPool));
                completableFutureArrayList.add(CompletableFuture.runAsync(() -> sort(list, iPlusOne, right), threadPool));
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