package test;

import org.junit.Test;
import set.SetContainerArray;
import set.SetInterface;
import types.Elem;
import types.Pos;

import static org.junit.Assert.assertEquals;

public class JUnitTestFrameSetContainerArray {
    final private int timelimit = 600_000; // ms

    @Test(timeout = timelimit)
    public void testAddFindRetrieveDeleteWKeyDeleteWPos() {
        SetInterface sa = new SetContainerArray();
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
        SetInterface sa1 = new SetContainerArray();
        SetInterface sa2 = new SetContainerArray();

        sa1.add(new Elem("a"));
        sa2.add(new Elem("b"));

        SetContainerArray sa3 = SetContainerArray.unify(sa1, sa2);

        assertEquals(2, sa3.size());
    }

    @Test(timeout = timelimit)
    public void testArraySizeIncrease() {
        SetInterface sa = new SetContainerArray();
        for (int i = 0; i < 10; i++) {
            sa.add(new Elem((char) i));
        }

        assertEquals(10, sa.size());
    }

    @Test(timeout = timelimit)
    public void testDeleteTwice() {
        SetInterface sa = new SetContainerArray();
        Elem<Integer> testElement = new Elem(new Integer(5));
        Pos testPos = sa.add(testElement);
        sa.delete(testPos);
        assertEquals(0, sa.size());
        sa.delete(testPos);
        assertEquals(0, sa.size());
    }
}
