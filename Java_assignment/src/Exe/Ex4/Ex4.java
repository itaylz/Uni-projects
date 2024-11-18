/* 203565676_318602885 */
/* select works only with anti and all, Rotate, Copy and Move work at weird delays and somewhat randomly
SAVE AND LOAD return no errors but do not work
*  */

package Exe.Ex4;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * This class is a simple "inter-layer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a 
 * "Singleton-like" implementation.
 * @author boaz.benmoshe
 *
 */
public class Ex4 implements Ex4_GUI {
	private ShapeCollectionable _shapes = new ShapeCollection();
	private GUI_Shapeable _gs;
	private Color _color = Color.blue;
	private boolean _fill;
	private String _mode;
	private String _shape;

	private Point2D _p1;
	private Point2D _p2;
	protected int _tag = 0;
	private ArrayList<Point2D> _polyPoints = new ArrayList<Point2D>();

	private static Ex4 _winEx4 = null;

	private Ex4() {
		init(null);
	}

	public void init(ShapeCollectionable s) {
		if (s == null) {
			_shapes = new ShapeCollection();
		} else {
			_shapes = s.copy();
		}
		GUI_Shapeable _gs = null;
		Polygon2D _pp = null;
		_color = Color.blue;
		_fill = false;
		_mode = "";
		_shape = "";
		Point2D _p1 = null;
		System.out.println(_shapes.size());
	}

	public void show(double d) {
		StdDraw_Ex4.setScale(0, d);
		StdDraw_Ex4.show();
		drawShapes();
	}

	public static Ex4 getInstance() {
		if (_winEx4 == null) {
			_winEx4 = new Ex4();
		}
		return _winEx4;
	}

