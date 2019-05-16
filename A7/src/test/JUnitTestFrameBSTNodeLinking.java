package test;

import binarySearchTree.BinarySearchTreeI;
import binarySearchTree.BinarySerachTreeNodeLinking;
import binarySearchTree.PrintVariant;

public class JUnitTestFrameBSTNodeLinking {
    public static void main(String... args) {
        testThree();
    }

    private static void testOne() {
        BinarySearchTreeI bstnl = new BinarySerachTreeNodeLinking();
        bstnl.addData(1);
        bstnl.addData(3);
        bstnl.addData(1);
        bstnl.addData(0);
        bstnl.addData(2);
        bstnl.addData(4);
        bstnl.printTree(PrintVariant.INORDER);
        bstnl.printTree(PrintVariant.PREORDER);
        bstnl.printTree(PrintVariant.POSTORDER);
    }

    private static void testTwo() {
        BinarySearchTreeI bstnl = new BinarySerachTreeNodeLinking();
        bstnl.addData("Hallo");
        bstnl.addData("Welto");
        bstnl.addData("Hanso");
        bstnl.addData("Peter");
        bstnl.addData("Bratw");
        bstnl.printTree(PrintVariant.INORDER);
        bstnl.printTree(PrintVariant.PREORDER);
        bstnl.printTree(PrintVariant.POSTORDER);
    }

    private static void testThree() {
        BinarySearchTreeI bstnl = new BinarySerachTreeNodeLinking();
        bstnl.addData("Hallo");
        bstnl.addData("Welto");
        bstnl.addData(12345);
        bstnl.addData(98765);
        bstnl.addData("Bratw");
        bstnl.printTree(PrintVariant.INORDER);
        bstnl.printTree(PrintVariant.PREORDER);
        bstnl.printTree(PrintVariant.POSTORDER);
    }
}
