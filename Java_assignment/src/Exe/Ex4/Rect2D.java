package Exe.Ex4;

import java.util.*;

/**
 * This class represents a 2D rectangle (NOT necessarily axis parallel - this shape can be rotated!)
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class  Rect2D implements GeoShapeable {
	private Point2D _p1, _p2, _p3, _p4;

	public Rect2D(Point2D p1, Point2D p2) {
		_p1 = new Point2D(p1);
		_p2 = new Point2D(p2);
		_p3 = new Point2D(p1.x(), p2.y());
		_p4 = new Point2D(p2.x(), p1.y());
	}

	public Rect2D(Rect2D rect){
		_p1 = new Point2D(rect._p1);
		_p2 = new Point2D(rect._p2);
		_p3 = new Point2D(rect._p3);
		_p4 = new Point2D(rect._p4);
	}

	public Rect2D(ArrayList<Point2D> points){
		if(points.size()>4){
			throw new RuntimeException("cannot cast rectangle with more than 4 points.");
		}
		_p1 = new Point2D(points.get(0));
		_p2 = new Point2D(points.get(1));
		_p3 = new Point2D(points.get(2));
		_p4 = new Point2D(points.get(3));
	}

	public Rect2D(String[] pointStrings) {
		if(pointStrings.length<8){
			throw new IllegalArgumentException("Cannot cast rectangle with less than 8 elements");
		}
		_p1 = new Point2D(Double.parseDouble(pointStrings[0]), Double.parseDouble(pointStrings[1]));
		_p2 = new Point2D(Double.parseDouble(pointStrings[2]), Double.parseDouble(pointStrings[3]));
		_p3 = new Point2D(Double.parseDouble(pointStrings[4]), Double.parseDouble(pointStrings[5]));
		_p4 = new Point2D(Double.parseDouble(pointStrings[6]), Double.parseDouble(pointStrings[7]));
	}


	@Override
	public boolean contains(Point2D ot) {
		// TODO Auto-generated method stub
		ArrayList<Point2D> points = new ArrayList<Point2D>();
		points.add(this.getPoints()[0]);
		points.add(this.getPoints()[1]);
		points.add(this.getPoints()[2]);
		points.add(this.getPoints()[3]);

		//points = ShapeCollection.sortPoints(points);
		return ot.x() >= points.get(0).x() && ot.x() <= points.get(2).x() & ot.y() >= points.get(0).y() && ot.y() <= points.get(3).y();
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		double length = Math.abs(_p1.x() - _p2.x());
		double height = Math.abs(_p1.y() - _p2.y());

		return length*height;
	}

	@Override
	public double perimeter() {
		// TODO Auto-generated method stub
		double length = Math.abs(_p1.x() - _p2.x());
		double height = Math.abs(_p1.y() - _p2.y());

		return 2*length + 2*height;
	}

	@Override
	public void move(Point2D vec) {
		Point2D centre = get_centre();
		Point2D vector = centre.vector(vec);

		Point2D new_p1 = new Point2D(vector.x()+_p1.x(),vector.y()+_p1.y());
		Point2D new_p2 = new Point2D(vector.x()+_p2.x() ,vector.y()+_p2.y());
		Point2D new_p3 = new Point2D(vector.x()+_p3.x() ,vector.y()+_p3.y());
		Point2D new_p4 = new Point2D(vector.x()+_p4.x(), vector.y()+_p4.y());

		_p1.move(new_p1);
		_p2.move(new_p2);
		_p3.move(new_p3);
		_p4.move(new_p4);


	}

	@Override
	public GeoShapeable copy() {
		// TODO Auto-generated method stub
		return new Rect2D(_p1,_p2);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		// TODO Auto-generated method stub
		_p1.scale(center,ratio);
		_p2.scale(center,ratio);
		_p3.scale(center,ratio);
		_p4.scale(center,ratio);


	}

	@Override
	public Point2D get_centre(){
		return new Point2D((_p1.x() + _p2.x()) / 2, (_p1.y() + _p2.y())/2);
	}

	@Override
	public void rotate(Point2D cen, double angleDegrees) {
		// TODO Auto-generated method stub
		_p1.rotate(angleDegrees);
		_p2.rotate(angleDegrees);
		_p3.rotate(angleDegrees);
		_p4.rotate(angleDegrees);
		
	}

	public Point2D[] getSortedPoints(){
		List<Point2D> points = Arrays.asList(getPoints());
		Comparator<Point2D> x_coor = new Comparator<Point2D>() {
			@Override
			public int compare(Point2D p1, Point2D p2) {
				return Double.compare(p1.x(),p2.x());
			}
		};
		Comparator<Point2D> y_coor = new Comparator<Point2D>() {
			@Override
			public int compare(Point2D p1, Point2D p2) {
				return Double.compare(_p1.y(), _p2.y());
			}
		};

		double min_x = Collections.min(points, x_coor).x();
		double max_x = Collections.max(points, x_coor).x();
		double min_y = Collections.min(points, y_coor).y();
		double max_y = Collections.max(points, y_coor).y();

		Point2D smallest_point = new Point2D(min_x, min_y);
		Point2D biggest_point = new Point2D(max_x, max_y);

		return new Point2D[] { smallest_point, biggest_point };
	}

	@Override
	public Point2D[] getPoints() {
		return new Point2D[] {_p1,_p2,_p3,_p4};
	}

}
