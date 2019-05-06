package test;

import org.junit.Test;
import quicksort.Node;
import quicksort.QuickersortI;
import quicksort.Quickersort;

import static junit.framework.TestCase.assertEquals;

public class JUnitTestFrameQuickersort {
    final private int timelimit = 1_000_000_000;

    @Test(timeout = timelimit)
    public void testOne() {
        QuickersortI qs = new Quickersort();
        Node[] sortedList = {new Node(0, 0)};
        qs.sort(sortedList, 0, sortedList.length-1);

        for (int i = 0; i < sortedList.length; i++) {
            assertEquals(sortedList[i].getKey(), i);
        }
    }

    @Test(timeout = timelimit)
    public void testThree() {
        int size = 3;
        QuickersortI qs = new Quickersort();
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
    public void testTen() {
        int size = 10;
        QuickersortI qs = new Quickersort();
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
    public void testEleven() {
        int size = 11;
        QuickersortI qs = new Quickersort();
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
    public void testOnehundred() {
        int size = 100;
        QuickersortI qs = new Quickersort();
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
