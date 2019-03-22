package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import set.*;
import types.*;

public class JUnitTestFrame {
    final private int timelimit = 5_000; // ms

    @Test(timeout = timelimit)
    public void testAddSetArray() {
        SetInterface sa = new SetArray();
        Elem<Integer> e = new Elem(new Integer(5));
        Pos p = sa.add(e);

        assertEquals(1, sa.size());
    }
}
