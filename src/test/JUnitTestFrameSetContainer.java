package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import set.*;
import types.*;

public class JUnitTestFrameSetContainer {
    final private int timelimit = 6_000; // ms

    @Test(timeout = timelimit)
    public void testAddFindRetrieveDeleteWKeyDeleteWPos() {
        SetContainer sa = new SetContainer();
        Elem<Integer> testElement = new Elem(new Integer(5));
        Pos testPos = sa.add(testElement);
        int testKey = testElement.key;

        assertEquals(1, sa.size());
        assertEquals(testPos, sa.find(testKey));
        assertEquals(testElement, sa.retrieve(testPos));

        sa.delete(testKey);
        assertEquals(0, sa.size());

        Pos testPos2 = sa.add(testElement);
        assertEquals(1, sa.size());

        sa.delete(testPos2);
        assertEquals(0, sa.size());
    }

    @Test(timeout = timelimit)
    public void testUnify() {
        SetContainer sa1 = new SetContainer();
        SetContainer sa2 = new SetContainer();

        sa1.add(new Elem("a"));
        sa2.add(new Elem("b"));

        SetContainer sa3 = SetContainer.unify(sa1, sa2);

        assertEquals(2, sa3.size());
    }

    @Test(timeout = timelimit)
    public void testArraySizeIncrease() {
        SetContainer sa = new SetContainer();
        for (int i = 0; i < 10; i++) {
            sa.add(new Elem((char) i));
        }

        assertEquals(10, sa.size());
    }

    // 1.2
    @Test(timeout = timelimit)
    public void testDeleteTwice() {
        SetContainer sa = new SetContainer();
        Elem<Integer> testElement = new Elem(new Integer(5));
        Pos testPos = sa.add(testElement);
        sa.delete(testPos);
        assertEquals(0, sa.size());
        sa.delete(testPos);
        assertEquals(0, sa.size());
    }

    // 1.2
    @Test(timeout = timelimit)
    public void testDeleteElementNotInSet() {
        SetContainer sa = new SetContainer();
        Elem<Integer> testElement = new Elem(new Integer(5));
        Pos p = new Pos("test", sa);
        Pos testPos = sa.add(testElement);
        sa.delete(p);
        assertEquals(1, sa.size());
    }

    @Test(timeout = timelimit)
    public void testAddTwice() {
        SetContainer sa = new SetContainer();
        Elem<Integer> testElement = new Elem(new Integer(5));
        sa.add(testElement);
        assertEquals(1, sa.size());
        sa.add(testElement);
        assertEquals(1, sa.size());
    }

    // 1.1.(a) and 1.(b)
    @Test(timeout = timelimit)
    public void testAdd10() {
        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[10];
        for (int i=0; i<elems.length; i++) {
            elems[i] = new Elem<>(i);
        }

        for (Elem e : elems) {
            sa.add(e);
        }

        for (Elem e : elems) {
            assertEquals(sa.retrieve(sa.find(e.key)), e);
        }

        assertEquals(10, sa.size());

        sa.add(elems[0]);
        assertEquals(sa.retrieve(sa.find(elems[0].key)), elems[0]);

        sa.delete(elems[0].key);
        assertEquals(9, sa.size());
    }

    // 1.1.(c)
    @Test(timeout = timelimit)
    public void testAdd1000() {
        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[1000];
        for (int i=0; i<elems.length; i++) {
            elems[i] = new Elem<>(i);
        }

        for (Elem e : elems) {
            sa.add(e);
        }

        for (Elem e : elems) {
            assertEquals(sa.retrieve(sa.find(e.key)), e);
        }

        assertEquals(1000, sa.size());
    }
    //--------------------------------------DeletePOS-------------------------------------------------------------------
    //--------------------------------------WorstCase--------------------------------------------------------------------
    // 2
    @Test(timeout = timelimit)
    public void testDeletePOS10WorstCase() {
        int amount = 10;
        int watchIdx = amount-1;
        Pos watchPos = null;
        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            if (i != watchIdx) {
                sa.add(elems[i]);
            } else {
                watchPos = sa.add(elems[i]);
            }
        }

