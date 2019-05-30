package graphDykstra;

import java.util.PriorityQueue;
import java.util.Queue;

public class Dykstra {
    private static long counter = 0;

    public static void findRoute(GraphI graph, Node start, boolean useCounter) {
        Queue<Node> rand = new PriorityQueue<>();

        // 1
        // init every node
        for(Node n : graph.getNodes()) {
            if (useCounter) {
                counter++;
            }
            n.setPred(null);
            n.setCost(Double.MAX_VALUE);
            n.setMarked(false);
        }

        // init start node
        start.setPred(start);
        start.setCost(0.0);
        start.setMarked(true);

        // 2
        // fill rand with adjacence nodes of start and init the neighbour nodes of start //TODO:
        for(Node n : graph.getNeighbours(start)) {
            if (useCounter) {
                counter++;
            }
            n.setCost(graph.getWeight(start, n));
            n.setPred(start);
            rand.add(n);
        }

        // 3
        // process every node starting with the lowest cost one (automatically, since this is a prioriry queue, sorted after the cost
        while(!rand.isEmpty()) {
            if (useCounter) {
                counter++;
            }
            Node v = rand.poll();
            v.setMarked(true);

            // process every node in rand that is not marked
            for(Node k : graph.getNeighbours(v)) {
                if (useCounter) {
                    counter++;
                }
                // 3.1
                if (!k.getMarked()) {
                    if (useCounter) {
                        counter++;
                    }
                    // 3.2
                    double VKCost = v.getCost() + graph.getWeight(v, k);
                    if(k.getCost() > VKCost) { // check if the cost to go to this node via the previous node is faster than currently known
                        if (useCounter) {
                            counter++;
                        }
                        k.setCost(VKCost);
                        k.setPred(v);
                    }
                    // 3.3
                    rand.add(k);
                }
            }
        }
    }

    public static long getCounter() {
        return counter;
    }

    public static void resetCounter() {
        counter = 0;
    }
}
