package quicksort;

public interface QuicksortI {
    void sort(Node[] list, PivotType pivotType);
    void sort(Node[] list, int left, int right, PivotType pivotType);
    long getCounter();
    void resetCounter();
}
