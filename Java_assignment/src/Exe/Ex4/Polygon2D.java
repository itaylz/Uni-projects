package Exe.Ex4;

import java.util.ArrayList;

/**
 * This class represents a 2D polygon, as in https://en.wikipedia.org/wiki/Polygon
 * This polygon can be assumed to be simple in terms of area and contains.
 * 
 * You should update this class!
 * @author boaz.benmoshe
 *
 */
public class Polygon2D implements GeoShapeable{
	private ArrayList<Point2D> _points;

	public Polygon2D(){
		_points = new ArrayList<Point2D>();
	}

	public Polygon2D(ArrayList<Point2D> points){
		_points = (ArrayList<Point2D>) points.clone();
	}

	public Polygon2D(String[] pointStrings) {
		if(pointStrings.length % 2 !=0){
			throw new IllegalArgumentException("Polygon cant be initialized with an odd number of elements");
		}
	}

	public void addPoint(Point2D point){
		_points.add(point);
	}

	public String toString() {
		StringBuilder res = new StringBuilder(_points.get(0).toString());
		for (int i = 1; i < _points.size(); i++) {
			res.append(",").append(_points.get(i));
		}
		return res.toString();
	}

	@Override
	public boolean contains(Point2D ot) {
		// TODO Auto-generated method stub
		Point2D[] points = this.getPoints();
		int count = 0;
		double x = ot.x();
		double y = ot.y();
		for(int i = 0;i<points.length;i++){
			Segment2D side = new Segment2D(points[i],points[i+1]);
			double x1 = side.getPoints()[0].x() , x2 = side.getPoints()[1].x(),
					y1 = side.getPoints()[0].y(), y2 = side.getPoints()[1].y();
			if(y<y1 != y<y2 && x<(x2-x1)*(y-y1/(y2-y1)+x1)){
				count+=1;
			}
		}
		return count % 2 != 0;
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		double area = 0;
		int counter = 0;

		while ( counter < _points.size() ) {

			Point2D first_point = _points.get(counter);
			Point2D next_point = _points.get((counter + 1) % _points.size());
			area += (first_point.x() * next_point.y()) - (first_point.y() * next_point.x());

			counter++;
		}
		return Math.abs( area / 2 );
	}

	@Override
	public double perimeter() {
		// TODO Auto-generated method stub
		ArrayList<Double> sides = new ArrayList<Double>();
		int i=0;
		while(_points.iterator().hasNext()){
			double distances = _points.get(i).distance(_points.get(i+1));
			sides.add(distances);
			i++;
		}
		double sum = 0;
		for (int j=0;j<sides.size();j++){
			sum+=sides.get(i);
		}
		return sum;
	}

	@Override
	public void move(Point2D vec) {
		// TODO Auto-generated method stub
		Point2D centre = get_centre();
		Point2D vector = centre.vector(vec);

		for (Point2D point:_points) {
				point.add(vector);
		}
	}

	@Override
	public Point2D get_centre(){
		double x = 0;
		double y = 0;
		for (int i = 0;i < _points.size()-1;i++) {
			 x += (_points.get(i).x()+_points.get(i+1).x())*(_points.get(i).x()*_points.get(i+1).y()
			-_points.get(i+1).x()*_points.get(i).y());

			 y += (_points.get(i).y()+_points.get(i+1).y())*(_points.get(i).x()*_points.get(i+1).y()
					-_points.get(i+1).x()*_points.get(i).y());

		}
		return new Point2D((1/(6*area()))*x,(1/(6*area()))*y);
	}

	@Override
	public GeoShapeable copy() {
		// TODO Auto-generated method stub
		return new Polygon2D(_points);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		// TODO Auto-generated method stub
		for(Point2D point:_points){
			point.scale(center,ratio);
		}
	}

	@Override
	public void rotate(Point2D cen, double angleDegrees) {
		// TODO Auto-generated method stub
		for(Point2D point : _points){
			point.rotate(angleDegrees);
		}
	}

	@Override
	public Point2D[] getPoints() {
		// TODO Auto-generated method stub
		return (Point2D[])_points.toArray();
	}

	public double[] getX() {
		double[] ans = new double[_points.size()];
		for(int i =0;i<_points.size();i++){
			ans[i] = _points.get(i).x();
		}
		return ans;
	}

	public double[] getY() {
		double[] ans = new double[_points.size()];
		for(int i =0;i<_points.size();i++){
			ans[i] = _points.get(i).y();
		}
		return ans;
	}
}
