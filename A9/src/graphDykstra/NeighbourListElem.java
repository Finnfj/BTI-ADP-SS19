package graphDykstra;

public class NeighbourListElem {
    private Node node;
    private NeighbourListElem next;
    private double cost;

    public NeighbourListElem(Node neighbour, double cost) {
        this.node = neighbour;
        this.cost = cost;
        next = null;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public NeighbourListElem getNext() {
        return next;
    }

    public void setNext(NeighbourListElem next) {
        this.next = next;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
