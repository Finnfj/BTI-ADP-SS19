package graphDykstra;

import java.util.HashSet;

public abstract class GraphA {
    private HashSet<Node> nodes = new HashSet<>();

    public void addNode(Node node) {
        nodes.add(node);
    }

    public HashSet<Node> getNodes() {
        return nodes;
    }
}