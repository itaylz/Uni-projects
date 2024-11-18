package Exe.Ex4;


import java.awt.*;

/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Segment2D implements GeoShapeable{
	private Point2D _point1;
	private  Point2D _point2;

	public Segment2D(Point2D p1, Point2D p2){
		_point1 = new Point2D(p1);
		_point2 = new Point2D(p2);
	}

	public Segment2D(double x1, double x2, double y1, double y2){
		_point1 = new Point2D(x1,y1);
		_point2 = new Point2D(x2,y2);
	}

	public Segment2D(Segment2D seg) {
		this(seg.getPoints()[0], seg.getPoints()[1]);
	}

	public Segment2D(String[] pointStrings) {
		if(pointStrings.length<4){
			throw new IllegalArgumentException("Cannot cast segment with less than 4 elements");
		}
		_point1 = new Point2D(Double.parseDouble(pointStrings[0]),Double.parseDouble(pointStrings[1]));
		_point2 = new Point2D(Double.parseDouble(pointStrings[2]),Double.parseDouble(pointStrings[4]));
	}



	@Override
	public boolean contains(Point2D ot) {
		// TODO Auto-generated method stub
		double minX = Math.min(_point1.x(), _point2.x()), maxX = Math.max(_point1.x(), _point2.x()), minY = Math.min(_point1.y(), _point2.y()), maxY = Math.max(_point1.y(), _point2.y());
		if (ot.x() < minX || ot.x() > maxX || ot.y() < minY || ot.y() > maxY) return false;
		return !(ot.distance(this) > 0.1);
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double perimeter() {
		// TODO Auto-generated method stub
		return _point1.distance(_point2)*2;

	}

	@Override
	public void move(Point2D vec) {
		// TODO Auto-generated method stub
		Point2D centre = get_centre();
		Point2D vector = centre.vector(vec);

		Point2D new_point1 = _point1.add(vector);
		Point2D new_point2 = _point2.add(vector);

		_point1.move(new_point1);
		_point2.move(new_point2);
	}

	@Override
	public GeoShapeable copy() {
		// TODO Auto-generated method stub
		return new Segment2D(_point1,_point2);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		// TODO Auto-generated method stub
		_point1.scale(center,ratio);
		_point2.scale(center,ratio);
	}

	@Override
	public Point2D get_centre(){
		return new Point2D((_point1.x()+_point2.x())/2, (_point1.y()+ _point2.y())/2);
	}

	@Override
	public void rotate(Point2D cen, double angleDegrees) {
		// TODO Auto-generated method stub
		_point1.rotate(angleDegrees);
		_point2.rotate(angleDegrees);
	}

	@Override
	public Point2D[] getPoints() {
		// TODO Auto-generated method stub
		return new Point2D[] { new Point2D(_point1), new Point2D(_point2) };
	}
	
}