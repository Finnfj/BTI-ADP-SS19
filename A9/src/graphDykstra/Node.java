package graphDykstra;

import java.util.Comparator;
import java.util.HashSet;

public class Node implements Comparator, Comparable {
    private int id;
    private Node pred;
    private double cost;
    private boolean marked;
    private HashSet<Node> neighbours;

    public Node(int id) {
        this.id = id;
        pred = null;
        cost = Double.MAX_VALUE;
        marked = false;
        neighbours = new HashSet<>();
    }

    public Node(int id, HashSet<Node> neighbours) {
        this.id = id;
        pred = null;
        cost = Double.MAX_VALUE;
        marked = false;
        this.neighbours = neighbours;
    }

    public void addNeighbour(Node neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public int compare(Object o1, Object o2) {
        //if(!(o1 instanceof Node) || !(o2 instanceof Node)) return 0;
        return (int) (((Node) o1).getCost() - ((Node) o2).getCost());
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

    public HashSet<Node> getNeighbours() {
        return neighbours;
    }

    @Override
    public int compareTo(Object o) {
        return (int) (cost - ((Node) o).getCost());
    }
}
