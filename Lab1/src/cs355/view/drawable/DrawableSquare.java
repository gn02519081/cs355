package cs355.view.drawable;

import cs355.model.drawing.Shape;
import cs355.model.drawing.Square;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Created by allenliao on 1/24/17.
 */
public class DrawableSquare extends DrawableShape {

    private Point2D up_left;
    private double length;
    private Point2D.Double center;
    private double rotation;

    public DrawableSquare(Shape s) {
        super(s);
        length = ((Square)s).getSize();
        center = ((Square)s).getCenter();
        rotation = ((Square)s).getRotation();
        up_left = new Point2D.Double(((Square)s).getCenter().getX()-length/2, ((Square)s).getCenter().getY()-length/2);

    }


    @Override
    public void draw(Graphics2D g2d){
        g2d.setColor(getColor());
        //g2d.fillRect((int)up_left.getX(), (int)up_left.getY(), (int)length, (int)length);

        AffineTransform objToWorld = new AffineTransform();
        objToWorld.translate(center.getX(), center.getY());
        objToWorld.rotate(rotation);
        g2d.setTransform(objToWorld);
        g2d.fillRect((int)-length/2, (int)-length/2, (int)length, (int)length);

    }

    @Override
    public void draw_handle(Graphics2D g2d){
        AffineTransform objToWorld = new AffineTransform();
        objToWorld.translate(center.getX(), center.getY());
        objToWorld.rotate(rotation);
        g2d.setTransform(objToWorld);

        g2d.setColor(Color.CYAN);
        g2d.drawRect((int)-length/2, (int)-length/2, (int)length, (int)length);

        g2d.drawOval(-3, (int)-(length/2 + 23), (int)5*2, (int)5*2);
    }
}
