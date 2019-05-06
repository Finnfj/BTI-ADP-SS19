package quicksort;

public interface QuickersortI {
    void sort(Node[] list);
    void sort(Node[] list, int left, int right);
    long getCounter();
    void resetCounter();
}
