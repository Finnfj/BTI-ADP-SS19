package graphDykstra;

public class Node implements Comparable<Node> {
    private int id;
    private Node pred;
    private double cost;
    private boolean marked;

    public Node(int id) {
        this.id = id;
        pred = null;
        cost = Double.MAX_VALUE;
        marked = false;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Node)) return false;
        if (id != ((Node) obj).getId()) return false;
        return true;
    }

    public int getId() {
        return id;
    }

    public Node getPred() {
        return pred;
    }

    public void setPred(Node pred) {
        this.pred = pred;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean getMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    @Override
    public int compareTo(Node o) {
        if (cost < o.cost) {
            return -1;
        } else if (cost > o.cost) {
            return 1;
        } else {
            return 0;
        }
    }
}
