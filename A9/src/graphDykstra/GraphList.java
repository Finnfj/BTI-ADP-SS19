package graphDykstra;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphList extends GraphA implements GraphI {
    private HashMap<Node, NeighbourListElem> neighbourList;

    public GraphList() {
        neighbourList = new HashMap<>();
    }

    @Override
    public void addNeighbour(Node n1, Node n2, double weight) {
        addSingleNeighbour(n1, n2, weight); // TODO: do this smarter by just doing this once
        addSingleNeighbour(n2, n1, weight);
    }

    // adds only n2 as neighbour of n1 and NOT vice versa
    private void addSingleNeighbour(Node n1, Node n2, double weight) {
        if(neighbourList.get(n1) == null) { // if n1 has no neighbour
            neighbourList.put(n1, new NeighbourListElem(n2, weight));
        } else {
            NeighbourListElem worker = neighbourList.get(n1);
            while (worker.getNext() != null) { // go to the last listNode
                worker = worker.getNext();
            }
            worker.setNext(new NeighbourListElem(n2, weight));
        }
    }

    @Override
    public ArrayList<Node> getNeighbours(Node node) {
        ArrayList<Node> ret = new ArrayList<>();

        if(neighbourList.get(node) != null) { // only if a has neighbours
            NeighbourListElem worker = neighbourList.get(node);
            while (worker != null) { // go through the whole list
                ret.add(worker.getNode());
                worker = worker.getNext();
            }
        }
        return ret;
    }

    @Override
    public double getWeight(Node n1, Node n2) {
        if(neighbourList.get(n1) != null) { // only if a has neighbours
            NeighbourListElem worker = neighbourList.get(n1);
            while (worker != null) { // go through the whole list
                if(worker.getNode() == n2) {
                    return worker.getCost();
                }
                worker = worker.getNext();
            }
        }
        throw new IllegalArgumentException("a and b are not neighbours");
    }
}