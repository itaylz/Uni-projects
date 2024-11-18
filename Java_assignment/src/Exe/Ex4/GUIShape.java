package Exe.Ex4;
/**
 * This class implements the GUI_shape.
 * Ex4: you should implement this class!
 * @author I2CS
 */
import java.awt.Color;



public class GUIShape implements GUI_Shapeable {
    private GeoShapeable _g;
    private boolean _fill;
    private Color _color;
    private int _tag;
    private boolean _isSelected;

    public GUIShape(GeoShapeable g, boolean f, Color c, int t) {
        _g = g;
        _fill = f;
        _color = c;
        _tag = t;
        _isSelected = false;
    }

    public GUIShape(GUIShape ot) {
        this(ot._g, ot._fill, ot._color, ot._tag);
    }

    @Override
    public GeoShapeable getShape() {
        return _g;
    }

    @Override
    public boolean isFilled() {
        return _fill;
    }

    @Override
    public void setFilled(boolean filled) {
        _fill = filled;
    }

    @Override
    public Color getColor() {
        return _color;
    }

    @Override
    public void setColor(Color cl) {
        _color = cl;
    }

    @Override
    public int getTag() {
        return _tag;
    }

    @Override
    public void setTag(int tag) {
        _tag = tag;

    }

    @Override
    public GUI_Shapeable copy() {
        GUI_Shapeable cp = new GUIShape(this);
        return cp;
    }

    @Override
    public String toString() {
        return "GUIShape," + _color.getRGB() + ',' + _fill + ',' + _tag + ',' + _g.getClass().getSimpleName() + ',' + _g;
    }

    /* this method is used for testing purposes unnecessary for runtime, creates new shape using terminal input
    */


    private void init(String[] ww) {
        if (!ww[0].equals("GUIShape")) throw new IllegalArgumentException("String type is non-GUIShape");
        try {
            _color = new Color(Integer.parseInt(ww[1]));
            _fill = Boolean.parseBoolean(ww[2]);
            _tag = Integer.parseInt(ww[3]);
        } catch (NumberFormatException e) {
            System.err.println("ERROR: wrong string format for GUIShape initialization: " + e.getMessage());
            throw e;
        }
        String type = ww[4];
        String[] pointStrings = new String[ww.length - 5];
        System.arraycopy(ww, 5, pointStrings, 0, pointStrings.length);
        switch (type) {
            case "Circle2D" -> {
                try {
                    _g = new Circle2D(new Point2D(Double.parseDouble(ww[5]), Double.parseDouble(ww[6])), Double.parseDouble(ww[7]));
                } catch (IllegalArgumentException e) {
                    System.err.println("ERROR: wrong string format for Circle initialization: " + e.getMessage());
                }
            }
            case "Segment2D" -> _g = new Segment2D(pointStrings);
            case "Triangle2D" -> _g = new Triangle2D(pointStrings);
            case "Rect2D" -> _g = new Rect2D(pointStrings);
            case "Polygon2D" -> _g = new Polygon2D(pointStrings);
        }
    }

    @Override
    public boolean isSelected() {
        return _isSelected;
    }

    @Override
    public void setSelected(boolean s) {
        _isSelected = s;
    }

    @Override
    public void setShape(GeoShapeable g) {
        // TODO Auto-generated method stub
        _g = g;
    }

    protected void sortPoints(GeoShapeable g){
        Point2D[] points = g.getPoints();

    }
}




