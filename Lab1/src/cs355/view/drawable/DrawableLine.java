package cs355.view.drawable;

import cs355.model.drawing.Line;
import cs355.model.drawing.Shape;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Created by allenliao on 1/23/17.
 */
public class DrawableLine extends DrawableShape {

    private Point2D start_point;
    private Point2D end_point;
    private double rotation;


    public DrawableLine(Shape s) {
        super(s);
        start_point = ((Line)s).getCenter();
        end_point = ((Line)s).getEnd();
        rotation = ((Line)s).getRotation();
    }

    @Override
    public void draw(Graphics2D g2d){
        //System.out.println(start_point);
        g2d.setColor(getColor());
        //g2d.drawLine((int)start_point.getX(), (int)start_point.getY(), (int)end_point.getX(), (int)end_point.getY());
        AffineTransform objToWorld = new AffineTransform();
        objToWorld.translate(start_point.getX(), start_point.getY());
        objToWorld.rotate(rotation);
        g2d.setTransform(objToWorld);
        g2d.drawLine((int) (end_point.getX()-start_point.getX()), (int)(end_point.getY()- start_point.getY()), 0, 0);

        System.out.println("start: " + start_point);
        System.out.println("end: " + end_point);

    }

    @Override
    public void draw_handle(Graphics2D g2d){

        AffineTransform objToWorld = new AffineTransform();
        objToWorld.translate(start_point.getX(), start_point.getY());
        objToWorld.rotate(rotation);
        g2d.setTransform(objToWorld);

        g2d.setColor(Color.CYAN);

        g2d.drawOval((int)end_point.getX()-(int)start_point.getX() - 5, (int)end_point.getY() - (int)start_point.getY() - 5, (int)5*2, (int)5*2);
        g2d.drawOval((int)start_point.getX()-(int)start_point.getX() - 5, (int)start_point.getY()- (int)start_point.getY() - 5, (int)5*2, (int)5*2);

    }
}
