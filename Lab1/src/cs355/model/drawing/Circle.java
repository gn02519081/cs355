package cs355.model.drawing;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Add your circle code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Circle extends Shape {

	// The radius.
	private double radius;

	/**
	 * Basic constructor that sets all fields.
	 * @param color the color for the new shape.
	 * @param center the center of the new shape.
	 * @param radius the radius of the new shape.
	 */
	public Circle(Color color, Point2D.Double center, double radius) {

		// Initialize the superclass.
		super(color, center);

		// Set the field.
		this.radius = radius;
	}

	/**
	 * Getter for this Circle's radius.
	 * @return the radius of this Circle as a double.
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Setter for this Circle's radius.
	 * @param radius the new radius of this Circle.
	 */
	public void setRadius(double radius) {
		this.radius = radius;
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

		//System.out.println(objectCoordinate.distance(center.getX(), center.getY()));

//		if (objectCoordinate.distance(center) <= radius){
//			System.out.println("Circle is selected");
//			return true;
//		}
		double distance = Math.sqrt(Math.pow(objectCoordinate.getX(),2) + Math.pow(objectCoordinate.getY(),2));
		if (distance <= radius){
			System.out.println("Circle selected");
			return true;
		}
		return false;
	}

	@Override
	public boolean pointInHandle(Point2D.Double pt, double tolerance) {
		return false;
	}

}
