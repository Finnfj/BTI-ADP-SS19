package test;

import binarySearchTree.BinarySearchTreeI;
import binarySearchTree.BinarySerachTreeNodeLinking;
import binarySearchTree.PrintVariant;

public class JUnitTestFrameBSTNodeLinking {
    public static void main(String... args) {
        testOne();
    }

    private static void testOne() {
        BinarySearchTreeI bstnl = new BinarySerachTreeNodeLinking();
        bstnl.printTree(PrintVariant.INORDER);
        bstnl.addData(1);
        bstnl.printTree(PrintVariant.INORDER);
        bstnl.addData(2);
        bstnl.printTree(PrintVariant.INORDER);
        bstnl.addData(1);
        bstnl.printTree(PrintVariant.INORDER);
        bstnl.addData(0);
        bstnl.printTree(PrintVariant.INORDER);
        bstnl.addData(3);
        bstnl.printTree(PrintVariant.INORDER);
    }
}
