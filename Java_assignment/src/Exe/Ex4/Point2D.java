
package Exe.Ex4;

import java.util.Comparator;
import java.math.*;

/**
 * This class represents a 2D point in the plane.
 * Do NOT change this class! It would be used as is for testing.
 * Ex4: you should edit and update this class!
 * @author boaz.benmoshe
 */




public class Point2D{
    //public static final double EPS1 = 0.001, EPS2 = Math.pow(EPS1,2), EPS=EPS2;
    public static final Point2D ORIGIN = new Point2D(0,0);
    private double _x,_y;
    public Point2D(double x,double y) {
    	_x=x; _y=y;
    }
    public Point2D(Point2D p) {
       this(p.x(), p.y());
    }
    public Point2D(String s) {
        try {
            String[] a = s.split(",");
            _x = Double.parseDouble(a[0]);
            _y = Double.parseDouble(a[1]);
        }
        catch(IllegalArgumentException e) {
            System.err.println("ERR: got wrong format string for Point2D init, got:"+s+"  should be of format: x,y");
            throw(e);
        }
    }
    public double x() {return _x;}
    public double y() {return _y;}
  
    public Point2D add(Point2D p) {
        return new Point2D(p.x()+x(),p.y()+y());
    }
    public String toString()
    {
        return _x+","+_y;
    }

    public double distance()
    {
        return this.distance(ORIGIN);
    }
    public double distance(Point2D p2)
    {
        double dx = this.x() - p2.x();
        double dy = this.y() - p2.y();
        double delta = (dx*dx+dy*dy);
        return Math.sqrt(delta);
    }

    public double distance(Segment2D seg) {
        Point2D[] points = seg.getPoints();
        double dist = Math.sqrt(Math.pow((points[0].x() - points[1].x()), 2) + Math.pow((points[0].y() - points[1].y()), 2));
        return dist * 2;
    }


    @Override
    public boolean equals(Object p)
    {
        if(p==null || !(p instanceof Point2D)) {return false;}
        Point2D p2 = (Point2D)p;
        return ( (_x==p2._x) && (_y==p2._y));
    }
    public boolean close2equals(Point2D p2, double eps)
    {
        return ( this.distance(p2) < eps );
    }
    public boolean close2equals(Point2D p2)
    {
        return close2equals(p2, Ex4_Const.EPS);
    }
    /**
     * This method returns the vector between this point and the target point. The vector is represented as a Point2D.
     * @param target
     * @return
     */
    public Point2D vector(Point2D target) {
    	double dx = target.x() - _x;
    	double dy = target.y() - _y;
    	return new Point2D(dx,dy);
    }
	
	public void move(Point2D vec) {
		_x = vec.x();
		_y = vec.y();
	}

	/////////////////////// You should implement the methods below ///////////////////////////
	public void scale(double ratio) {
		//////////add your code below ///////////
        _x*=ratio;
        _y*=ratio;
        /////////////////////////////////////////
	}

    public void scale(Point2D cen,double ratio){
            Point2D vector = cen.vector(this);
            vector.scale(ratio);
            Point2D after_scale = cen.add(vector);
            _x = after_scale.x();
            _y = after_scale.y();
    }
    public double angleFromP(Point2D pAngle) {

        double x = pAngle.x() - _x;
        double y = pAngle.y() - _y;
        return Math.atan2(y, x);
    }

    public void rotate(Point2D cen, double angle){

    }

    public void rotate(double angleDegrees) {
        _x = _x*Math.cos(angleDegrees) - _y*Math.sin(angleDegrees);
        _y = _x*Math.sin(angleDegrees) + _y*Math.cos(angleDegrees);
        }
		/////////////////////////////////////////
}


