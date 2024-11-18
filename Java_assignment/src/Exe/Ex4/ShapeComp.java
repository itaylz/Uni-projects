package Exe.Ex4;

import java.util.Comparator;

/**
 * This class represents a Comparator over GUI_Shapes - 
 * as a linear order over GUI_Shapes.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeComp implements Comparator<GUI_Shapeable>{
	//////////add your code below ///////////
	public static final Comparator<GUI_Shapeable> CompareByAntiToString = new ShapeComp(Ex4_Const.Sort_By_Anti_toString);
	public static final Comparator<GUI_Shapeable> CompareByToString = new ShapeComp(Ex4_Const.Sort_By_toString);
	public static final Comparator<GUI_Shapeable> CompareByTag = new ShapeComp(Ex4_Const.Sort_By_Tag);
	public static final Comparator<GUI_Shapeable> CompareByAntiTag = new ShapeComp(Ex4_Const.Sort_By_Anti_Tag);
	public static final Comparator<GUI_Shapeable> CompareByArea = new ShapeComp(Ex4_Const.Sort_By_Area);
	public static final Comparator<GUI_Shapeable> CompareByAntiArea = new ShapeComp(Ex4_Const.Sort_By_Anti_Area);
	public static final Comparator<GUI_Shapeable> CompareByPerimeter = new ShapeComp(Ex4_Const.Sort_By_Perimeter);
	public static final Comparator<GUI_Shapeable> CompareByAntiPerimeter = new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter);
	
	private int _flag;
	public ShapeComp(int flag) {
		_flag = flag;
	}
	@Override
	public int compare(GUI_Shapeable o1, GUI_Shapeable o2) {
		int ans=0;
		if(_flag == Ex4_Const.Sort_By_toString) {
			ans = o1.toString().compareTo(o2.toString());
		}
		//////////add your code below ///////////
		if (_flag == Ex4_Const.Sort_By_Tag) {
			return Integer.compare(o2.getTag(), o1.getTag());
		}
		if (_flag == Ex4_Const.Sort_By_Anti_Tag) {
			return Integer.compare(o1.getTag(), o2.getTag());
		}
		if (_flag == Ex4_Const.Sort_By_Area) {
			return Double.compare(o1.getShape().area(), o2.getShape().area());
		}
		if (_flag == Ex4_Const.Sort_By_Anti_Area) {
			return Double.compare(o2.getShape().area(), o1.getShape().area());
		}
		if (_flag == Ex4_Const.Sort_By_Perimeter) {
			return Double.compare(o1.getShape().perimeter(), o2.getShape().perimeter());
		}
		if (_flag == Ex4_Const.Sort_By_Anti_Perimeter) {
			return Double.compare(o2.getShape().perimeter(), o1.getShape().perimeter());
		}
		if (_flag == Ex4_Const.Sort_By_Anti_toString) {
			return o2.toString().compareTo(o1.toString());
		}
		//////////////////////////////////////////
		return ans;
	}

}
