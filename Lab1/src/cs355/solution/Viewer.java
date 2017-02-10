package cs355.solution;

import cs355.GUIFunctions;
import cs355.model.drawing.*;
import cs355.model.drawing.Rectangle;
import cs355.view.ViewRefresher;
import cs355.view.drawable.*;

import java.awt.*;
import java.awt.Shape;
import java.util.*;
import java.util.List;

/**
 * Created by allenliao on 1/22/17.
 */
public class Viewer implements ViewRefresher {

    private Drawing drawing;
    private List<DrawableShape> drawableShapeList;
    private DrawableShape shapeSelected = null;

    public Viewer(){
        drawing = Drawing.getSINGLETON();
        Drawing.SINGLETON.addObserver(this);
        drawableShapeList = new ArrayList<DrawableShape>();
    }

    @Override
    public void refreshView(Graphics2D g2d) {
        loadDrawableShapes();

        for (DrawableShape s: drawableShapeList){
            s.draw(g2d);
            //s.draw_handle(g2d);
        }

        if (shapeSelected != null){
            shapeSelected.draw_handle(g2d);
        }
    }

    @Override
    public void update(Observable o, Object arg) {

        GUIFunctions.refresh();

    }


    public void loadDrawableShapes(){
        drawableShapeList = new ArrayList<DrawableShape>();
        List<cs355.model.drawing.Shape> shapes = Drawing.SINGLETON.getShapes();
        shapeSelected = null;
        if (Drawing.SINGLETON.getShapeSelected() != null){
            shapeSelected = convertShape(Drawing.SINGLETON.getShapeSelected());
        }

        for (cs355.model.drawing.Shape s: shapes){
            DrawableShape temp_drawableShape = convertShape(s);
            drawableShapeList.add(temp_drawableShape);
        }
    }


    public DrawableShape convertShape(cs355.model.drawing.Shape s){

        DrawableShape temp_drawableShape = null;
        if (s instanceof Line){
            temp_drawableShape = new DrawableLine((cs355.model.drawing.Shape) s);
        }else if (s instanceof Square){
            temp_drawableShape = new DrawableSquare(s);
        }else if (s instanceof Rectangle){
            temp_drawableShape = new DrawableRectangle(s);
        }else if (s instanceof  Triangle){
            temp_drawableShape = new DrawableTriangle(s);
        }else if (s instanceof  Circle){
            temp_drawableShape = new DrawableCircle(s);
        }else if (s instanceof  Ellipse){
            temp_drawableShape = new DrawableEllipse(s);
        }

        return temp_drawableShape;
    }




}

// convert shapes to g2d types and draw it. setcolor
// check circle -> eclipse2d.double, square or rectangle - > rectangle2D.double. line -> line2d, triangle -> polygon