package cs355.model.drawing;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Add your square code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Square extends Shape {

	// The size of this Square.
	private double size;

	/**
	 * Basic constructor that sets all fields.
	 * @param color the color for the new shape.
	 * @param center the center of the new shape.
	 * @param size the size of the new shape.
	 */
	public Square(Color color, Point2D.Double center, double size) {

		// Initialize the superclass.
		super(color, center);

		// Set the field.
		this.size = size;
	}

	/**
	 * Getter for this Square's size.
	 * @return the size as a double.
	 */
	public double getSize() {
		return size;
	}

	/**
	 * Setter for this Square's size.
	 * @param size the new size.
	 */
	public void setSize(double size) {
		this.size = size;
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

		if (Math.abs(objectCoordinate.getX()) <= size/2 && Math.abs(objectCoordinate.getY()) <= size/2){
			System.out.println("Square is selected");
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

		if (objectCoordinate.getX() >= -5 && objectCoordinate.getX() <= 5 ){
			if (objectCoordinate.getY() >= -(size/2 + 23) && objectCoordinate.getY() <= -(size/2 +12)){
				System.out.println("handle selected");
				//System.out.println("ordi: " + objectCoordinate);
				return true;
			}
		}
		return false;
	}

}
