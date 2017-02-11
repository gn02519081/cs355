package cs355.view.drawable;

import cs355.model.drawing.Circle;
import cs355.model.drawing.Shape;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Created by allenliao on 1/24/17.
 */
public class DrawableCircle extends DrawableShape {

    private double radius;
    private Point2D up_left;
    private Point2D center;
    private double rotation;

    public DrawableCircle(Shape s) {
        super(s);
        radius = ((Circle)s).getRadius();
        center = ((Circle)s).getCenter();
        rotation = ((Circle)s).getRotation();
        create_up_left();
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(getColor());
        //g2d.fillOval((int)up_left.getX(), (int)up_left.getY(), (int)(radius*2), (int)(radius*2));

        AffineTransform objToWorld = new AffineTransform();
        objToWorld.translate(center.getX(), center.getY());
        objToWorld.rotate(rotation);
        g2d.setTransform(objToWorld);
        g2d.fillOval((int)-radius, (int)-radius, (int)radius*2, (int)radius*2);
    }

    @Override
    public void draw_handle(Graphics2D g2d){
        AffineTransform objToWorld = new AffineTransform();
        objToWorld.translate(center.getX(), center.getY());
        objToWorld.rotate(rotation);
        g2d.setTransform(objToWorld);

        g2d.setColor(Color.CYAN);
        g2d.drawOval((int)-radius, (int)-radius, (int)radius*2, (int)radius*2);

        g2d.drawOval(-3, (int)-(radius + 23), (int)5*2, (int)5*2);

    }

    public void create_up_left(){
        double x = center.getX();
        double y = center.getY();

        x -= radius;
        y -= radius;
        if (x < 0){
            x = 0;
        }
        if (y < 0){
            y = 0;
        }

        up_left = new Point2D.Double(x,y);
    }
}
