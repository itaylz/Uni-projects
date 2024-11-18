package Exe.Ex4;

import junit.framework.TestCase;

public class Rect2DTest extends TestCase {

    public void testContains() {
    }

    public void testArea() {
        Rect2D rect0 = new Rect2D(new Point2D(0,0),new Point2D(0,4));

        assertEquals(rect0.area(),16.0);
    }

    public void testPerimeter() {
        Rect2D rect0 = new Rect2D(new Point2D(0,0),new Point2D(0,4));

        assertEquals(rect0.perimeter(),16.0);
    }

    public void testMove() {
    }

    public void testCopy() {
        Rect2D rect0 = new Rect2D(new Point2D(0,0),new Point2D(0,4));
        Rect2D rect1 = (Rect2D) rect0.copy();

        assertEquals(rect0.area(),rect1.area());
    }

    public void testScale() {
    }

    public void testRotate() {
    }

    public void testGetPoints() {
        Rect2D rect0 = new Rect2D(new Point2D(0,0),new Point2D(0,4));

        Point2D[] arrayPointOfRect = rect0.getPoints();
        Point2D p1 = arrayPointOfRect[0];
        Point2D p2 = arrayPointOfRect[1];
        Point2D p3 = arrayPointOfRect[2];
        Point2D p4 = arrayPointOfRect[3];
        double rect0AreaFromGetPoint = p1.distance(p3) * p2.distance(p1);

        assertEquals(4,arrayPointOfRect.length);
    }
}