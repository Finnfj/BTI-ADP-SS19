package binarySearchTree;

public class NodeNodeLinking<T> implements Comparable, NodeI {
    private T data;
    private int key;
    private NodeNodeLinking left, right, father;

    public NodeNodeLinking(T data) {
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
        if(obj instanceof NodeNodeLinking == false) return false;
        return (data.equals(((NodeNodeLinking) obj).getData()));
        //if(((NodeNodeLinking) obj).getKey() != this.key) return false;
        //return true;
    }

    @Override
    public int compareTo(Object obj) {
        if(data instanceof Comparable && obj instanceof Comparable) {
            if(data.getClass() == obj.getClass()) {
                return ((Comparable) data).compareTo(obj);
            }
        }
        if(obj == null) return 0;
        if(obj instanceof NodeNodeLinking == false) return 0;
        return this.key - ((NodeNodeLinking) obj).getKey();
    }

    public boolean exist(NodeNodeLinking newNodeNodeLinking) {
        if(this.equals(newNodeNodeLinking)) {
            return true;
        } else {
            if(compareTo(newNodeNodeLinking) > 0) { // -1 if newNodeNodeLinking is smaller, 0 if newNodeNodeLinking is equal, 1 if newNodeNodeLinking is larger
                if(this.left == null) {
                    return false;
                } else {
                    return this.left.exist(newNodeNodeLinking);
                }
            } else {
                if(this.right == null) {
                    return false;
                } else {
                    return this.right.exist(newNodeNodeLinking);
                }
            }
        }
    }

    public NodeNodeLinking insert(NodeNodeLinking newNodeNodeLinking) {
        if(this.equals(newNodeNodeLinking)) {
            return this;
        } else {
            if(compareTo(newNodeNodeLinking) > 0) { // -1 if newNodeNodeLinking is larger, 0 if newNodeNodeLinking is equal, 1 if newNodeNodeLinking is smaller
                if(left == null) {
                    left = newNodeNodeLinking;
                    return left;
                } else {
                    return left.insert(newNodeNodeLinking);
                }
            } else {
                if(right == null) {
                    right = newNodeNodeLinking;
                    return right;
                } else {
                    return right.insert(newNodeNodeLinking);
                }
            }
        }
    }

    public void printData(PrintVariant pv) {
        switch (pv) {
            case INORDER:
                if(left != null) {
                    left.printData(pv);
                }
                System.out.println(data.toString() + ", " + key);
                if(right != null) {
                    right.printData(pv);
                }
                break;
            case POSTORDER:
                if(left != null) {
                    left.printData(pv);
                }
                if(right != null) {
                    right.printData(pv);
                }
                System.out.println(data.toString() + ", " + key);
                break;
            case PREORDER:
                System.out.println(data.toString() + ", " + key);
                if(left != null) {
                    left.printData(pv);
                }
                if(right != null) {
                    right.printData(pv);
                }
                break;
        }
    }

    public T getData() {
        return data;
    }

    public int getKey() {
        return key;
    }

    public NodeNodeLinking getLeft() {
        return left;
    }

    public void setLeft(NodeNodeLinking left) {
        this.left = left;
    }

    public NodeNodeLinking getRight() {
        return right;
    }

    public void setRight(NodeNodeLinking right) {
        this.right = right;
    }

    public NodeNodeLinking getFather() {
        return father;
    }

    public void setFather(NodeNodeLinking father) {
        this.father = father;
    }
}
