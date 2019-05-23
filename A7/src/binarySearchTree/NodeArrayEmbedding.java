package binarySearchTree;

public class NodeArrayEmbedding<T> implements Comparable, NodeI {
    private T data;
    private int key;
    private int pos;
    private NodeArrayEmbedding[] nodes;

    public NodeArrayEmbedding(T data, NodeArrayEmbedding[] nodes) {
        this.data = data;
        key = data.hashCode();
        this.nodes = nodes;
    }

    public NodeArrayEmbedding(T data, NodeArrayEmbedding[] nodes, int pos) {
        this.data = data;
        key = data.hashCode();
        this.nodes = nodes;
        this.pos = pos;
    }

    @Override //TODO: check for changes
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if(obj instanceof NodeArrayEmbedding == false) return false;
        return (data.equals(((NodeArrayEmbedding) obj).getData()));
        //if(((NodeArrayEmbedding) obj).getKey() != this.key) return false;
        //return true;
    }

    @Override //TODO: check for changes
    public int compareTo(Object obj) {
        if(data instanceof Comparable && obj instanceof Comparable) {
            if(data.getClass() == obj.getClass()) {
                return ((Comparable) data).compareTo(obj);
            }
        }
        if(obj == null) return 0;
        if(obj instanceof NodeArrayEmbedding == false) return 0;
        return this.key - ((NodeArrayEmbedding) obj).getKey();
    }

    public int insert(NodeArrayEmbedding newNodeArrayEmbedding) {
        if(this.equals(newNodeArrayEmbedding)) {
            return pos;
        } else {
            if(compareTo(newNodeArrayEmbedding) > 0) { // -1 if newNodeNodeLinking is larger, 0 if newNodeNodeLinking is equal, 1 if newNodeNodeLinking is smaller
                if(nodes[getLeft()] == null) {
                    newNodeArrayEmbedding.setPos(getLeft());
                    nodes[getLeft()] = newNodeArrayEmbedding;
                    return getLeft();
                } else {
                    return nodes[getLeft()].insert(newNodeArrayEmbedding);
                }
            } else {
                if(nodes[getRight()] == null) {
                    newNodeArrayEmbedding.setPos(getRight());
                    nodes[getRight()] = newNodeArrayEmbedding;
                    return getRight();
                } else {
                    return nodes[getRight()].insert(newNodeArrayEmbedding);
                }
            }
        }
    }

    public void printData(PrintVariant pv) {
        switch (pv) {
            case INORDER:
                if(nodes[getLeft()] != null) {
                    nodes[getLeft()].printData(pv);
                }
                System.out.println(data.toString() + ", " + key);
                if(nodes[getRight()] != null) {
                    nodes[getRight()].printData(pv);
                }
                break;
            case POSTORDER:
                if(nodes[getLeft()] != null) {
                    nodes[getLeft()].printData(pv);
                }
                if(nodes[getRight()] != null) {
                    nodes[getRight()].printData(pv);
                }
                System.out.println(data.toString() + ", " + key);
                break;
            case PREORDER:
                System.out.println(data.toString() + ", " + key);
                if(nodes[getLeft()] != null) {
                    nodes[getLeft()].printData(pv);
                }
                if(nodes[getRight()] != null) {
                    nodes[getRight()].printData(pv);
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

    public int getLeft() {
        return pos*2;
    }

    public int getRight() {
        return pos*2+1;
    }

    public int getFather() {
        return pos/2;
    }

    public void setPos(int pos) {this.pos = pos;}
}
