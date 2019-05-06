package quicksort;

public interface QuicksortI {
    void sort(Node[] list);
    void sort(Node[] list, int left, int right);
    long getCounter();
    void resetCounter();
}
