package types;

/**
 * A Node that contains an Elem object and two Nodes
 */
public class Node {
    private Node next;
    private Node previous;
    private Elem elem;

    /**
     * A constructor that sets every attribute to null
     */
    public Node() {
        this.elem = null;
        this.next = null;
        this.previous = null;
    }

    /**
     * A constructor where you can directly set the elem
     * @param elem The elem
     */
    public Node(Elem elem) {
        this.elem = elem;
        this.next = null;
        this.previous = null;
    }

    /**
     * Returns the next node
     * @return The node
     */
    public Node getNext() {
        return next;
    }

    /**
     * Set the next node
     * @param next The node
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * Returns the previous node
     * @return The node
     */
    public Node getPrevious() {
        return previous;
    }

    /**
     * Sets the previous node
     * @param previous The node
     */
    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    /**
     * Returns the elem
     * @return The elem
     */
    public Elem getElem() {
        return elem;
    }
}
