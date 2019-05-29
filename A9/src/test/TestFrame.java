package test;

import graphDykstra.Dykstra;
import graphDykstra.GraphI;
import graphDykstra.GraphMatrix;
import graphDykstra.Node;

public class TestFrame {
    public static void main(String... args) {
        GraphI gm = new GraphMatrix(4);
        Node nodeA = new Node(0);
        Node nodeB = new Node(1);
        Node nodeC = new Node(2);
        Node nodeD = new Node(3);

        nodeA.addNeighbour(nodeB);
        nodeB.addNeighbour(nodeA);

        nodeA.addNeighbour(nodeC);
        nodeC.addNeighbour(nodeA);

        nodeB.addNeighbour(nodeD);
        nodeD.addNeighbour(nodeB);

        nodeC.addNeighbour(nodeB);
        nodeB.addNeighbour(nodeC);

        gm.addWeight(5.0, nodeA, nodeB);
        gm.addWeight(1.0, nodeA, nodeC);
        gm.addWeight(1.0, nodeB, nodeD);
        gm.addWeight(1.0, nodeB, nodeC);

        gm.addNode(nodeA);
        gm.addNode(nodeB);
        gm.addNode(nodeC);
        gm.addNode(nodeD);

        Dykstra.findRoute(gm, nodeA);
        System.out.println("done");
    }
}
