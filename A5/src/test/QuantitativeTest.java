package test;

import quicksort.*;

public class QuantitativeTest {
    public static void main(String... args) {
        final int sampleSize = 1;
        Quicksort qs = new QuicksortOne();
        PivotType[] pivotTypes = {PivotType.MEDIANOFTHREE, PivotType.RANDOM, PivotType.RIGHT};

        System.out.println("---Best Case/Average Case---------------------------------------");
        for (int i = 1; i <= 100000; i *= 10) {
            System.out.println("List size: " + i + "-------------");
            for (PivotType p: pivotTypes) {
                long averageCount = 0;
                long time = 0;
                for(int k = 0; k < sampleSize; k++) {
                    Node[] unsortedList = new Node[i];
                    for (int j = 0; j < i; j++) {
                        double rndNum = Math.random() * Integer.MAX_VALUE;
                        unsortedList[j] = new Node((int) rndNum, (int) rndNum);
                    }
                    long startTime = System.nanoTime();
                    qs.sort(unsortedList, p);
                    long endTime = System.nanoTime();
                    time += endTime - startTime;
                    averageCount += qs.getCounter();
                    qs.resetCounter();
                }
                System.out.println(p + " =\n" + averageCount/sampleSize + "\n" + time/sampleSize + " ns");
            }
        }

        System.out.println("---Worst Case---------------------------------------");
        for (int i = 1; i <= 100000; i *= 10) {
            System.out.println("List size: " + i + "-------------");
            for (PivotType p: pivotTypes) {
                Node[] unsortedList = new Node[i];
                for (int j = 0; j < i; j++) {
                    unsortedList[j] = new Node(j, j);
                }
                long startTime = System.nanoTime();
                qs.sort(unsortedList, p);
                long endTime = System.nanoTime();
                long time = endTime - startTime;
                System.out.println(p + " =\n" + qs.getCounter() + "\n" + time + " ns");
                qs.resetCounter();
            }
        }
    }
}
