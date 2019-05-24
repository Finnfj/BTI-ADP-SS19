package binarySearchTree;

public class NodeArrayEmbedding implements Comparable, NodeI {
    private Integer data;
    private int key;
    private int pos;
    private int sum;
    private NodeArrayEmbedding[] nodes;

    public NodeArrayEmbedding(Integer data, NodeArrayEmbedding[] nodes) {
        this.data = data;
        key = data.hashCode();
        this.nodes = nodes;
    }

    public NodeArrayEmbedding(Integer data, NodeArrayEmbedding[] nodes, int pos) {
        this.data = data;
        key = data.hashCode();
        this.nodes = nodes;
        this.pos = pos;
    }

    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if(obj instanceof NodeArrayEmbedding == false) return false;
        return (data.equals(((NodeArrayEmbedding) obj).getData()));
        //if(((NodeArrayEmbedding) obj).getKey() != this.key) return false;
        //return true;
    }

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
        if(this.equals(newNodeArrayEmbedding)) { // if the node we want to add is equal to this, return this one
            return pos;
        } else { // else compare with childs
            if(compareTo(newNodeArrayEmbedding) > 0) { // -1 if newNodeNodeLinking is larger, 0 if newNodeNodeLinking is equal, 1 if newNodeNodeLinking is smaller
                if(nodes[getLeft()] == null) { // if this node has no left child
                    newNodeArrayEmbedding.setPos(getLeft());
                    nodes[getLeft()] = newNodeArrayEmbedding;
                    return getLeft();
                } else { //if this node has a left child, give the new node to them
                    return nodes[getLeft()].insert(newNodeArrayEmbedding);
                }
            } else { // if this node has no right child
                if(nodes[getRight()] == null) {
                    newNodeArrayEmbedding.setPos(getRight());
                    nodes[getRight()] = newNodeArrayEmbedding;
                    return getRight();
                } else { // if this node has a right child, give the new node to them
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

    public Integer getData() {
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

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
