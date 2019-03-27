package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import set.*;
import types.*;

public class JUnitTestFrameSetArray {
    final private int timelimit = 600_000; // ms

    @Test(timeout = timelimit)
    public void testAddFindRetrieveDeleteWKeyDeleteWPos() {
        SetInterface sa = new SetArray();
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
        SetInterface sa1 = new SetArray();
        SetInterface sa2 = new SetArray();

        sa1.add(new Elem("a"));
        sa2.add(new Elem("b"));

        SetArray sa3 = SetArray.unify(sa1, sa2);

        assertEquals(2, sa3.size());
    }

    @Test(timeout = timelimit)
    public void testArraySizeIncrease() {
        SetInterface sa = new SetArray();
        for (int i = 0; i < 10; i++) {
            sa.add(new Elem((char) i));
        }

        assertEquals(10, sa.size());
    }

    @Test(timeout = timelimit)
    public void testDeleteTwice() {
        SetInterface sa = new SetArray();
        Elem<Integer> testElement = new Elem(new Integer(5));
        Pos testPos = sa.add(testElement);
        sa.delete(testPos);
        assertEquals(0, sa.size());
        sa.delete(testPos);
        assertEquals(0, sa.size());
    }

    @Test(timeout = timelimit)
    public void testAddTwice() {
        SetInterface sa = new SetArray<>();
        Elem<Integer> testElement = new Elem(new Integer(5));
        sa.add(testElement);
        assertEquals(1, sa.size());
        sa.add(testElement);
        assertEquals(1, sa.size());
    }

    @Test(timeout = timelimit)
    public void testAdd10() {
        SetInterface sa = new SetArray<>();
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
    }
}