        sa.delete(watchPos);
        System.out.println("testDeletePOS10WorstCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    // 2
    @Test(timeout = timelimit)
    public void testDeletePOS100WorstCase() {
        int amount = 100;
        int watchIdx = amount-1;
        Pos watchPos = null;
        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            if (i != watchIdx) {
                sa.add(elems[i]);
            } else {
                watchPos = sa.add(elems[i]);
            }
        }

        sa.delete(watchPos);
        System.out.println("testDeletePOS100WorstCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    // 2
    @Test(timeout = timelimit)
    public void testDeletePOS1000WorstCase() {
        int amount = 1000;
        int watchIdx = amount-1;
        Pos watchPos = null;
        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            if (i != watchIdx) {
                sa.add(elems[i]);
            } else {
                watchPos = sa.add(elems[i]);
            }
        }

        sa.delete(watchPos);
        System.out.println("testDeletePOS1000WorstCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    // 2
    @Test(timeout = timelimit)
    public void testDeletePOS10000WorstCase() {
        int amount = 10000;
        int watchIdx = amount-1;
        Pos watchPos = null;
        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            if (i != watchIdx) {
                sa.add(elems[i]);
            } else {
                watchPos = sa.add(elems[i]);
            }
        }

        sa.delete(watchPos);
        System.out.println("testDeletePOS10000WorstCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    // 2
    @Test(timeout = timelimit)
    public void testDeletePOS100000WorstCase() {
        int amount = 100000;
        int watchIdx = amount-1;
        Pos watchPos = null;
        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            if (i != watchIdx) {
                sa.add(elems[i]);
            } else {
                watchPos = sa.add(elems[i]);
            }
        }

        sa.delete(watchPos);
        System.out.println("testDeletePOS100000WorstCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    //--------------------------------------AverageCase-----------------------------------------------------------------
    // 2
    @Test(timeout = timelimit)
    public void testDeletePOS10AverageCase() {
        int amount = 10;
        int watchIdx = amount/2;
        Pos watchPos = null;
        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            if (i != watchIdx) {
                sa.add(elems[i]);
            } else {
                watchPos = sa.add(elems[i]);
            }
        }

        sa.delete(watchPos);
        System.out.println("testDeletePOS10AverageCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    // 2
    @Test(timeout = timelimit)
    public void testDeletePOS100AverageCase() {
        int amount = 100;
        int watchIdx = amount/2;
        Pos watchPos = null;
        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            if (i != watchIdx) {
                sa.add(elems[i]);
            } else {
                watchPos = sa.add(elems[i]);
            }
        }

        sa.delete(watchPos);
        System.out.println("testDeletePOS100AverageCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    // 2
    @Test(timeout = timelimit)
    public void testDeletePOS1000AverageCase() {
        int amount = 1000;
        int watchIdx = amount/2;
        Pos watchPos = null;
        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            if (i != watchIdx) {
                sa.add(elems[i]);
            } else {
                watchPos = sa.add(elems[i]);
            }
        }

        sa.delete(watchPos);
        System.out.println("testDeletePOS1000AverageCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    // 2
    @Test(timeout = timelimit)
    public void testDeletePOS10000AverageCase() {
        int amount = 10000;
        int watchIdx = amount/2;
        Pos watchPos = null;
        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            if (i != watchIdx) {
                sa.add(elems[i]);
            } else {
                watchPos = sa.add(elems[i]);
            }
        }

        sa.delete(watchPos);
        System.out.println("testDeletePOS10000AverageCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    // 2
    @Test(timeout = timelimit)
    public void testDeletePOS100000AverageCase() {
        int amount = 100000;
        int watchIdx = amount/2;
        Pos watchPos = null;
        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            if (i != watchIdx) {
                sa.add(elems[i]);
            } else {
                watchPos = sa.add(elems[i]);
            }
        }

        sa.delete(watchPos);
        System.out.println("testDeletePOS100000AverageCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    //-------------------------------------BestCase--------------------------------------------------------------------
    @Test(timeout = timelimit)
    public void testDeletePOS10BestCase() {
        int amount = 10;
        int watchIdx = 0;
        Pos watchPos = null;
        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            if (i != watchIdx) {
                sa.add(elems[i]);
            } else {
                watchPos = sa.add(elems[i]);
            }
        }

        sa.delete(watchPos);
        System.out.println("testDeletePOS10BestCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    // 2
    @Test(timeout = timelimit)
    public void testDeletePOS100BestCase() {
        int amount = 100;
        int watchIdx = 0;
        Pos watchPos = null;
        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            if (i != watchIdx) {
                sa.add(elems[i]);
            } else {
                watchPos = sa.add(elems[i]);
            }
        }

        sa.delete(watchPos);
        System.out.println("testDeletePOS100BestCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    // 2
    @Test(timeout = timelimit)
    public void testDeletePOS1000BestCase() {
        int amount = 1000;
        int watchIdx = 0;
        Pos watchPos = null;
        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            if (i != watchIdx) {
                sa.add(elems[i]);
            } else {
                watchPos = sa.add(elems[i]);
            }
        }

        sa.delete(watchPos);
        System.out.println("testDeletePOS1000BestCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    // 2
    @Test(timeout = timelimit)
    public void testDeletePOS10000BestCase() {
        int amount = 10000;
        int watchIdx = 0;
        Pos watchPos = null;
        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            if (i != watchIdx) {
                sa.add(elems[i]);
            } else {
                watchPos = sa.add(elems[i]);
            }
        }

        sa.delete(watchPos);
        System.out.println("testDeletePOS10000BestCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    // 2
    @Test(timeout = timelimit)
    public void testDeletePOS100000BestCase() {
        int amount = 100000;
        int watchIdx = 0;
        Pos watchPos = null;
        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            if (i != watchIdx) {
                sa.add(elems[i]);
            } else {
                watchPos = sa.add(elems[i]);
            }
        }

        sa.delete(watchPos);
        System.out.println("testDeletePOS100000BestCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    //--------------------------------------DeleteKEY-------------------------------------------------------------------
    //--------------------------------------WorstCase--------------------------------------------------------------------
    // 2
    @Test(timeout = timelimit)
    public void testDeleteKEY10WorstCase() {
        int amount = 10;
        int watchIdx = amount-1;

        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            sa.add(elems[i]);
        }

        sa.delete(elems[watchIdx].key);
        System.out.println("testDeleteKEY10WorstCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    // 2
    @Test(timeout = timelimit)
    public void testDeleteKEY100WorstCase() {
        int amount = 100;
        int watchIdx = amount-1;

        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            sa.add(elems[i]);
        }

        sa.delete(elems[watchIdx].key);
        System.out.println("testDeleteKEY100WorstCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    // 2
    @Test(timeout = timelimit)
    public void testDeleteKEY1000WorstCase() {
        int amount = 1000;
        int watchIdx = amount-1;

        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            sa.add(elems[i]);
        }

        sa.delete(elems[watchIdx].key);
        System.out.println("testDeleteKEY1000WorstCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    // 2
    @Test(timeout = timelimit)
    public void testDeleteKEY10000WorstCase() {
        int amount = 10000;
        int watchIdx = amount-1;

        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            sa.add(elems[i]);
        }

        sa.delete(elems[watchIdx].key);
        System.out.println("testDeleteKEY10000WorstCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    // 2
    @Test(timeout = timelimit)
    public void testDeleteKEY100000WorstCase() {
        int amount = 100000;
        int watchIdx = amount-1;

        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            sa.add(elems[i]);
        }

        sa.delete(elems[watchIdx].key);
        System.out.println("testDeleteKEY100000WorstCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    //-----------------------------------AverageCase--------------------------------------------------------------------
    // 2
    @Test(timeout = timelimit)
    public void testDeleteKEY10AverageCase() {
        int amount = 10;
        int watchIdx = amount/2;

        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            sa.add(elems[i]);
        }

        sa.delete(elems[watchIdx].key);
        System.out.println("testDeleteKEY10AverageCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    // 2
    @Test(timeout = timelimit)
    public void testDeleteKEY100AverageCase() {
        int amount = 100;
        int watchIdx = amount/2;

        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            sa.add(elems[i]);
        }

        sa.delete(elems[watchIdx].key);
        System.out.println("testDeleteKEY100AverageCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    // 2
    @Test(timeout = timelimit)
    public void testDeleteKEY1000AverageCase() {
        int amount = 1000;
        int watchIdx = amount/2;

        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            sa.add(elems[i]);
        }

        sa.delete(elems[watchIdx].key);
        System.out.println("testDeleteKEY1000AverageCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    // 2
    @Test(timeout = timelimit)
    public void testDeleteKEY10000AverageCase() {
        int amount = 10000;
        int watchIdx = amount/2;

        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            sa.add(elems[i]);
        }

        sa.delete(elems[watchIdx].key);
        System.out.println("testDeleteKEY10000AverageCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    // 2
    @Test(timeout = timelimit)
    public void testDeleteKEY100000AverageCase() {
        int amount = 100000;
        int watchIdx = amount/2;

        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            sa.add(elems[i]);
        }

        sa.delete(elems[watchIdx].key);
        System.out.println("testDeleteKEY100000AverageCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    //-----------------------------------BestCase--------------------------------------------------------------------
    // 2
    @Test(timeout = timelimit)
    public void testDeleteKEY10BestCase() {
        int amount = 10;
        int watchIdx = 0;

        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            sa.add(elems[i]);
        }

        sa.delete(elems[watchIdx].key);
        System.out.println("testDeleteKEY10BestCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    // 2
    @Test(timeout = timelimit)
    public void testDeleteKEY100BestCase() {
        int amount = 100;
        int watchIdx = 0;

        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            sa.add(elems[i]);
        }

        sa.delete(elems[watchIdx].key);
        System.out.println("testDeleteKEY100BestCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    // 2
    @Test(timeout = timelimit)
    public void testDeleteKEY1000BestCase() {
        int amount = 1000;
        int watchIdx = 0;

        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            sa.add(elems[i]);
        }

        sa.delete(elems[watchIdx].key);
        System.out.println("testDeleteKEY1000BestCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    // 2
    @Test(timeout = timelimit)
    public void testDeleteKEY10000BestCase() {
        int amount = 10000;
        int watchIdx = 0;

        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            sa.add(elems[i]);
        }

        sa.delete(elems[watchIdx].key);
        System.out.println("testDeleteKEY10000BestCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }

    // 2
    @Test(timeout = timelimit)
    public void testDeleteKEY100000BestCase() {
        int amount = 100000;
        int watchIdx = 0;

        SetContainer sa = new SetContainer();
        Elem<Integer>[] elems = new Elem[amount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new Elem<>(i, i);
        }

        for (int i = 0; i < elems.length; i++) {
            sa.add(elems[i]);
        }

        sa.delete(elems[watchIdx].key);
        System.out.println("testDeleteKEY100000BestCase" + ": " + sa.counter);
        assertEquals(amount-1, sa.size());
    }
}