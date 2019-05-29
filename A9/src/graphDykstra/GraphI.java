package graphDykstra;

import java.util.HashSet;

public interface GraphI {
    void addNode(Node node);
    HashSet<Node> getNodes();
    double getWeight(Node a, Node b);
    void addWeight(double weight, Node a, Node b);
}