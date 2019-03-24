package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import set.*;
import types.*;

public class JUnitTestFrame {
    final private int timelimit = 600_000; // ms

    @Test(timeout = timelimit)
    public void testSetArray() {
        SetInterface sa = new SetArray();
        Elem<Integer> testElement = new Elem(new Integer(5));
        Pos testPos = sa.add(testElement);
        int testKey = testElement.key;

        assertEquals(1, sa.size());
        sa.showall();
        assertEquals(testPos, sa.find(testKey));
        assertEquals(testElement, sa.retrieve(testPos));

        sa.delete(testKey);
        assertEquals(0, sa.size());

        Pos testPos2 = sa.add(testElement);
        assertEquals(1, sa.size());

        sa.delete(testPos2);
        assertEquals(0, sa.size());
    }
}
