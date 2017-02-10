package cs355.view.drawable;

import cs355.model.drawing.Rectangle;
import cs355.model.drawing.Shape;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Created by allenliao on 1/24/17.
 */
public class DrawableRectangle extends DrawableShape {

    private Point2D up_left;
    private double height;
    private double width;
    private Point2D.Double center;
    private double rotation;

    public DrawableRectangle(Shape s) {
        super(s);

        height = ((Rectangle)s).getHeight();
        width = ((Rectangle)s).getWidth();
        center = ((Rectangle)s).getCenter();
        rotation = ((Rectangle)s).getRotation();

        up_left = new Point2D.Double(((Rectangle)s).getCenter().getX()-width/2, ((Rectangle)s).getCenter().getY()-height/2);
    }

    @Override
    public void draw(Graphics2D g2d){
        g2d.setColor(getColor());
        //g2d.fillRect((int)up_left.getX(), (int)up_left.getY(), (int)width, (int)height);
        AffineTransform objToWorld = new AffineTransform();
        objToWorld.translate(center.getX(), center.getY());
        objToWorld.rotate(rotation);
        g2d.setTransform(objToWorld);
        g2d.fillRect((int)-width/2, (int)-height/2, (int)width, (int)height);
    }
    @Override
    public void draw_handle(Graphics2D g2d){
        AffineTransform objToWorld = new AffineTransform();
        objToWorld.translate(center.getX(), center.getY());
        objToWorld.rotate(rotation);
        g2d.setTransform(objToWorld);

        g2d.setColor(Color.CYAN);
        g2d.drawRect((int)-width/2, (int)-height/2, (int)width, (int)height);
    }
}
