package cs355.view.drawable;

import cs355.model.drawing.Ellipse;
import cs355.model.drawing.Shape;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Created by allenliao on 1/24/17.
 */
public class DrawableEllipse extends DrawableShape {

    private double height;
    private double width;
    private Point2D center;
    private Point2D up_left;
    private double rotation;

    public DrawableEllipse(Shape s) {
        super(s);
        height = ((Ellipse)s).getHeight();
        width = ((Ellipse)s).getWidth();
        center = ((Ellipse)s).getCenter();
        rotation = ((Ellipse)s).getRotation();
        create_up_left();
    }

    @Override
    public void draw(Graphics2D g2d){
        g2d.setColor(getColor());
        //g2d.fillOval((int)up_left.getX(), (int)up_left.getY(), (int)width, (int)height);
        AffineTransform objToWorld = new AffineTransform();
        objToWorld.translate(center.getX(), center.getY());
        objToWorld.rotate(rotation);
        g2d.setTransform(objToWorld);
        g2d.fillOval((int)-width/2, (int)-height/2, (int)width, (int)height);
    }

    @Override
    public void draw_handle(Graphics2D g2d){
        AffineTransform objToWorld = new AffineTransform();
        objToWorld.translate(center.getX(), center.getY());
        objToWorld.rotate(rotation);
        g2d.setTransform(objToWorld);

        g2d.setColor(Color.CYAN);
        g2d.drawOval((int)-width/2, (int)-height/2, (int)width, (int)height);

        g2d.drawOval(-3, (int)-(height/2 + 23), (int)5*2, (int)5*2);

    }

    public void create_up_left(){
        double x = center.getX();
        double y = center.getY();

        x -= width/2;
        y -= height/2;
        if (x < 0){
            x = 0;
        }
        if (y < 0){
            y = 0;
        }

        up_left = new Point2D.Double(x,y);
    }

}
