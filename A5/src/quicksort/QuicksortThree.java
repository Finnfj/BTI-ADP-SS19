package quicksort;

import java.util.ArrayList;
import java.util.List;

public class QuicksortThree implements Quicksort {
    private long counter;
    public QuicksortThree() {counter = 0;}

    public void sort(Node[] list, PivotType pivotType) {
        sort(list, 0, list.length-1, pivotType);
    }

    @Override
    public void sort(Node[] list, int left, int right, PivotType pivotType) {
        counter++;
        if (left < right) {
            int p = partition(list, left, right, pivotType);
            sort(list, left, p-1, pivotType);
            sort(list, p+1, right, pivotType);
        }
    }

    int partition(Node[] list, int left, int right, PivotType pivotType) {
        int i = left;

        int pivot = right;
        // select PivotType
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

        for(int j = left; j < right; j++) {
            if (list[j].getKey() < pivot) {
                swap(list, i, j);
                i++;
            }
        }
        swap(list, i, right);
        return i;
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