package graphDykstra;

import java.util.ArrayList;

public interface GraphI {
    void addNode(Node node);
    ArrayList<Node> getNodes();
    void addNeighbour(Node n1, Node n2, double weight);
    ArrayList<Node> getNeighbours(Node node);
    double getWeight(Node n1, Node n2);
}