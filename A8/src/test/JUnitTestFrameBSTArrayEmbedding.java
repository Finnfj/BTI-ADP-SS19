package test;

import binarySearchTree.BinarySearchTreeArrayEmbedding;
import binarySearchTree.BinarySearchTreeI;
import binarySearchTree.PrintVariant;

public class JUnitTestFrameBSTArrayEmbedding {
    public static void main(String... args) {
        testOne();
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
        BinarySearchTreeI bstae = new BinarySearchTreeArrayEmbedding(10);
//        bstae.addData("Hallo");
//        bstae.addData("Welto");
//        bstae.addData("Hanso");
//        bstae.addData("Peter");
//        bstae.addData("Bratw");
        bstae.printTree(PrintVariant.INORDER);
        bstae.printTree(PrintVariant.PREORDER);
        bstae.printTree(PrintVariant.POSTORDER);
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
