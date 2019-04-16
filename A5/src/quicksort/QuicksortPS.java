package quicksort;

import java.util.ArrayList;
import java.util.List;

public class QuicksortPS implements Quicksort {
    private long counter;
    public QuicksortPS() {counter = 0;}

    public void sort(Node[] list, PivotType pivotType) {
        sort(list, 0, list.length-1, pivotType);
    }

    @Override
    public void sort(Node[] list, int left, int right, PivotType pivotType) {
        counter++;
        if (right > left) {
            int i = left;
            int j = right;
            int pivot = -1;
            // select PivotType
            switch (pivotType) {
                case RIGHT:
                    pivot = list[list.length-1].getKey();
                    break;
                case MEDIANOFTHREE:
                    pivot = (list[0].getKey() + list[list.length/2].getKey() + list[list.length-1].getKey()) / 3;
                    break;
                case RANDOM:
                    double index = Math.random() * list.length;
                    pivot = list[(int) index].getKey();
                    break;
            }

            iterationLoop:
            while(true) {
                while(list[i].getKey() < pivot) {
                    i++;
                }
                while(list[j].getKey() >= pivot) { // TODO: j causes out of bonds exception
                    j--;
                }
                if (i >= j) {
                    break iterationLoop;
                }
                swap(list, i, j);
            }

            swap(list, i, right);
            sort(list, left, i-1, pivotType);
            sort(list, i+1, right, pivotType);
        }
    }

    private void swap(Node[] list, int i, int j) {
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