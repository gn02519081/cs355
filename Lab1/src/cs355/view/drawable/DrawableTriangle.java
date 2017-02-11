package cs355.view.drawable;

import cs355.model.drawing.Shape;
import cs355.model.drawing.Triangle;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Created by allenliao on 1/24/17.
 */
public class DrawableTriangle extends DrawableShape {

    private Point2D point_a;
    private Point2D point_b;
    private Point2D point_c;
    private Point2D center;
    private double rotation;

    public DrawableTriangle(Shape s) {
        super(s);
        point_a = ((Triangle) s).getA();
        point_b = ((Triangle) s).getB();
        point_c = ((Triangle) s).getC();
        center = ((Triangle) s).getCenter();
        //center = new Point2D.Double((point_a.getX()+point_b.getX()+point_c.getX())/3, (point_a.getY()+point_b.getY()+point_c.getY())/3);
        rotation = ((Triangle) s).getRotation();
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(getColor());

        int points = 3;
        int[] x = new int[points];
        int[] y = new int[points];

        x[0] = (int) point_a.getX();
        y[0] = (int) point_a.getY();

        x[1] = (int) point_b.getX();
        y[1] = (int) point_b.getY();

        x[2] = (int) point_c.getX();
        y[2] = (int) point_c.getY();

        //g2d.fillPolygon(x, y, points);
        AffineTransform objToWorld = new AffineTransform();
        objToWorld.translate(center.getX(), center.getY());
        objToWorld.rotate(rotation);
        g2d.setTransform(objToWorld);
        g2d.fillPolygon(x, y, points);
        //System.out.println("In drawable: x->" + x.toString() + " y->" + y.toString());

    }

    @Override
    public void draw_handle(Graphics2D g2d) {

        int points = 3;
        int[] x = new int[points];
        int[] y = new int[points];

        x[0] = (int) point_a.getX();
        y[0] = (int) point_a.getY();

        x[1] = (int) point_b.getX();
        y[1] = (int) point_b.getY();

        x[2] = (int) point_c.getX();
        y[2] = (int) point_c.getY();

        AffineTransform objToWorld = new AffineTransform();
        objToWorld.translate(center.getX(), center.getY());
        objToWorld.rotate(rotation);
        g2d.setTransform(objToWorld);

        g2d.setColor(Color.CYAN);

        g2d.drawPolygon(x, y, points);
        double max_Y = Math.max(point_a.getY(), point_b.getY());
        max_Y = Math.max(max_Y, point_c.getY());


        g2d.drawOval(-3, (int)-(max_Y + 23), (int)5*2, (int)5*2);

    }
}