	public void drawShapes() {
		StdDraw_Ex4.clear();
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable sh = _shapes.get(i);
			// How to get the shape.size to represent tag
			drawShape(sh);
		}
		if (_gs != null) {
			drawShape(_gs);
		}
		StdDraw_Ex4.show();
		System.out.println(Arrays.toString(_shapes.getBoundingBox().getPoints()));
	}

	private static void drawShape(GUI_Shapeable g) {
		StdDraw_Ex4.setPenColor(g.getColor());
		if (g.isSelected()) {
			StdDraw_Ex4.setPenColor(Color.gray);
		}
		GeoShapeable gs = g.getShape();
		boolean isFill = g.isFilled();
		if (gs instanceof Circle2D) {
			Circle2D c = (Circle2D) gs;
			Point2D cen = c.getPoints()[0];
			double rad = c.getRadius();
			if (isFill) {
				StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
			} else {
				StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
			}
		}
		if (gs instanceof Rect2D) {

			Rect2D rec = (Rect2D) gs;
			Point2D[] pts = rec.getPoints();
			// 2+6 /2  = 4
			double x = (pts[0].x() + pts[1].x()) / 2;
			double y = (pts[0].y() + pts[1].y()) / 2;
			double halfWidth = Math.abs(pts[0].x() - pts[1].x()) / 2;
			double halfHeight = Math.abs(pts[0].y() - pts[1].y()) / 2;
			if (isFill) { StdDraw_Ex4.filledRectangle(x, y, halfWidth, halfHeight); }
			else { StdDraw_Ex4.rectangle(x, y, halfWidth, halfHeight); }
		}


		if (gs instanceof Polygon2D) {
			Polygon2D poly = (Polygon2D) gs;
			if (isFill) {
				StdDraw_Ex4.filledPolygon(poly.getX(), poly.getY());
			} else {
				StdDraw_Ex4.polygon(poly.getX(), poly.getY());
			}
		}

		if (gs instanceof Segment2D) {
			Segment2D seg = (Segment2D) gs;
			Point2D[] seg_point = seg.getPoints();
			StdDraw_Ex4.line(seg_point[0].x(), seg_point[0].y(), seg_point[1].x(), seg_point[1].y());
		}

		if (gs instanceof Triangle2D) {
			Triangle2D tri = (Triangle2D) gs;
			if (isFill) {
				StdDraw_Ex4.filledPolygon(tri.get_X(), tri.get_Y());
			} else {
				StdDraw_Ex4.polygon(tri.get_X(), tri.get_Y());
			}
		}
	}

	private void setColor(Color c) {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			if (s.isSelected()) {
				s.setColor(c);
			}
		}
	}

	private void setFill() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			if (s.isSelected()) {
				s.setFilled(_fill);
			}
		}
	}

	private void copy() {
		int original_size = _shapes.size();
		for (int i = 0; i < original_size; i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if (s.isSelected() && g != null) {
				GUI_Shapeable s_copy = s.copy();
				GeoShapeable g_copy = s_copy.getShape();
				g_copy.move(_p1);
				_shapes.add(s_copy);
			}
		}
	}

	public void actionPerformed(String p) {
		_mode = p;
		if (p.equals("Blue")) {
			_color = Color.BLUE;
			setColor(_color);
		}
		if (p.equals("Red")) {
			_color = Color.RED;
			setColor(_color);
		}
		if (p.equals("Green")) {
			_color = Color.GREEN;
			setColor(_color);
		}
		if (p.equals("White")) {
			_color = Color.WHITE;
			setColor(_color);
		}
		if (p.equals("Black")) {
			_color = Color.BLACK;
			setColor(_color);
		}
		if (p.equals("Yellow")) {
			_color = Color.YELLOW;
			setColor(_color);
		}
		if (p.equals("Fill")) {
			_fill = true;
			setFill();
		}
		if (p.equals("Empty")) {
			_fill = false;
			setFill();
		}
		if (p.equals("Clear")) {
			_shapes.removeAll();
			_tag = 0;

		}

		if (p.equals("ByToString")) {
			_shapes.sort(ShapeComp.CompareByToString);
		}
		if (p.equals("ByAntiToString")) {
			_shapes.sort(ShapeComp.CompareByAntiToString);
		}
		if (p.equals("ByTag")) {
			_shapes.sort(ShapeComp.CompareByTag);
		}
		if (p.equals("ByAntiTag")) {
			_shapes.sort(ShapeComp.CompareByAntiTag);
		}
		if (p.equals("ByArea")) {
			_shapes.sort(ShapeComp.CompareByArea);
		}
		if (p.equals("ByAntiArea")) {
			_shapes.sort(ShapeComp.CompareByAntiArea);
		}
		if (p.equals("ByPerimeter")) {
			_shapes.sort(ShapeComp.CompareByPerimeter);
		}
		if (p.equals("ByAntiPerimeter")) {
			_shapes.sort(ShapeComp.CompareByAntiPerimeter);
		}

		drawShapes();


		if (p.equals("All")) {
			int i = 0;
			while ( i < _shapes.size() ) {
				if (!_shapes.get(i).isSelected()) { _shapes.get(i).setSelected(true); }
				i++;
			}
		}
		if (p.equals("Anti")) {
			int i = 0;
			while ( i < _shapes.size()) {
				GUI_Shapeable s = _shapes.get(i);
				s.setSelected(!s.isSelected());
				i++;
			}
		}
		if (p.equals("None")) {
			int i = 0;
			while (i < _shapes.size()) {
				_shapes.get(i).setSelected(false);
				i++;
			}
		}

		if (p.equals("Remove")) {
			int i = _shapes.size() - 1;
			while ( i >= 0 ) {
				GUI_Shapeable s = _shapes.get(i);
				if (s.isSelected()) {
					_shapes.removeElementAt(i);

				}
				i--;
			}
		}

		if (p.equals("Load")) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Select a file to load");

			String currentWorkingDirectory = System.getProperty("user.dir");
			File projectDirectory = new File(currentWorkingDirectory);
			fileChooser.setCurrentDirectory(projectDirectory);

			int userSelection = fileChooser.showSaveDialog(fileChooser);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File fileToLoad = fileChooser.getSelectedFile();
				_shapes.removeAll();
				_shapes.load(fileToLoad.getAbsolutePath());
			}
		}
		if (p.equals("Save")) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Specify a file to save");

			String currentWorkingDirectory = System.getProperty("user.dir");
			File projectDirectory = new File(currentWorkingDirectory);
			fileChooser.setCurrentDirectory(projectDirectory);

			int userSelection = fileChooser.showSaveDialog(fileChooser);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File fileToSave = fileChooser.getSelectedFile();
				System.out.println("Save as file: " + fileToSave.getAbsolutePath());
				_shapes.save(fileToSave.getAbsolutePath());
			}
		}
	}

	private void rotateSelected(Point2D cen, double angleDegrees) {
		for (int i = 0; i < _shapes.size(); ++i) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if (s.isSelected() && g != null) {
				g.rotate(cen,angleDegrees);
			}
		}
	}


	public void mouseClicked(Point2D p) {
		System.out.println("Mode: " + _mode + "  " + p);
		if (_mode.equals("Circle")) {
			if (_gs == null) {
				_p1 = new Point2D(p);
			} else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_gs.setTag(_tag++);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}

		if (_mode.equals("Point")) {
			for(int i = 0; i < _shapes.size(); i++) {
				GUI_Shapeable s = _shapes.get(i);
				GeoShapeable g = _shapes.get(i).getShape();
				if (g != null && g.contains(p)) {
					s.setSelected(!s.isSelected());
				}
			}
		}

		if (_mode.equals("Copy")) {
			if (_p1 == null) {
				_p1 = new Point2D(p);
			}
			else {
				_p1 = new Point2D(p.x() - _p1.x(), p.y() - _p1.y());
				int i = 0;
				while ( i < _shapes.size() ) {
					GUI_Shapeable s = _shapes.get(i);
					if (s.isSelected() && s.getShape() != null) {
						_shapes.add(s.copy());
						_shapes.get(_shapes.size() - 1).getShape().move(_p1);
					}
					i++;
				}
				_p1 = null;
			}
		}

		if (_mode.equals("Rotate")) {
			if (_p1 == null) {
				_p1 = new Point2D(p);
				System.out.println("first point:" + " " + + _p1.x() + "," + _p1.y());
			} else {
				_p2 = new Point2D(p);
				System.out.println("second point:" + " " + _p2.x() + "," + _p2.y());

				for (int i = 0; i < _shapes.size(); i++) {
					GUI_Shapeable shapes = _shapes.get(i);
					GeoShapeable g = shapes.getShape();
					if (shapes.isSelected() && g != null) {
						g.rotate(_p1, Math.toDegrees(g.get_centre().angleFromP(_p1)));
					}
				}
				_p1 = null;
				_p2 = null;
			}
		}

		if (_mode.equals("Scale_90%")) {
			for (int i = 0; i < _shapes.size(); i++) {
				if (_shapes.get(i).isSelected()) {
					_shapes.get(i).getShape().scale(p, 0.9);
				}
			}
		}
		if (_mode.equals("Scale_110%")) {
			for (int i = 0; i < _shapes.size(); i++) {
				if (_shapes.get(i).isSelected()) {
					_shapes.get(i).getShape().scale(p, 1.1);
				}
			}
		}


		if(_mode.equals("Segment")){
			if (_gs == null){
			_p1 = new Point2D(p);
			}
			else {
			_gs.setColor(_color);
			_gs.setFilled(_fill);
			_gs.setTag(_tag++);
			_shapes.add(_gs);
			_gs = null;
			_p1 = null;
		}
	}

		if(_mode.equals("Move")){
			if (_p1 == null) {
			_p1 = new Point2D(p);
			move();
		}
			_p1 = null;
	}
		if (_mode.equals("Rect")) {
			if (_gs == null) {
				_p1 = new Point2D(p);
			} else {
				_gs.setFilled(_fill);
				_gs.setColor(_color);
				_gs.setTag(_tag++);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}
		if(_mode.equals("Point")) {
		select(p);
	}

		if(_mode.equals("Polygon")) {
		if (_gs == null) {
			_polyPoints.add(p);
			_p1 = new Point2D(p);
		} else {
			_polyPoints.add(p);
		}
	}

		if(_mode.equals("Triangle"))
	{
		if (_gs == null) {
			_p1 = new Point2D(p);
		} else if (_p1 != null && _p2 == null) {
			_p2 = new Point2D(p);
		} else {
			_gs.setColor(_color);
			_gs.setFilled(_fill);
			_gs.setTag(_tag++);
			_shapes.add(_gs);
			_gs = null;
			_p1 = null;
			_p2 = null;
		}
	}
		drawShapes();
}

		private void select(Point2D p){
			for (int i = 0; i < _shapes.size(); i++) {
				GUI_Shapeable s = _shapes.get(i);
				GeoShapeable g = s.getShape();
				if (g != null && g.contains(p)) {
					s.setSelected(!s.isSelected());
				}
			}
		}
		private void move(){
			System.out.println(_shapes.size());
			for (int i = 0; i < _shapes.size(); i++) {
				GUI_Shapeable s = _shapes.get(i);
				GeoShapeable g = s.getShape();
				System.out.println(g.toString());
				if (s.isSelected()) {
					g.move(_p1);
					drawShapes();
					System.out.println(Arrays.toString(_shapes.getBoundingBox().getPoints()));
					System.out.println(_shapes.size());
				}
			}
		}

		public void mouseRightClicked (Point2D p){
			System.out.println("right click!");
			if (_mode.equals("Polygon") && _p1 != null) {
				Polygon2D poly = new Polygon2D(_polyPoints);
				_gs = new GUIShape(poly, _fill, _color, _tag++);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
				_polyPoints.clear();
				drawShapes();
			}
		}
		public void mouseMoved (MouseEvent e){
			if (_p1 != null) {
				double x1 = StdDraw_Ex4.mouseX();
				double y1 = StdDraw_Ex4.mouseY();
				GeoShapeable gs = null;
				//	System.out.println("M: "+x1+","+y1);
				Point2D p = new Point2D(x1, y1);
				if (_mode.equals("Circle")) {
					double r = _p1.distance(p);
					gs = new Circle2D(_p1, r);
				}

				if(_mode.equals("Rect")){
					Rect2D rec = new Rect2D(_p1,p);
					gs = rec;
				}

				if(_mode.equals("Segment")){
					Segment2D seg = new Segment2D(_p1,p);
					gs = seg;
				}

				if (_mode.equals("Polygon")) {
					Polygon2D poly = new Polygon2D(_polyPoints);
					poly.addPoint(p);
					gs = poly;
				}

				if(_mode.equals("Triangle")){
					if(_p2 == null){
						Segment2D line1 = new Segment2D(_p1,p);
						gs = line1;
					}
					else {
						Triangle2D tri = new Triangle2D(_p1,_p2,p);
						gs = tri;
					}
				}

				_gs = new GUIShape(gs, false, Color.pink, _tag);
				_gs.setShape(gs);
				drawShapes();
			}
		}
		@Override
		public ShapeCollectionable getShape_Collection () {
			// TODO Auto-generated method stub
			return this._shapes;
		}
		@Override
		public void show () {
			show(Ex4_Const.DIM_SIZE);
		}
		@Override
		public String getInfo () {
			// TODO Auto-generated method stub
			String ans = "";
			for (int i = 0; i < _shapes.size(); i++) {
				GUI_Shapeable s = _shapes.get(i);
				ans += s.toString() + "\n";
			}
			return ans;
		}
	}


