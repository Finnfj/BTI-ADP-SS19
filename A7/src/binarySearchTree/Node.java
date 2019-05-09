package binarySearchTree;

public class Node<T> {
    private T data;
    private int key;
    private Node left, right, father;

    public Node(T data, Node father) {
        this.data = data;
        this.father = father;
        key = data.hashCode();
        left = null;
        right = null;
    }

    public T getData() {
        return data;
    }

    public int getKey() {
        return key;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getFather() {
        return father;
    }

    public void setFather(Node father) {
        this.father = father;
    }
}
