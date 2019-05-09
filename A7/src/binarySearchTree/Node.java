package binarySearchTree;

public class Node<T> implements Comparable {
    private T data;
    private int key;
    private Node left, right, father;

    public Node(T data) {
        this.data = data;
        key = data.hashCode();
        this.father = null;
        left = null;
        right = null;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if(obj instanceof Node == false) return false;
        if(((Node) obj).getKey() != this.key) return false;
        return true;
    }

    @Override
    public int compareTo(Object obj) {
        if(obj == null) return 0;
        if(obj instanceof Node == false) return 0;
        return this.key - ((Node) obj).getKey();
    }

    public boolean exist(Node newNode) {
        if(this.equals(newNode)) {
            return true;
        } else {
            if(compareTo(newNode) > 0) { // -1 if newNode is smaller, 0 if newNode is equal, 1 if newNode is larger
                if(this.left == null) {
                    return false;
                } else {
                    return this.left.exist(newNode);
                }
            } else {
                if(this.right == null) {
                    return false;
                } else {
                    return this.right.exist(newNode);
                }
            }
        }
    }

    public Node insert(Node newNode) {
        if(this.equals(newNode)) {
            return this;
        } else {
            if(compareTo(newNode) > 0) { // -1 if newNode is smaller, 0 if newNode is equal, 1 if newNode is larger
                if(left == null) {
                    left = newNode;
                    return left;
                } else {
                    return left.insert(newNode);
                }
            } else {
                if(right == null) {
                    right = newNode;
                    return right;
                } else {
                    return right.insert(newNode);
                }
            }
        }
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
