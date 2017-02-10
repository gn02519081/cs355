package cs355.solution;

import cs355.model.drawing.*;
import cs355.model.drawing.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by allenliao on 1/22/17.
 */
public class Drawing extends CS355Drawing {

    private List<cs355.model.drawing.Shape> shapes ;
    private int currentShapeIndex;
    private Color currentColor;
    private int shapeCounts;
    private Shape shapeSelected = null;



    public static final Drawing SINGLETON = new Drawing();

    private Drawing(){
        shapes = new ArrayList<cs355.model.drawing.Shape>();
        currentShapeIndex = -1;
        currentColor = Color.WHITE;

    }

    public void updateView(){
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public cs355.model.drawing.Shape getShape(int index) {
        //updateView();
        return shapes.get(index);
    }

    @Override
    public int addShape(cs355.model.drawing.Shape s) {
        shapes.add(s);
        updateView();
        return 0;
    }

    @Override
    public void deleteShape(int index) {
        shapes.remove(index);
    }

    @Override
    public void moveToFront(int index) {

        cs355.model.drawing.Shape shape_temp = shapes.get(index);
        shapes.remove(index);
        shapes.add(shape_temp);
        currentShapeIndex = shapes.size()-1;

    }

    @Override
    public void movetoBack(int index) {

        cs355.model.drawing.Shape shape_temp = shapes.get(index);
        shapes.remove(index);
        shapes.add(0,shape_temp);
        currentShapeIndex = 0;
    }

    @Override
    public void moveForward(int index) {
        if (index == shapes.size()-1){
            return;
        }
        cs355.model.drawing.Shape shape_temp = shapes.get(index);
        shapes.remove(index);
        shapes.add(index+1, shape_temp);
        currentShapeIndex = index+1;
    }

    @Override
    public void moveBackward(int index) {
        if (index == 0){
            return;
        }
        cs355.model.drawing.Shape shape_temp = shapes.get(index);
        shapes.remove(index);
        shapes.add(index-1, shape_temp);
        currentShapeIndex = index-1;
    }

    @Override
    public List<cs355.model.drawing.Shape> getShapes() {
        //updateView();
        return shapes;
    }

    @Override
    public List<cs355.model.drawing.Shape> getShapesReversed() {
        ArrayList<cs355.model.drawing.Shape> shapes_temp = new ArrayList<cs355.model.drawing.Shape>(shapes);
        Collections.reverse(shapes_temp);
        //updateView();
        return shapes_temp;
    }

    @Override
    public void setShapes(List<cs355.model.drawing.Shape> shapes) {
        this.shapes = shapes;
        updateView();
    }

    public int getCurrentShapeIndex() {
        return currentShapeIndex;
    }

    public void setCurrentShapeIndex(int currentShapeIndex) {
        this.currentShapeIndex = currentShapeIndex;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public static Drawing getSINGLETON() {
        return SINGLETON;
    }

    public int getShapeCounts() {
        return shapes.size();
    }
    public Shape getShapeSelected() {
        return shapeSelected;
    }

    public void setShapeSelected(Shape shapeSelected) {
        this.shapeSelected = shapeSelected;
    }
}
