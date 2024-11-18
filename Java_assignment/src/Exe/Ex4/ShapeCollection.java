package Exe.Ex4;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements ShapeCollectionable{
    private ArrayList<GUI_Shapeable> _shapes;

    public ShapeCollection() {
        _shapes = new ArrayList<>();
    }
    @Override
    public GUI_Shapeable get(int i) {
        return _shapes.get(i);
    }

    @Override
    public int size() {
        return _shapes.size();
    }

    @Override
    public GUI_Shapeable removeElementAt(int i) {
        //////////add your code below ///////////
        _shapes.remove(i);
        return _shapes.get(i);
        //////////////////////////////////////////
    }

    @Override
    public void addAt(GUI_Shapeable s, int i) {
        //////////add your code below ///////////
        _shapes.add(i,s);
        //////////////////////////////////////////
    }
    @Override
    public void add(GUI_Shapeable s) {
        if(s!=null && s.getShape()!=null) {
            _shapes.add(s);
        }
    }

//    public void addAll(ShapeCollectionable c) {
//        for (int i = 0; i < c.size(); ++i) {
//            _shapes.add(c.get(i));
//        }
//    }
    @Override
    public ShapeCollectionable copy() {
        //////////add your code below ///////////
        ShapeCollection _shapes_copy = new ShapeCollection();
        for (GUI_Shapeable shape : _shapes) {
            _shapes_copy.add(shape);
        }
        return _shapes_copy;
        //////////////////////////////////////////
    }

    @Override
    public void sort(Comparator<GUI_Shapeable> comp) {
        //////////add your code below ///////////
        _shapes.sort(comp);
        //////////////////////////////////////////
    }

    @Override
    public void removeAll() {
        //////////add your code below ///////////
        _shapes.clear();
        //////////////////////////////////////////
    }

    @Override
    public void save(String file) {
        //////////add your code below ///////////
        try {
            FileWriter saved_file = new FileWriter(file);
            for (GUI_Shapeable shape : _shapes) {
                saved_file.append(shape.toString()).append(String.valueOf('\n'));
            }
            saved_file.close();
        } catch (IOException e) {
            System.err.println("ERROR: Could not open \"" + file + "\" for writing: " + e.getMessage());
            System.err.println("Looked in " + System.getProperty("user.dir"));
        }
    }
        //////////////////////////////////////////


    @Override
    public void load(String file) {
        ////////// add your code below ///////////
        try {
            File saved_file = new File(file);
            Scanner myReader = new Scanner(saved_file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //////////////////////////////////////////
    }
    @Override
    public Rect2D getBoundingBox() {
        Rect2D ans;
        //////////add your code below ///////////
        double min_x = _shapes.get(0).getShape().getPoints()[0].x();
        double min_y = _shapes.get(0).getShape().getPoints()[0].y();
        double max_x = _shapes.get(0).getShape().getPoints()[0].x();
        double max_y = _shapes.get(0).getShape().getPoints()[0].y();
        
        Point2D point;
        for (GUI_Shapeable shape : _shapes) {
            for (int i = 0; i < shape.getShape().getPoints().length; i++) {
                point = shape.getShape().getPoints()[i];
                if (point.y() > max_y)
                    max_y = point.y();

                if (point.y() < min_y)
                    min_y = point.y();

                if (point.x() < min_x)
                    min_x = point.x();

                if (point.x() < min_x)
                    min_x = point.x();
            }
        }
        ans = new Rect2D(new Point2D(min_x,min_y), new Point2D(max_x,max_y));
        return ans;
    }

    @Override
    public String toString() {
        String ans = "";
        for(int i=0;i<size();i=i+1) {
            ans += this.get(i);
        }
        return ans;
    }
}

