package graphDykstra;

public class GraphList extends GraphA implements GraphI {
    public GraphList() {}

    @Override
    public double getWeight(Node a, Node b) {
        return 0;
    }

    @Override
    public void addWeight(double weight, Node a, Node b) {

    }
}