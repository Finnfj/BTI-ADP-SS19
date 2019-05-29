package graphDykstra;

import java.util.ArrayList;

public interface GraphI {
    void addNode(Node node);
    ArrayList<Node> getNodes();
    double getWeight(Node n1, Node n2);
    ArrayList<Node> getNeighbours(Node node);
    void addNeighbour(Node n1, Node n2, double weight);
}