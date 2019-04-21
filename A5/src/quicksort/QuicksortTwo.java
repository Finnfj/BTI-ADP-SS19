package quicksort;

import java.util.ArrayList;
import java.util.List;

public class QuicksortTwo implements Quicksort {
    private long counter;
    public QuicksortTwo() {counter = 0;}

    public void sort(Node[] list, PivotType pivotType) {
        sort(list, 0, list.length-1, pivotType);
    }

    @Override
    public void sort(Node[] list, int left, int right, PivotType pivotType) {
        counter++;
        if (left < right) {
            int p = partition(list, left, right, pivotType);
            sort(list, left, p, pivotType);
            sort(list, p+1, right, pivotType);
        }
    }

    int partition(Node[] list, int left, int right, PivotType pivotType) {
        int i = left - 1;
        int j = right + 1;

        int pivot = -1;
        // select PivotType
        switch (pivotType) {
            case RIGHT:
                pivot = list[right].getKey();
                break;
            case MEDIANOFTHREE:
                pivot = (list[left].getKey() + list[(right-left)/2].getKey() + list[right].getKey()) / 3;
                break;
            case RANDOM:
                double index = Math.random() * list.length;
                pivot = list[(int) index].getKey();
                break;
        }

        while(true) {
            do {
                i++;
            } while (i < right && list[i].getKey() < pivot);

            do {
                j--;
            } while (j > 0 && list[j].getKey() > pivot);

            if (i >= j) {
                return j;
            }

            swap(list, i, j);
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