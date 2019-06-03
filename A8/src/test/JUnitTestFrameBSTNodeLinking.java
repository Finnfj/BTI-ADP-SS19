package test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import binarySearchTree.BinarySearchTreeI;
import binarySearchTree.BinarySearchTreeNodeLinking;
import binarySearchTree.PrintVariant;

public class JUnitTestFrameBSTNodeLinking {
    public static void main(String... args) {
        testTwo();
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
    
    private static void testTwo() {
    	long timeA[] = {0,0,0,0,0,0,0};
    	for (int j = 0; j < 10; j++) {
    		int z = 0;
	        for (int i = 10; i <= 1_000_0000; i *= 10) {
	            BinarySearchTreeI bstnl = new BinarySearchTreeNodeLinking();
	            long testSum = 0;
	            List<Integer> keymap = IntStream.range(0, i).boxed().collect(Collectors.toCollection(ArrayList<Integer>::new));
	            Collections.shuffle(keymap);
	            System.out.println("List size: " + i + "-------------");
	            for (int key : keymap) {
	                bstnl.addData(key);
	                testSum += key;
	            }
	            long startTime = System.currentTimeMillis();
	            bstnl.updateSum();
	            long sum = bstnl.getSum(0, 10 * i);
	            long endTime = System.currentTimeMillis();
	            long time = endTime - startTime;
	            //System.out.println("testSum = " + testSum + "\nsum = " + sum + "\ntime = " + time + " ms");
	            timeA[z] += time;
	            z++;
	        }
    	}
    	for (int j = 0; j < 7; j++) {
    		timeA[j] = timeA[j] / 10;
        	System.out.println(timeA[j] + "ms");
    	}
    }
}
