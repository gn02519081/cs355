package cs355.model.drawing;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Add your triangle code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Triangle extends Shape {

	// The three points of the triangle.
	private Point2D.Double a;
	private Point2D.Double b;
	private Point2D.Double c;

	/**
	 * Basic constructor that sets all fields.
	 * @param color the color for the new shape.
	 * @param center the center of the new shape.
	 * @param a the first point, relative to the center.
	 * @param b the second point, relative to the center.
	 * @param c the third point, relative to the center.
	 */
	public Triangle(Color color, Point2D.Double center, Point2D.Double a,
					Point2D.Double b, Point2D.Double c)
	{

		// Initialize the superclass.
		super(color, center);

		// Set fields.
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	 * Getter for the first point.
	 * @return the first point as a Java point.
	 */
	public Point2D.Double getA() {
		return a;
	}

	/**
	 * Setter for the first point.
	 * @param a the new first point.
	 */
	public void setA(Point2D.Double a) {
		this.a = a;
	}

	/**
	 * Getter for the second point.
	 * @return the second point as a Java point.
	 */
	public Point2D.Double getB() {
		return b;
	}

	/**
	 * Setter for the second point.
	 * @param b the new second point.
	 */
	public void setB(Point2D.Double b) {
		this.b = b;
	}

	/**
	 * Getter for the third point.
	 * @return the third point as a Java point.
	 */
	public Point2D.Double getC() {
		return c;
	}

	/**
	 * Setter for the third point.
	 * @param c the new third point.
	 */
	public void setC(Point2D.Double c) {
		this.c = c;
	}


	public void resetCorner(){
		setA(new Point2D.Double(a.getX()-center.getX(), a.getY()-center.getY()));
		setB(new Point2D.Double(b.getX()-center.getX(), b.getY()-center.getY()));
		setC(new Point2D.Double(c.getX()-center.getX(), c.getY()-center.getY()));

	}



	/**
	 * Add your code to do an intersection test
	 * here. You shouldn't need the tolerance.
	 * @param pt = the point to test against.
	 * @param tolerance = the allowable tolerance.
	 * @return true if pt is in the shape,
	 *		   false otherwise.
	 */
	@Override
	public boolean pointInShape(Point2D.Double pt, double tolerance) {
		//throw new UnsupportedOperationException("Not supported yet.");
		Point2D.Double objectCoordinate = new Point2D.Double();
		AffineTransform worldToObject = new AffineTransform();
		worldToObject.rotate(-rotation);
		worldToObject.translate(-center.getX(), -center.getY());
		worldToObject.transform(pt, objectCoordinate);

		boolean b1, b2, b3;
		b1 = sign(objectCoordinate, a, b) < 0;
		b2 = sign(objectCoordinate, b, c) < 0;
		b3 = sign(objectCoordinate, c, a) < 0;

		if (b1 && b2 && b3){
			System.out.println("Triangle selected");
			return true;
		}
		return false;


	}

	@Override
	public boolean pointInHandle(Point2D.Double pt, double tolerance) {
		Point2D.Double objectCoordinate = new Point2D.Double();
		AffineTransform worldToObject = new AffineTransform();
		worldToObject.rotate(-rotation);
		worldToObject.translate(-center.getX(), -center.getY());
		worldToObject.transform(pt, objectCoordinate);

		double max_Y = Math.max(a.getY(), b.getY());
		max_Y = Math.max(max_Y, c.getY());


		if (objectCoordinate.getX() >= -5 && objectCoordinate.getX() <= 5 ){
			if (objectCoordinate.getY() >= -(max_Y + 23) && objectCoordinate.getY() <= -(max_Y +12)){
				System.out.println("handle selected");
				//System.out.println("ordi: " + objectCoordinate);
				return true;
			}
		}
		return false;	}


	public double sign(Point2D p1, Point2D p2, Point2D p3){
		return (p1.getX()-p3.getX()) * (p2.getY()-p3.getY()) - (p2.getX()-p3.getX()) * (p1.getY()-p3.getY());
	}

}
