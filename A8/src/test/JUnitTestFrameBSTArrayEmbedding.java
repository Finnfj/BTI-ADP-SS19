package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import binarySearchTree.BinarySearchTreeArrayEmbedding;
import binarySearchTree.BinarySearchTreeI;
import binarySearchTree.BinarySearchTreeNodeLinking;
import binarySearchTree.PrintVariant;

public class JUnitTestFrameBSTArrayEmbedding {
    public static void main(String... args) {
        testTwo();
    }

    private static void testOne() {
        BinarySearchTreeI bstae = new BinarySearchTreeArrayEmbedding(10);
        bstae.addData(1);
        bstae.addData(3);
        bstae.addData(1);
        bstae.addData(0);
        bstae.addData(2);
        bstae.addData(4);
        bstae.addData(6);
        bstae.addData(8);
        bstae.addData(5);
        bstae.updateSum();
        System.out.println(bstae.getSum(0, 8));
        bstae.printTree(PrintVariant.INORDER);
        bstae.printTree(PrintVariant.PREORDER);
        bstae.printTree(PrintVariant.POSTORDER);
    }

    private static void testTwo() {
        for (int i = 10; i <= 1_000_000; i *= 10) {
            BinarySearchTreeI bstae = new BinarySearchTreeArrayEmbedding(28);
            long testSum = 0;
            List<Integer> keymap = IntStream.range(0, i).boxed().collect(Collectors.toCollection(ArrayList<Integer>::new));
            Collections.shuffle(keymap);
            System.out.println("List size: " + i + "-------------");
            for (int key : keymap) {
            	bstae.addData(key);
                testSum += key;
            }
            long startTime = System.currentTimeMillis();
            bstae.updateSum();
            long sum = bstae.getSum(0, 10 * i);
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            System.out.println("testSum = " + testSum + "\nsum = " + sum + "\ntime = " + time + " ms");
        }
    }

    private static void testThree() {
        BinarySearchTreeI bstae = new BinarySearchTreeArrayEmbedding(10);
//        bstae.addData("Hallo");
//        bstae.addData("Welto");
//        bstae.addData(12345);
//        bstae.addData(98765);
//        bstae.addData("Bratw");
        bstae.printTree(PrintVariant.INORDER);
        bstae.printTree(PrintVariant.PREORDER);
        bstae.printTree(PrintVariant.POSTORDER);
    }
}
