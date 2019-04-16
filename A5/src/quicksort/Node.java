package quicksort;

public class Node<T> {
    private T object;
    private int key;

    public Node(T object) {
        this.object = object;
        key = object.hashCode();
    }

    public int getKey() {
        return key;
    }

    public T getObject() {
        return object;
    }
}
