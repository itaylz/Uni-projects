package Exe.Ex4;

import java.util.ArrayList;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle2D implements GeoShapeable{

	private Point2D _p1;
	private Point2D _p2;
	private Point2D _p3;
	public Triangle2D(Point2D p1, Point2D p2, Point2D p3){
		_p1 = new Point2D(p1);
		_p2 = new Point2D(p2);
		_p3 = new Point2D(p3);
	}

	public Triangle2D(ArrayList<Point2D> points){
		if(points.size()>3){
			throw new ArrayIndexOutOfBoundsException("cannot cast with more than 3 points");
		}
		else{
			_p1 = new Point2D(points.get(0));
			_p2 = new Point2D(points.get(1));
			_p3 = new Point2D(points.get(2));
		}
	}
	public Triangle2D(String[] pointStrings) {
		if (pointStrings.length < 6) throw new IllegalArgumentException("minimum 6 elements required to cast triangle");
		try {
			_p1 = new Point2D(Double.parseDouble(pointStrings[0]), Double.parseDouble(pointStrings[1]));
			_p2 = new Point2D(Double.parseDouble(pointStrings[2]), Double.parseDouble(pointStrings[3]));
			_p3 = new Point2D(Double.parseDouble(pointStrings[4]), Double.parseDouble(pointStrings[5]));
		} catch (IllegalArgumentException e) {
			System.err.println("ERROR: cannot cast triangle " + e.getMessage());
		}
	}



	public String toString() {
		return "" + _p1 + ',' + _p2 + ',' + _p3;
	}

	@Override
	public boolean contains(Point2D ot) {
		//calculate if given point falls within area of triangle
		boolean ans = false;
		double a = (_p1.x() - ot.x()) * (_p2.y() - ot.y()) - (_p1.y() - ot.y()) * (_p2.x() - ot.x());
		double b = (_p2.x() - ot.x()) * (_p3.y() - ot.y()) - (_p2.y() - ot.y()) * (_p3.x() - ot.x());
		double c = (_p3.x() - ot.x()) * (_p1.y() - ot.y()) - (_p3.y() - ot.y()) * (_p1.x() - ot.x());
		if ((a >= 0 && b >= 0 && c >= 0) || (a <= 0 && b <= 0 && c <= 0)) {
			ans = true;
		}
		return ans;
	}

	@Override
	public double area() {
		//calculate area using points
		double area = Math.abs(_p1.x()*(_p2.y()-_p3.y())+_p2.x()*(_p3.y()-_p1.y())+_p3.x()*(_p1.y()-_p2.y()));
		return area/2;
	}

	@Override
	public double perimeter() {
		return _p1.distance(_p2)+ _p2.distance(_p3)+ _p3.distance(_p1);
	}

	@Override
	public Point2D get_centre(){
		return new Point2D ((_p1.x()+_p2.x()+_p3.x())/3, (_p1.y()+_p2.y()+_p3.y())/3);
	}


	@Override
	public void move(Point2D vec) {
		Point2D centre = get_centre();
		Point2D vector = centre.vector(vec);

		Point2D new_p1 = new Point2D(vector.x()+_p1.x(),vector.y()+_p1.y());
		Point2D new_p2 = new Point2D(vector.x()+_p2.x() ,vector.y()+_p2.y());
		Point2D new_p3 = new Point2D(vector.x()+_p3.x() ,vector.y()+_p3.y());

		_p1.move(new_p1);
		_p2.move(new_p2);
		_p3.move(new_p3);

	}

	@Override
	public GeoShapeable copy() {
		return new Triangle2D(_p1, _p2, _p3);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		_p1.scale(center, ratio);
		_p2.scale(center, ratio);
		_p3.scale(center, ratio);
	}

	@Override
	public void rotate(Point2D cen, double angleDegrees) {
		Point2D centre_of_triangle = get_centre();
		Point2D vector = centre_of_triangle.vector(cen);
		vector.rotate(angleDegrees);

		_p1.rotate(angleDegrees);
		_p2.rotate(angleDegrees);
		_p3.rotate(angleDegrees);
	}

	@Override
	public Point2D[] getPoints() {
		return new Point2D[] { _p1, _p2, _p3 };
	}

	public double[] get_X() {
		double[] ans = new double[3];
		for(int i =0;i<3;i++){
			ans[i] = this.getPoints()[i].x();
		}
		return ans;
	}

	public double[] get_Y() {
		double[] ans = new double[3];
		for(int i =0;i<3;i++){
			ans[i] = this.getPoints()[i].y();
		}
		return ans;
	}
}
