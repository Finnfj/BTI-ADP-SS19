package test;

import fasterQuicksort.FasterQuicksortI;
import org.junit.Test;
import fasterQuicksort.Node;
import fasterQuicksort.FasterQuicksort;

import static junit.framework.TestCase.assertEquals;

public class JUnitTestFrameFasterQuicksort {
    final private int timelimit = 1_000_000_000;

    @Test(timeout = timelimit)
    public void testOne() {
        FasterQuicksortI qs = new FasterQuicksort();
        Node[] sortedList = {new Node(0, 0)};
        qs.sort(sortedList, 0, sortedList.length-1);

        for (int i = 0; i < sortedList.length; i++) {
            assertEquals(sortedList[i].getKey(), i);
        }
    }

    @Test(timeout = timelimit)
    public void testThree() {
        int size = 3;
        FasterQuicksortI qs = new FasterQuicksort();
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
        FasterQuicksortI qs = new FasterQuicksort();
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
        FasterQuicksortI qs = new FasterQuicksort();
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
        FasterQuicksortI qs = new FasterQuicksort();
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
