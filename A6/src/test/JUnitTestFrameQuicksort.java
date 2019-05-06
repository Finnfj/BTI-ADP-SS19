package test;

import org.junit.Test;
import quicksort.Node;
import quicksort.QuicksortI;
import quicksort.Quicksort;

import static junit.framework.TestCase.assertEquals;

public class JUnitTestFrameQuicksort {
    final private int timelimit = 1_000_000_000;

    @Test(timeout = timelimit)
    public void testsOne() {
        QuicksortI qs = new Quicksort();
        Node[] sortedList = {new Node(0, 0)};
        qs.sort(sortedList, 0, sortedList.length-1);

        for (int i = 0; i < sortedList.length; i++) {
            assertEquals(sortedList[i].getKey(), i);
        }
    }

    @Test(timeout = timelimit)
    public void testsThree() {
        int size = 3;
        QuicksortI qs = new Quicksort();
        Node[] sortedList = new Node[size];
        for (int i = 0; i < size; i++) {
            sortedList[i] = new Node(size - 1 - i, size - 1 - i);
        }
        qs.sort(sortedList);

        for (int i = 0; i < sortedList.length; i++) {
            assertEquals(sortedList[i].getKey(), i);
        }
    }

    @Test(timeout = timelimit)
    public void testsTen() {
        int size = 10;
        QuicksortI qs = new Quicksort();
        Node[] sortedList = new Node[size];
        for (int i = 0; i < size; i++) {
            sortedList[i] = new Node(size - 1 - i, size - 1 - i);
        }
        qs.sort(sortedList);

        for (int i = 0; i < sortedList.length; i++) {
            assertEquals(sortedList[i].getKey(), i);
        }
    }
}
