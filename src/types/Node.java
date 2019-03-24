package types;

public class Node {
    private Node next;
    private Node previous;
    private Elem elem;

    public Node() {
        this.elem = null;
        this.next = null;
        this.previous = null;
    }

    public Node(Elem elem) {
        this.elem = elem;
        this.next = null;
        this.previous = null;
    }

    public Node(Elem elem, Node next, Node previous) {
        this.elem = elem;
        this.next = next;
        this.previous = previous;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Elem getElem() {
        return elem;
    }

    public void setElem(Elem elem) {
        this.elem = elem;
    }
}
