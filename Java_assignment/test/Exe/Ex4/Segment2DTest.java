package Exe.Ex4;

import junit.framework.TestCase;

public class Segment2DTest extends TestCase {

    public void testContains() {
    }

    public void testDistance() {

    }

    public void testArea() {
        Segment2D seg0 = new Segment2D(new Point2D(0,0),new Point2D(3,4));

        assertEquals(0,seg0.area());
    }

    public void testPerimeter() {
        Segment2D seg0 = new Segment2D(new Point2D(0,0),new Point2D(3,4));

        assertEquals(5, seg0.perimeter());
    }

    public void testMove() {
    }

    public void testCopy() {
        Segment2D seg0 = new Segment2D(new Point2D(0,0),new Point2D(3,4));
        Segment2D seg1 = (Segment2D) seg0.copy();

        assertEquals(seg0.perimeter(),seg1.perimeter());
    }

    public void testScale() {
    }

    public void testRotate() {
    }

    public void testGetPoints() {
        Segment2D seg0 = new Segment2D(new Point2D(0,0),new Point2D(3,4));
        Point2D[] arrayOfSegPoints = seg0.getPoints();
        Point2D p1 = arrayOfSegPoints[0];
        Point2D p2 = arrayOfSegPoints[1];


         assertEquals(2,arrayOfSegPoints.length);

    }
}