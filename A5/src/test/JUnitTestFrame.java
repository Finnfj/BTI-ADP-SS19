package test;

import org.junit.Test;
import quicksort.Node;
import quicksort.Pivot;
import quicksort.Quicksort;
import quicksort.QuicksortPS;

import static junit.framework.TestCase.assertEquals;

public class JUnitTestFrame {
    final private int timelimit = 1_000;

    @Test(timeout = timelimit)
    public void testsNullMOT() {
        Quicksort qs = new QuicksortPS();
        Node[] sortedList = qs.sort(null, Pivot.MEDIANOFTHREE);
        assertEquals(null, sortedList);
    }

    @Test(timeout = timelimit)
    public void testsOneMOT() {
        Quicksort qs = new QuicksortPS();
        Node[] unsortedList = {new Node(0, 0)};
        Node[] sortedList = qs.sort(unsortedList, Pivot.MEDIANOFTHREE);

        for (int i = 0; i < unsortedList.length; i++) {
            assertEquals(sortedList[i].getKey(), i);
        }
    }

    @Test(timeout = timelimit)
    public void testsThreeMOT() {
        int size = 3;
        Quicksort qs = new QuicksortPS();
        Node[] unsortedList = new Node[size];
        for (int i = 0; i < size; i++) {
            unsortedList[i] = new Node(size - 1 - i, size - 1 - i);
        }
        Node[] sortedList = qs.sort(unsortedList, Pivot.MEDIANOFTHREE);

        for (int i = 0; i < unsortedList.length; i++) {
            assertEquals(sortedList[i].getKey(), i);
        }
    }

    @Test(timeout = timelimit)
    public void testsTenMOT() {
        int size = 10;
        Quicksort qs = new QuicksortPS();
        Node[] unsortedList = new Node[size];
        for (int i = 0; i < size; i++) {
            unsortedList[i] = new Node(size - 1 - i, size - 1 - i);
        }
        Node[] sortedList = qs.sort(unsortedList, Pivot.MEDIANOFTHREE);

        for (int i = 0; i < unsortedList.length; i++) {
            assertEquals(sortedList[i].getKey(), i);
        }
    }

    @Test(timeout = timelimit)
    public void testsNullRight() {
        Quicksort qs = new QuicksortPS();
        Node[] sortedList = qs.sort(null, Pivot.RIGHT);
        assertEquals(null, sortedList);
    }

    @Test(timeout = timelimit)
    public void testsOneRight() {
        Quicksort qs = new QuicksortPS();
        Node[] unsortedList = {new Node(0, 0)};
        Node[] sortedList = qs.sort(unsortedList, Pivot.RIGHT);

        for (int i = 0; i < unsortedList.length; i++) {
            assertEquals(sortedList[i].getKey(), i);
        }
    }

    @Test(timeout = timelimit)
    public void testsThreeRight() {
        int size = 3;
        Quicksort qs = new QuicksortPS();
        Node[] unsortedList = new Node[size];
        for (int i = 0; i < size; i++) {
            unsortedList[i] = new Node(size - 1 - i, size - 1 - i);
        }
        Node[] sortedList = qs.sort(unsortedList, Pivot.RIGHT);

        for (int i = 0; i < unsortedList.length; i++) {
            assertEquals(sortedList[i].getKey(), i);
        }
    }

    @Test(timeout = timelimit)
    public void testsTenRight() {
        int size = 10;
        Quicksort qs = new QuicksortPS();
        Node[] unsortedList = new Node[size];
        for (int i = 0; i < size; i++) {
            unsortedList[i] = new Node(size - 1 - i, size - 1 - i);
        }
        Node[] sortedList = qs.sort(unsortedList, Pivot.RIGHT);

        for (int i = 0; i < unsortedList.length; i++) {
            assertEquals(sortedList[i].getKey(), i);
        }
    }

    @Test(timeout = timelimit)
    public void testsNullRandom() {
        Quicksort qs = new QuicksortPS();
        Node[] sortedList = qs.sort(null, Pivot.RANDOM);
        assertEquals(null, sortedList);
    }

    @Test(timeout = timelimit)
    public void testsOneRandom() {
        Quicksort qs = new QuicksortPS();
        Node[] unsortedList = {new Node(0, 0)};
        Node[] sortedList = qs.sort(unsortedList, Pivot.RANDOM);

        for (int i = 0; i < unsortedList.length; i++) {
            assertEquals(sortedList[i].getKey(), i);
        }
    }

    @Test(timeout = timelimit)
    public void testsThreeRandom() {
        int size = 3;
        Quicksort qs = new QuicksortPS();
        Node[] unsortedList = new Node[size];
        for (int i = 0; i < size; i++) {
            unsortedList[i] = new Node(size - 1 - i, size - 1 - i);
        }
        Node[] sortedList = qs.sort(unsortedList, Pivot.RANDOM);

        for (int i = 0; i < unsortedList.length; i++) {
            assertEquals(sortedList[i].getKey(), i);
        }
    }

    @Test(timeout = timelimit)
    public void testsTenRandom() {
        int size = 10;
        Quicksort qs = new QuicksortPS();
        Node[] unsortedList = new Node[size];
        for (int i = 0; i < size; i++) {
            unsortedList[i] = new Node(size - 1 - i, size - 1 - i);
        }
        Node[] sortedList = qs.sort(unsortedList, Pivot.RANDOM);

        for (int i = 0; i < unsortedList.length; i++) {
            assertEquals(sortedList[i].getKey(), i);
        }
    }
}
