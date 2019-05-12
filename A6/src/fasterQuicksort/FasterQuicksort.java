package fasterQuicksort;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import quicksort.PivotType;

/**<br>
 * Kurs: BTI3-ADP <br>
 * Aufgabe: 5 FasterQuicksortI Algorithm<br>
 * A Quicksort Algorithm that uses InsertionSort for list sizes smaller than 10, multithreading and median of X as Pivot<br>
 * @author Finn Jannsen
 * @author Philipp Schwarz
 * @version 2.0, 12.05.19
 *
 */
public class FasterQuicksort implements FasterQuicksortI {
    private long Counter;
    public FasterQuicksort() {Counter = 0;}

    /**
     * A method that sorts a list of Nodes
     * @param list The list that is going to be sorted by the Nodes keys
     * @param pivotType The pivot that is going to be used
     */
    @Override
    public void sort(Node[] list) {
        sort(list, 0, list.length-1);
    }

    /**
     * A method that sorts a part of a list
     * @param list The list that is going to be sorted by the Nodes keys
     * @param left The smallest index of the part that is going to be sorted
     * @param right The rightest index of the part that is going to be sorted
     * @param pivotType The pivot that is going to be used
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
                        Counter++;
                        swap(list, j, j - 1);
                        j--;
                    }
                }
            // Quicksort
            } else {
                // Calculate Median-of-three
                int center = (left + right) / 2;

                if (list[left].getKey() > list[right].getKey()) {
                    Counter++;
                    swap(list, left, right);
                }
                if (list[center].getKey() > list[right].getKey()) {
                    Counter++;
                    swap(list, center, right);
                }
                if (list[center].getKey() < list[left].getKey()) {
                    Counter++;
                    swap(list, center, left);
                }
                //swap pivot to the left
                swap(list, center, left);

                int low = left, high = right;
                int pivot = list[left].getKey();
                int i = left;
                
                while (true) {
                	if (list[i].getKey() < pivot) {
                		// smaller than pivot, accumulate from the left and eventually shift pivot to i
                		swap(list, i, low);
                		i++; 
                		low++;
                        Counter++;
                	} else if (list[i].getKey() > pivot) {
                		// bigger than pivot, accumulate from the right
                		swap(list, i, high);
                		high--;
                        Counter++;
                	} else {
                		// ran over pivot, skip
                		i++;
                        Counter++;
                	}
                	if (i > high) {
                		// don't run over already iterated elements
                		break;
                	}
                }
                
                
	            sort(list, left, low-1);
	            sort(list, high+1, right);
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

    @Override
    public long getCounter() {
        return Counter;
    }

    @Override
    public void resetCounter() {
        Counter = 0;
    }
}