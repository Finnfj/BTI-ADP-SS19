package quicksort;

import java.util.ArrayList;
import java.util.List;

public class QuicksortPS implements Quicksort {
    public QuicksortPS() {}

    @Override
    public Node[] sort(Node[] list, Pivot pivot) {
        if (list == null || list.length <= 1) {
            return list;
        }

        // select Pivot
        int pivotKey = -1;
        switch (pivot) {
            case RIGHT:
                pivotKey = list[list.length-1].getKey();
                break;
            case MEDIANOFTHREE:
                pivotKey = (list[0].getKey() + list[list.length/2].getKey() + list[list.length-1].getKey()) / 3;
                break;
            case RANDOM:
                double index = Math.random() * list.length;
                pivotKey = list[(int) index].getKey();
                break;
        }

        // split list in left and right list
        List<Node> listLeft = new ArrayList<>();
        List<Node> listRight = new ArrayList<>();
        for (Node n : list) {
            if (n.getKey() <= pivotKey) {
                listLeft.add(n);
            } else {
                listRight.add(n);
            }
        }
        // sort the split lists
        Node[] listLeftSorted = sort(listLeft.toArray(new Node[listLeft.size()]), pivot);
        Node[] listRightSorted = sort(listRight.toArray(new Node[listRight.size()]), pivot);
        Node[] listSorted = new Node[listLeft.size() + listRight.size()];

        // merge the sorted lists
        int count = 0;
        for(Node n : listLeftSorted) {
            listSorted[count++] = n;
        }
        for(Node n : listRightSorted) {
            listSorted[count++] = n;
        }

        return listSorted;
    }
}