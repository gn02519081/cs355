package cs355.solution;

import cs355.GUIFunctions;
import cs355.controller.CS355Controller;
import cs355.model.drawing.*;
import cs355.model.drawing.Rectangle;
import cs355.model.drawing.Shape;
import cs355.solution.Drawing;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.Iterator;

import static cs355.solution.Controller.State.Line;
import static cs355.solution.Controller.State.Selection;
import static cs355.solution.Controller.State.Square;

/**
 * Created by allenliao on 1/22/17.
 */
public class Controller implements CS355Controller {

//create enum for shape enum state = {Line, ......}

    // GUI function change color


    public Controller() {
    }

    public enum State{
        Line, Circle, Ellipse, Rectangle, Square, Triangle, Selection
    }

    private Color currentColor = Color.WHITE;
    private State currentState;
    private Shape shapeSelected;
    private Point2D.Double start_point;
    //private Point2D.Double end_point;
    private Point2D.Double T_point_1 = null;
    private Point2D.Double T_point_2 = null;
    private Point2D.Double T_point_3 = null;
    private int currentIndex = -1;
    private Point2D.Double lastDrag;
    private boolean dragHandleSelected = false;
    @Override
    public void colorButtonHit(Color c) {

        currentColor = c;
        Drawing.SINGLETON.setCurrentColor(currentColor);
        GUIFunctions.changeSelectedColor(currentColor);

        if (currentState == Selection && shapeSelected != null){
            shapeSelected.setColor(currentColor);
        }
    }

    @Override
    public void lineButtonHit() {
        currentState = State.Line;
        Drawing.SINGLETON.setShapeSelected(null);
        Drawing.SINGLETON.updateView();
        shapeSelected = null;
    }

    @Override
    public void squareButtonHit() {
        currentState = State.Square;
        Drawing.SINGLETON.setShapeSelected(null);
        Drawing.SINGLETON.updateView();
        shapeSelected = null;

    }

    @Override
    public void rectangleButtonHit() {
        currentState = State.Rectangle;
        Drawing.SINGLETON.setShapeSelected(null);
        Drawing.SINGLETON.updateView();
        shapeSelected = null;

    }

    @Override
    public void circleButtonHit() {
        currentState = State.Circle;
        Drawing.SINGLETON.setShapeSelected(null);
        Drawing.SINGLETON.updateView();
        shapeSelected = null;

    }

    @Override
    public void ellipseButtonHit() {
        currentState = State.Ellipse;
        Drawing.SINGLETON.setShapeSelected(null);
        Drawing.SINGLETON.updateView();
        shapeSelected = null;

    }

    @Override
    public void triangleButtonHit() {
        currentState = State.Triangle;
        T_point_1 = null;
        T_point_2 = null;
        T_point_3 = null;
        shapeSelected = null;
        Drawing.SINGLETON.setShapeSelected(null);
        Drawing.SINGLETON.updateView();

    }

    @Override
    public void selectButtonHit() {
        currentState = State.Selection;
        dragHandleSelected = false;
        shapeSelected = null;
    }

    @Override
    public void zoomInButtonHit() {

    }

    @Override
    public void zoomOutButtonHit() {

    }

    @Override
    public void hScrollbarChanged(int value) {

    }

    @Override
    public void vScrollbarChanged(int value) {

    }

    @Override
    public void openScene(File file) {

    }

    @Override
    public void toggle3DModelDisplay() {

    }

    @Override
    public void keyPressed(Iterator<Integer> iterator) {

    }

    @Override
    public void openImage(File file) {

    }

    @Override
    public void saveImage(File file) {

    }

    @Override
    public void toggleBackgroundDisplay() {

    }

    @Override
    public void saveDrawing(File file) {
        Drawing.SINGLETON.save(file);
    }

    @Override
    public void openDrawing(File file) {
        Drawing.SINGLETON.open(file);
    }

    @Override
    public void doDeleteShape() {
        if (currentIndex > -1) {
            Drawing.SINGLETON.deleteShape(currentIndex);
            currentIndex = -1;
            shapeSelected = null;
            Drawing.SINGLETON.setShapeSelected(null);
            Drawing.SINGLETON.updateView();
        }
    }

    @Override
    public void doEdgeDetection() {

    }

    @Override
    public void doSharpen() {

    }

    @Override
    public void doMedianBlur() {

    }

    @Override
    public void doUniformBlur() {

    }

    @Override
    public void doGrayscale() {

    }

