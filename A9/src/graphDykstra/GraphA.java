package graphDykstra;

import java.util.ArrayList;

public abstract class GraphA {
    private ArrayList<Node> nodes = new ArrayList<>();

    public void addNode(Node node) {
        nodes.add(node);
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }
}