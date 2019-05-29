package test;

import graphDykstra.*;

import java.util.PriorityQueue;
import java.util.Queue;

public class TestFrame {
    public static void main(String... args) {
        testGraphMaxtrix();
        testPriorityQueueWithNodes();
    }

    public static void testGraphMaxtrix() {
        //GraphI gm = new GraphMatrix(4);
        GraphI gm = new GraphList();
        Node nodeA = new Node(0);
        Node nodeB = new Node(1);
        Node nodeC = new Node(2);
        Node nodeD = new Node(3);

        gm.addNeighbour(nodeA, nodeB, 5.0);
        gm.addNeighbour(nodeC, nodeA, 1.0);
        gm.addNeighbour(nodeD, nodeB, 1.0);
        gm.addNeighbour(nodeB, nodeC, 1.0);

        gm.addNode(nodeA);
        gm.addNode(nodeB);
        gm.addNode(nodeC);
        gm.addNode(nodeD);

        Dykstra.findRoute(gm, nodeA);
        System.out.println("done");
    }

    public static void testPriorityQueueWithNodes() {
        Queue<Node> rand = new PriorityQueue<>();

        Node a = (new Node(0));
        Node b = (new Node(1));
        a.setCost(3.0);
        b.setCost(1.0);
        rand.add(a);
        rand.add(b);

        for(int i = 0; i < 2; i++) {
            System.out.print(rand.poll().getId() + ", ");
        }
        System.out.println();
    }
}