    @Override
    public void doChangeContrast(int contrastAmountNum) {

    }

    @Override
    public void doChangeBrightness(int brightnessAmountNum) {

    }

    @Override
    public void doMoveForward() {
        Drawing.SINGLETON.moveForward(currentIndex);
        currentIndex = Drawing.SINGLETON.getCurrentShapeIndex();
        Drawing.SINGLETON.setCurrentColor(currentColor);
        Drawing.SINGLETON.setShapeSelected(shapeSelected);
        Drawing.SINGLETON.updateView();
    }

    @Override
    public void doMoveBackward() {
        Drawing.SINGLETON.moveBackward(currentIndex);
        currentIndex = Drawing.SINGLETON.getCurrentShapeIndex();
        Drawing.SINGLETON.setCurrentColor(currentColor);
        Drawing.SINGLETON.setShapeSelected(shapeSelected);
        Drawing.SINGLETON.updateView();
    }

    @Override
    public void doSendToFront() {
        if (currentIndex > -1) {
            Drawing.SINGLETON.moveToFront(currentIndex);
            currentIndex = Drawing.SINGLETON.getCurrentShapeIndex();
            Drawing.SINGLETON.setCurrentColor(currentColor);
            Drawing.SINGLETON.setShapeSelected(shapeSelected);
            Drawing.SINGLETON.updateView();
        }
    }

