package fasterQuicksort;

import java.util.ArrayList;

import static fasterQuicksort.BucketLists.numA;
import static fasterQuicksort.BucketLists.numB;

public class RadixSort {
    public void sort(Node[] list) {
        // iniziali
        ArrayList[] bucketsA = new ArrayList[10];
        for(int i = 0; i < 10; i++) {
            bucketsA[i] = new ArrayList<Node>();
        }

        ArrayList[] bucketsB = new ArrayList[10];
        for(int i = 0; i < 10; i++) {
            bucketsB[i] = new ArrayList<Node>();
        }

        int modNum = 10;
        int oldModNum = 10;
        BucketLists bl;
        for(Node n : list) {
            bucketsA[n.getKey() % modNum].add(n);
        }
        oldModNum = modNum;
        modNum *= 10;
        bl = numA;

        if (!(String.valueOf(modNum).length() > String.valueOf(list[0].getKey()).length() * 10)) {
            theLoop:
            while (true) {
                if (String.valueOf(modNum).length() > String.valueOf(list[0].getKey()).length() * 10) {
                    bl = numB;
                    break theLoop;
                }
                transfer(bucketsA, bucketsB, modNum, oldModNum);
                oldModNum = modNum;
                modNum *= 10;
                if (String.valueOf(modNum).length() > String.valueOf(list[0].getKey()).length() * 10) {
                    bl = numA;
                    break theLoop;
                }
                transfer(bucketsB, bucketsA, modNum, oldModNum);
                oldModNum = modNum;
                modNum *= 10;
            }
        }

        int i = 0;
        switch (bl) {
            case numA:
                for(ArrayList<Node> al : bucketsA) {
                    for(Node n : al) {
                        list[i++] = n;
                    }
                }
                break;
            case numB:
                for(ArrayList<Node> al : bucketsB) {
                    for(Node n : al) {
                        list[i++] = n;
                    }
                }
                break;
        }
    }

    private void transfer(ArrayList[] a, ArrayList[] b, int modNum, int oldModNum) {
        for(ArrayList<Node> al : a) {
            for(Node n : al) {
                b[n.getKey() % modNum / oldModNum].add(n);
            }
        }
    }
}
