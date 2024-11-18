package Exe.Ex4;

/** 
 * This class represents a 2D circle in the plane. 
 * Please make sure you update it according to the GeoShape interface.
 * Ex4: you should update this class!
 * @author boaz.benmoshe
 *
 */
public class Circle2D implements GeoShapeable{
	private Point2D _center;
	private double _radius;
	
	public Circle2D(Point2D cen, double rad) {
		this._center = new Point2D(cen);
		this._radius = rad;
	}
	public double getRadius() {return this._radius;}
	 @Override
	    public String toString()
	    { return _center.toString()+", "+Double.toString(_radius);}
	@Override
	public boolean contains(Point2D ot) {
		double dist = ot.distance(this._center);
		return dist<=this._radius;
	}
	
	@Override
	public double area() {
		double ans = Math.PI * Math.pow(this._radius, 2);
		return ans;
	}
	@Override
	public double perimeter() {
		double ans = Math.PI * 2 * this._radius;
		return ans;
	}
	@Override
	public void move(Point2D vec) {
		Point2D vector = _center.vector(vec);
		Point2D new_centre = new Point2D(_center.x()+ vector.x(), _center.y()+ vector.y());
		_center.move(new_centre);
	}
	@Override
	public GeoShapeable copy() {
		return new Circle2D(_center, _radius);
	}
	@Override
	public Point2D[] getPoints() {
		Point2D[] ans = new Point2D[2];
		ans[0] =new Point2D(this._center);

		return ans;
	}
	@Override
	public void scale(Point2D center, double ratio) {
		//////////add your code below ///////////
	
		//////////////////////////////////////////
	}
	@Override
	public void rotate(Point2D cen, double angleDegrees) {
		//////////add your code below ///////////
		
		//////////////////////////////////////////
	}

	@Override
	public Point2D get_centre(){
		return _center;
	}

}