    @Override
    public void doSendtoBack() {
        if (currentIndex > -1){
            Drawing.SINGLETON.movetoBack(currentIndex);
            currentIndex = Drawing.SINGLETON.getCurrentShapeIndex();
            Drawing.SINGLETON.setCurrentColor(currentColor);
            Drawing.SINGLETON.setShapeSelected(shapeSelected);
            Drawing.SINGLETON.updateView();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("Mouse Click");

        if (currentState == State.Triangle){
            Point2D.Double point = new Point2D.Double(e.getX(),e.getY());

            if (T_point_1 == null){
                T_point_1 = point;
            }else if (T_point_2 == null){
                T_point_2 = point;
            }else if (T_point_3 == null){
                T_point_3 = point;
                Point2D.Double center = new Point2D.Double((T_point_1.getX()+T_point_2.getX()+T_point_3.getX())/3 , (T_point_1.getY()+T_point_2.getY()+T_point_3.getY())/3);
                shapeSelected = new Triangle(currentColor, center, T_point_1, T_point_2, T_point_3);
                ((Triangle)shapeSelected).resetCorner();
                System.out.println("p1: " + T_point_1.toString() + "p2: " + T_point_2.toString() + "p3: " + T_point_3.toString() + "Center: " +center.toString());
                Drawing.SINGLETON.addShape(shapeSelected);
                Drawing.SINGLETON.updateView();
                T_point_1 = null;
                T_point_2 = null;
                T_point_3 = null;
            }
        }
    }




    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("Mouse Pressed");


        Point2D.Double point = new Point2D.Double(e.getX(),e.getY());
        start_point = point;

        switch (currentState){
            case Line:
                shapeSelected = new Line(currentColor, point, point);
                lastDrag = null;
                Drawing.SINGLETON.addShape(shapeSelected);
                break;
            case Circle:
                shapeSelected = new Circle(currentColor, point, 0);
                Drawing.SINGLETON.addShape(shapeSelected);
                break;
            case Ellipse:
                shapeSelected = new Ellipse(currentColor, point, 0, 0);
                Drawing.SINGLETON.addShape(shapeSelected);
                break;
            case Rectangle:
                shapeSelected = new Rectangle(currentColor, point, 0, 0);
                Drawing.SINGLETON.addShape(shapeSelected);
                break;
            case Square:
                shapeSelected = new Square(currentColor, point, 0);
                Drawing.SINGLETON.addShape(shapeSelected);
                break;
            case Selection:

                if (shapeSelected != null){
                    dragHandleSelected = shapeSelected.pointInHandle(point, 5);
                }

                if (!dragHandleSelected) {
                    //System.out.println(shapeSelected);
                    for (int i = Drawing.SINGLETON.getShapeCounts() - 1; i > -1; i--) {
                        //System.out.println("go through shape: " + i);
                        Shape temp_shape = Drawing.SINGLETON.getShapes().get(i);
                        if (temp_shape.pointInShape(point, 0)) {
                            shapeSelected = temp_shape;
                            currentIndex = i;
                            Drawing.SINGLETON.setCurrentShapeIndex(currentIndex);
                            currentColor = shapeSelected.getColor();
                            Drawing.SINGLETON.setCurrentColor(currentColor);
                            GUIFunctions.changeSelectedColor(currentColor);
                            Drawing.SINGLETON.setShapeSelected(shapeSelected);
                            Drawing.SINGLETON.updateView();
                            System.out.println(currentIndex);
                            break;
                        }
                    }
                    if (shapeSelected != null) {
                        if (!shapeSelected.pointInShape(point, 0)) {
                            Drawing.SINGLETON.setShapeSelected(null);
                            Drawing.SINGLETON.updateView();
                            shapeSelected = null;
                        }
                    }
                }
//
//
//            case Triangle:
//                shapeSelected = new Triangle(currentColor, point, point, point);
//                Drawing.SINGLETON.addShape(shapeSelected);
//                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        //System.out.println("Mouse Released");

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        //System.out.println("Mouse Dragged");

        //double x = e.getX();
        //double y = e.getY();
        Point2D.Double point = new Point2D.Double(e.getX(),e.getY());
       // if (point.getX() > 0 || point.getY() > 0) {
            switch (currentState) {
                case Line:
                    ((Line) shapeSelected).setEnd(point);
//                    System.out.println("center: " + shapeSelected.getCenter());
//                    System.out.println("end: " + ((Line) shapeSelected).getEnd());
                    Drawing.SINGLETON.updateView();
                    break;
                case Circle:
                    editCircle(e, (Circle) shapeSelected);
                    Drawing.SINGLETON.updateView();
                    break;
                case Ellipse:
                    editEllipse(e, (Ellipse) shapeSelected);
                    Drawing.SINGLETON.updateView();
                    break;
                case Rectangle:
                    editRectangle(e, (Rectangle) shapeSelected);
                    Drawing.SINGLETON.updateView();
                    break;
                case Square:
                    editSquare(e, (Square) shapeSelected);
                    Drawing.SINGLETON.updateView();
                    break;
//                case Triangle:
//                    break;
                case Selection:
                    if (dragHandleSelected && shapeSelected.pointInHandle(point, 5)){
                        rotateShape(e);
                    }
                    else if (shapeSelected.pointInShape(point, 0)) {
                        if (shapeSelected instanceof  Line){
                            if (lastDrag == null){
                                lastDrag = point;
                            }
                            moveLine(e, (Line)shapeSelected);
                        }else {
                            shapeSelected.setCenter(point);
                        }
                        Drawing.SINGLETON.updateView();
                    }
            }
       // }

    }



    public void rotateShape(MouseEvent e){
        Point2D center = shapeSelected.getCenter();
        Point2D rotatePoint = e.getPoint();
        Point2D difference = new Point2D.Double(rotatePoint.getX() - center.getX(), rotatePoint.getY() - center.getY());
        double differenceAngle = Math.atan2(difference.getY(), difference.getX()) + Math.PI/2;
        shapeSelected.setRotation(differenceAngle);
        System.out.println(differenceAngle);
        Drawing.SINGLETON.updateView();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public  void moveLine(MouseEvent e, Line s){
        double x = e.getX() - lastDrag.getX();
        double y = e.getY() - lastDrag.getY();
        Point2D.Double new_center = new Point2D.Double();
        Point2D.Double new_end = new Point2D.Double();

        if (x > 0){
            if (y > 0){
                new_center.setLocation(s.getCenter().getX()+Math.abs(x), s.getCenter().getY()+Math.abs(y));
                new_end.setLocation(s.getEnd().getX()+Math.abs(x), s.getEnd().getY()+Math.abs(y));
            }else{
                new_center.setLocation(s.getCenter().getX()+Math.abs(x), s.getCenter().getY()-Math.abs(y));
                new_end.setLocation(s.getEnd().getX()+Math.abs(x), s.getEnd().getY()-Math.abs(y));
            }
        }else{
            if (y > 0){
                new_center.setLocation(s.getCenter().getX()-Math.abs(x), s.getCenter().getY()+Math.abs(y));
                new_end.setLocation(s.getEnd().getX()-Math.abs(x), s.getEnd().getY()+Math.abs(y));
            }else{
                new_center.setLocation(s.getCenter().getX()-Math.abs(x), s.getCenter().getY()-Math.abs(y));
                new_end.setLocation(s.getEnd().getX()-Math.abs(x), s.getEnd().getY()-Math.abs(y));
            }
        }
        s.setCenter(new_center);
        s.setEnd(new_end);
        lastDrag = new Point2D.Double(e.getX(), e.getY());

    }

    public void editSquare(MouseEvent e, Square s){

        Point2D.Double dragPoint = new Point2D.Double(e.getX(),e.getY());

        double height = dragPoint.getY() - start_point.getY();
        double width = dragPoint.getX() - start_point.getX();
        double length = Math.min(Math.abs(height), Math.abs(width));

        s.setSize(length);


        if (width > 0){
            if (height > 0){
                Point2D.Double center = new Point2D.Double(start_point.getX()+length/2, start_point.getY()+length/2);
                s.setCenter(center);
            }else{
                Point2D.Double center = new Point2D.Double(start_point.getX()+length/2, start_point.getY()-length/2);
                s.setCenter(center);
            }
        }else{
            if (height > 0){
                Point2D.Double center = new Point2D.Double(start_point.getX()-length/2, start_point.getY()+length/2);
                s.setCenter(center);

            }else{
                Point2D.Double center = new Point2D.Double(start_point.getX()-length/2, start_point.getY()-length/2);
                s.setCenter(center);
            }
        }
    }

    public void editRectangle(MouseEvent e, Rectangle s){

        Point2D.Double dragPoint = new Point2D.Double(e.getX(), e.getY());

        double height = dragPoint.getY() - start_point.getY();
        double width = dragPoint.getX() - start_point.getX();

        s.setHeight(Math.abs(height));
        s.setWidth(Math.abs(width));

        if (width > 0){
            if (height > 0){
                Point2D.Double center = new Point2D.Double(start_point.getX() + Math.abs(width)/2, start_point.getY() + Math.abs(height)/2);
                s.setCenter(center);
            }else{
                Point2D.Double center = new Point2D.Double(start_point.getX() + Math.abs(width)/2, start_point.getY() - Math.abs(height)/2);
                s.setCenter(center);
            }
        }else{
            if (height > 0){
                Point2D.Double center = new Point2D.Double(start_point.getX() - Math.abs(width)/2, start_point.getY() + Math.abs(height)/2);
                s.setCenter(center);

            }else{
                Point2D.Double center = new Point2D.Double(start_point.getX() - Math.abs(width)/2, start_point.getY() - Math.abs(height)/2);
                s.setCenter(center);
            }
        }
    }

    public void editCircle(MouseEvent e, Circle s){

        Point2D.Double dragPoint = new Point2D.Double(e.getX(), e.getY());
        double height = dragPoint.getY() - start_point.getY();
        double width = dragPoint.getX() - start_point.getX();

        double diameter = Math.min(Math.abs(height), Math.abs(width));
        double radius = diameter/2;

        s.setRadius(radius);
        Point2D.Double center = new Point2D.Double();

        if (width > 0){
            if (height > 0){
                center.setLocation(start_point.getX()+radius, start_point.getY()+radius);
                s.setCenter(center);
            }else{
                center.setLocation(start_point.getX()+radius, start_point.getY()-radius);
                s.setCenter(center);
            }
        }else{
            if (height > 0){
                center.setLocation(start_point.getX()-radius, start_point.getY()+radius);
                s.setCenter(center);

            }else{
                //System.out.printf(start_point.toString());
                center.setLocation(start_point.getX()-radius, start_point.getY()-radius);
                s.setCenter(center);
                //System.out.printf(center.toString());
            }
        }

    }

    public void editEllipse(MouseEvent e, Ellipse s){
        Point2D.Double dragPoint = new Point2D.Double(e.getX(), e.getY());

        double height = dragPoint.getY() - start_point.getY();
        double width = dragPoint.getX() - start_point.getX();

        s.setHeight(Math.abs(height));
        s.setWidth(Math.abs(width));

        Point2D.Double center = new Point2D.Double();

        if (width > 0){
            if (height > 0){
                center.setLocation(start_point.getX()+Math.abs(width)/2, start_point.getY()+Math.abs(height)/2);
                s.setCenter(center);
            }else{
                center.setLocation(start_point.getX()+Math.abs(width)/2, start_point.getY()-Math.abs(height)/2);
                s.setCenter(center);
            }
        }else{
            if (height > 0){
                center.setLocation(start_point.getX()-Math.abs(width)/2, start_point.getY()+Math.abs(height)/2);
                s.setCenter(center);
            }else{
                center.setLocation(start_point.getX()-Math.abs(width)/2, start_point.getY()-Math.abs(height)/2);
                s.setCenter(center);
            }
        }
    }

}
