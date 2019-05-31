package test;

import binarySearchTree.BinarySearchTreeI;
import binarySearchTree.BinarySearchTreeNodeLinking;
import binarySearchTree.PrintVariant;

public class JUnitTestFrameBSTNodeLinking {
    public static void main(String... args) {
        testOne();
    }

    private static void testOne() {
        BinarySearchTreeI bstnl = new BinarySearchTreeNodeLinking();
        bstnl.addData(1);
        bstnl.addData(3);
        bstnl.addData(1);
        bstnl.addData(0);
        bstnl.addData(2);
        bstnl.addData(4);
        bstnl.addData(6);
        bstnl.addData(8);
        bstnl.addData(5);
        bstnl.updateSum();
        System.out.println(bstnl.getSum(5, 8));
        bstnl.printTree(PrintVariant.INORDER);
        bstnl.printTree(PrintVariant.PREORDER);
        bstnl.printTree(PrintVariant.POSTORDER);
    }
}
