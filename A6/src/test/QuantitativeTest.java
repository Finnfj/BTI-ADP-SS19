package test;

import fasterQuicksort.*;
import quicksort.PivotType;
import quicksort.Quicksort;
import quicksort.QuicksortI;

public class QuantitativeTest {
    public static void main(String... args) {
        FasterQuicksort fqs = new FasterQuicksort();
        QuicksortI qs = new Quicksort();

        for (int i = 10; i <= 1_000_000; i *= 10) {
            System.out.println("List size: " + i + "-------------");
            Node[] unsortedListOne = new Node[i];
            Node[] unsortedListTwo = new Node[i];
            for (int j = 0; j < i; j++) {
                double rnd = Math.random() * 100;
                int num = (int) rnd + 700;
                int key = num * i;
                unsortedListOne[j] = new Node(key, key);
                unsortedListTwo[j] = new Node(key, key);
            }
            long startTime = System.currentTimeMillis();
            fqs.sort(unsortedListOne);
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            System.out.println("FastQuicksort: " + fqs.getCounter() + "\n" + time + " ms");
            fqs.resetCounter();

            startTime = System.currentTimeMillis();
            qs.sort(unsortedListTwo, PivotType.MEDIANOFTHREE);
            endTime = System.currentTimeMillis();
            time = endTime - startTime;
            System.out.println("Quicksort:     " + qs.getCounter() + "\n" + time + " ms");
            qs.resetCounter();
        }
    }
}
