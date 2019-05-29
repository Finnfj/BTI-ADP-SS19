package graphDykstra;

public class GraphMatrix extends GraphA implements GraphI {
    private double[][] adjacenceMatrix;

    public GraphMatrix(int size) {
        adjacenceMatrix = new double[size][];
        for(int i = 0; i < size; i++) {
            adjacenceMatrix[i] = new double[size];
        }
    }

    @Override
    public double getWeight(Node a, Node b) {
        return adjacenceMatrix[a.getId()][b.getId()];
    }

    @Override
    public void addWeight(double weight, Node a, Node b) {
        adjacenceMatrix[a.getId()][b.getId()] = weight;
        adjacenceMatrix[b.getId()][a.getId()] = weight;
    }
}