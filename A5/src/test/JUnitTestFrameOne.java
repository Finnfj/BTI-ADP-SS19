package test;

import org.junit.Test;
import quicksort.Node;
import quicksort.PivotType;
import quicksort.Quicksort;
import quicksort.QuicksortOne;

import static junit.framework.TestCase.assertEquals;

public class JUnitTestFrameOne {
    final private int timelimit = 1_000_000_000;

    @Test(timeout = timelimit)
    public void testsOneMOT() {
        Quicksort qs = new QuicksortOne();
        Node[] sortedList = {new Node(0, 0)};
        qs.sort(sortedList, 0, sortedList.length-1, PivotType.MEDIANOFTHREE);

        for (int i = 0; i < sortedList.length; i++) {
            assertEquals(sortedList[i].getKey(), i);
        }
    }

    @Test(timeout = timelimit)
    public void testsThreeMOT() {
        int size = 3;
        Quicksort qs = new QuicksortOne();
        Node[] sortedList = new Node[size];
        for (int i = 0; i < size; i++) {
            sortedList[i] = new Node(size - 1 - i, size - 1 - i);
        }
        qs.sort(sortedList, PivotType.MEDIANOFTHREE);

        for (int i = 0; i < sortedList.length; i++) {
            assertEquals(sortedList[i].getKey(), i);
        }
    }

    @Test(timeout = timelimit)
    public void testsTenMOT() {
        int size = 10;
        Quicksort qs = new QuicksortOne();
        Node[] sortedList = new Node[size];
        for (int i = 0; i < size; i++) {
            sortedList[i] = new Node(size - 1 - i, size - 1 - i);
        }
        qs.sort(sortedList, PivotType.MEDIANOFTHREE);

        for (int i = 0; i < sortedList.length; i++) {
            assertEquals(sortedList[i].getKey(), i);
        }
    }

    @Test(timeout = timelimit)
    public void testsOneRight() {
        Quicksort qs = new QuicksortOne();
        Node[] sortedList = {new Node(0, 0)};
        qs.sort(sortedList, PivotType.RIGHT);

        for (int i = 0; i < sortedList.length; i++) {
            assertEquals(sortedList[i].getKey(), i);
        }
    }

    @Test(timeout = timelimit)
    public void testsThreeRight() {
        int size = 3;
        Quicksort qs = new QuicksortOne();
        Node[] sortedList = new Node[size];
        for (int i = 0; i < size; i++) {
            sortedList[i] = new Node(size - 1 - i, size - 1 - i);
        }
        qs.sort(sortedList, PivotType.RIGHT);

        for (int i = 0; i < sortedList.length; i++) {
            assertEquals(sortedList[i].getKey(), i);
        }
    }

    @Test(timeout = timelimit)
    public void testsTenRight() {
        int size = 10;
        Quicksort qs = new QuicksortOne();
        Node[] sortedList = new Node[size];
        for (int i = 0; i < size; i++) {
            sortedList[i] = new Node(size - 1 - i, size - 1 - i);
        }
        qs.sort(sortedList, PivotType.RIGHT);

        for (int i = 0; i < sortedList.length; i++) {
            assertEquals(sortedList[i].getKey(), i);
        }
    }

    @Test(timeout = timelimit)
    public void testsOneRandom() {
        Quicksort qs = new QuicksortOne();
        Node[] sortedList = {new Node(0, 0)};
        qs.sort(sortedList, PivotType.RANDOM);

        for (int i = 0; i < sortedList.length; i++) {
            assertEquals(sortedList[i].getKey(), i);
        }
    }

    @Test(timeout = timelimit)
    public void testsThreeRandom() {
        int size = 3;
        Quicksort qs = new QuicksortOne();
        Node[] sortedList = new Node[size];
        for (int i = 0; i < size; i++) {
            sortedList[i] = new Node(size - 1 - i, size - 1 - i);
        }
        qs.sort(sortedList, PivotType.RANDOM);

        for (int i = 0; i < sortedList.length; i++) {
            assertEquals(sortedList[i].getKey(), i);
        }
    }

    @Test(timeout = timelimit)
    public void testsTenRandom() {
        int size = 10;
        Quicksort qs = new QuicksortOne();
        Node[] sortedList = new Node[size];
        for (int i = 0; i < size; i++) {
            sortedList[i] = new Node(size - 1 - i, size - 1 - i);
        }
        qs.sort(sortedList, PivotType.RANDOM);

        for (int i = 0; i < sortedList.length; i++) {
            assertEquals(sortedList[i].getKey(), i);
        }
    }
}
