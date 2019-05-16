package test;

import fasterQuicksort.FasterQuicksort;
import fasterQuicksort.FasterQuicksortI;
import fasterQuicksort.Node;
import fasterQuicksort.RadixSort;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class JUnitTestFrameRadixSort {
    final private int timelimit = 1000;
    @Test(timeout = timelimit)
    public void testTen() {
        int size = 10;
        RadixSort rs = new RadixSort();
        Node[] sortedList = new Node[size];
        for (int i = 0; i < size; i++) {
            sortedList[i] = new Node(size - 1 - i, size - 1 - i);
        }
        rs.sort(sortedList);

        for (int i = 0; i < sortedList.length; i++) {
            assertEquals(sortedList[i].getKey(), i);
        }
    }
}
