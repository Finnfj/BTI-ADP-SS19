package test;

import graphDykstra.*;

import java.util.Random;

public class QuantitativeTest {
    public static void main(String... args) {
        int samplesize = 30;

        long counterMatrix = 0;
        long counterList = 0;
        for (int size = 10; size <= 10_000; size *= 10) {
            for(int s = 0; s < samplesize; s++) {
                GraphI gmtrx = new GraphMatrix(size);
                GraphI glist = new GraphList();

                Node[] nodesGmtrx = new Node[size];
                Node[] nodesGlist = new Node[size];

                // create nodes and put them in graph
                for (int i = 0; i < size; i++) {
                    Node n1 = new Node(i);
                    nodesGmtrx[i] = n1;
                    gmtrx.addNode(n1);

                    Node n2 = new Node(i);
                    nodesGlist[i] = n2;
                    glist.addNode(n2);
                }

                // create an edge for every node
                for (int i = 0; i < size; i++) {
                    Random rnd = new Random();
                    int rndNode = rnd.nextInt(size - 1);
                    double rndWeight = rnd.nextDouble() * size;

                    gmtrx.addNeighbour(nodesGmtrx[i], nodesGmtrx[rndNode], rndWeight);
                    glist.addNeighbour(nodesGlist[i], nodesGlist[rndNode], rndWeight);
                }

                // create completely random edges
                for (int i = 0; i < size; i++) {
                    Random rnd = new Random();
                    int rndNode1 = rnd.nextInt(size - 1);
                    int rndNode2 = rnd.nextInt(size - 1);
                    double rndWeight = rnd.nextDouble() * size;

                    gmtrx.addNeighbour(nodesGmtrx[rndNode1], nodesGmtrx[rndNode2], rndWeight);
                    glist.addNeighbour(nodesGlist[rndNode1], nodesGlist[rndNode2], rndWeight);
                }

                Counter counter = new Counter();

                Dykstra.findRoute(gmtrx, nodesGmtrx[0], counter);
                counterMatrix += counter.getCount();
                counter.resetCount();

                Dykstra.findRoute(glist, nodesGlist[0], counter);
                counterList += counter.getCount();
                counter.resetCount();
            }
            System.out.println("Size = " + size);
            System.out.println("CounterMatrix = " + (counterMatrix/samplesize));
            System.out.println("CounterList   = " + (counterList/samplesize));
            System.out.println();
        }
    }
}
