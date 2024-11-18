package Exe.Ex4;

import junit.framework.TestCase;
import org.junit.Test;

public class Point2DTest extends TestCase {
    @Test
    public void testAdd() {
      /*
                Point2D p1 = new Point2D(0,1);
                Point2D p2 = new Point2D(1,0);
                Point2D[] p = new Point2D[2];
                */
    }
    @Test
    public void testTestToString() {
        Point2D p0 = new Point2D(3,3);

        assertEquals("3+3",p0.toString());
    }
}