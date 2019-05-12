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
 * @version 1.0, 07.05.19
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
     * @param right The highest index of the part that is going to be sorted
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
                        //Counter++;
                        swap(list, j, j - 1);
                        j--;
                    }
                }
            // Quicksort
            } else {
                int i = left;
                int j = right-1;
                int pivot = right;

                //find pivot with median of X TODO: change to X
                //swap the selected pivot to the last part of the list
                int center = (left + right) / 2;

                if (list[left].getKey() > list[center].getKey()) {
                    //Counter++;
                    swap(list, left, center);
                }
                if (list[left].getKey() > list[right].getKey()) {
                    //Counter++;
                    swap(list, left, right);
                }
                if (list[center].getKey() > list[right].getKey()) {
                    //Counter++;
                    swap(list, center, right);
                }
                //swap pivot to the right
                swap(list, center, right);
                
                int p = left - 1, q = right;
	            iterationLoop:
	            while(true) {
	                // increase i, which starts as the smallest index, until the Node it indexes is equal or larger than the pivot
	            	while(list[i].getKey() < list[pivot].getKey()) {
	                    //Counter++;
	            		i++;
	                }
	
	                // decrease j, which starts as the largest index, until the Node it indexes is smaller than the pivot
	                while(list[j].getKey() > list[pivot].getKey()) {
	                    //Counter++;
	                	j--;
	                    if (j == left) {
	                    	break;
	                    }
	                }
	
	                // break out of the loop when we iterated over every entry
	                if (i >= j) {
	                    break iterationLoop;
	                }
	                
	                // swap i, which points at an element larger than the pivot with j, which points at an element smaller than the pivot
	                swap(list, i, j);
	                
	                if (list[i].getKey() == list[pivot].getKey()) {
		            	//Counter++;
	                	p++;
	                	swap(list, p, i);
	                }
	                
	                if (list[j].getKey() == list[pivot].getKey()) {
		            	//Counter++;
	                	q--;
	                	swap(list, q, j);
	                }
	            }
	
	            // swap the pivot to the middle
	            swap(list, i, right);
	            
	            j = i-1;
	            for (int k = left; k < p; k++, j--) {
	            	//Counter++;
	            	swap(list, k, j);
	            }
	            
	            i = i+1;
	            for (int k = right-1; k > q; k--, i++) {
	            	//Counter++;
	            	swap(list, i, k);
	            }
	            sort(list, left, j);
	            sort(list, i, right);
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