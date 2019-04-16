package test;

import quicksort.Node;
import quicksort.PivotType;
import quicksort.Quicksort;
import quicksort.QuicksortPS;

public class QuantitativeTest {
    public static void main(String... args) {
        Quicksort qs = new QuicksortPS();
        PivotType[] pivotTypes = {PivotType.MEDIANOFTHREE, PivotType.RANDOM, PivotType.RIGHT};

        System.out.println("---Best Case---------------------------------------");
        for (int i = 1; i <= 100000; i *= 10) {
            System.out.println("List size: " + i + "-------------");
            for (PivotType p: pivotTypes) {
                Node[] unsortedList = new Node[i];
                for (int j = 0; j < i; j++) {
                    unsortedList[j] = new Node(j, j);
                }
                qs.sort(unsortedList, p);
                System.out.println("PivotType: " + p + " = " + qs.getCounter());
                qs.resetCounter();
            }
        }

        /*
        System.out.println("---Average Case---------------------------------------");
        for (int i = 1; i <= 100000; i *= 10) {
            System.out.println("List size: " + i + "-------------");
            for (PivotType p: pivotTypes) {
                Node[] unsortedList = new Node[i];
                for (int j = 0; j < i; j++) {
                    unsortedList[j] = new Node(i - 1 - j, i - 1 - j);
                }
                qs.sort(unsortedList, p);
                System.out.println("PivotType: " + p + " = " + qs.getCounter());
                qs.resetCounter();
            }
        }*/

        System.out.println("---Worst Case---------------------------------------");
        for (int i = 1; i <= 100000; i *= 10) {
            System.out.println("List size: " + i + "-------------");
            for (PivotType p: pivotTypes) {
                Node[] unsortedList = new Node[i];
                for (int j = 0; j < i; j++) {
                    unsortedList[j] = new Node(i - 1 - j, i - 1 - j);
                }
                qs.sort(unsortedList, p);
                System.out.println("PivotType: " + p + " = " + qs.getCounter());
                qs.resetCounter();
            }
        }
    }
}
