package graphDykstra;

import java.util.ArrayList;

public class GraphMatrix extends GraphA implements GraphI {
    private double[][] adjacenceMatrix;
    private int size;

    public GraphMatrix(int size) {
        this.size = size;

        adjacenceMatrix = new double[size][];
        for(int i = 0; i < size; i++) {
            adjacenceMatrix[i] = new double[size];
        }
    }

    @Override
    public void addNeighbour(Node n1, Node n2, double weight) {
        adjacenceMatrix[n1.getId()][n2.getId()] = weight;
        adjacenceMatrix[n2.getId()][n1.getId()] = weight;
    }

    @Override
    public ArrayList<Node> getNeighbours(Node node) {
        ArrayList<Node> ret = new ArrayList<>();

        for(int i = 0; i < size; i++) {
            // if the weight is over 0, they are neighbours, find them via their unique id in the nodes arraylist
            if(adjacenceMatrix[node.getId()][i] > 0.0) {
                ret.add(getNodes().get(getNodes().indexOf(new Node(i))));
            }
        }
        return ret;
    }

    @Override
    public double getWeight(Node n1, Node n2) {
        return adjacenceMatrix[n1.getId()][n2.getId()];
    }
}