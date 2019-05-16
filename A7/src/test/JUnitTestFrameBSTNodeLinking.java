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
}
