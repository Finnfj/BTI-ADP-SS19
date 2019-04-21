package quicksort;

public class QuicksortOne implements Quicksort {
    private long counter;
    public QuicksortOne() {counter = 0;}

    public void sort(Node[] list, PivotType pivotType) {
        sort(list, 0, list.length-1, pivotType);
    }

    @Override
    public void sort(Node[] list, int left, int right, PivotType pivotType) {
        counter++;
        if (right > left) {
            int i = left;
            int j = right;
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

            iterationLoop:
            while(true) {
                counter++;
                while(i < right && list[i].getKey() < pivot) {
                    counter++;
                    i++;
                }
                counter++;
                while(j > 0 && list[j].getKey() >= pivot) {
                    counter++;
                    j--;
                }
                counter++;
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