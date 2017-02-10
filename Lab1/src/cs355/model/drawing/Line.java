package cs355.model.drawing;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Add your line code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Line extends Shape {

	// The ending point of the line.
	private Point2D.Double end;

	/**
	 * Basic constructor that sets all fields.
	 * @param color the color for the new shape.
	 * @param start the starting point.
	 * @param end the ending point.
	 */
	public Line(Color color, Point2D.Double start, Point2D.Double end) {

		// Initialize the superclass.
		super(color, start);

		// Set the field.
		this.end = end;
	}

	/**
	 * Getter for this Line's ending point.
	 * @return the ending point as a Java point.
	 */
	public Point2D.Double getEnd() {
		return end;
	}

	/**
	 * Setter for this Line's ending point.
	 * @param end the new ending point for the Line.
	 */
	public void setEnd(Point2D.Double end) {
		this.end = end;
	}

	/**
	 * Add your code to do an intersection test
	 * here. You <i>will</i> need the tolerance.
	 * @param pt = the point to test against.
	 * @param tolerance = the allowable tolerance.
	 * @return true if pt is in the shape,
	 *		   false otherwise.
	 */
	@Override
	public boolean pointInShape(Point2D.Double pt, double tolerance) {
		//throw new UnsupportedOperationException("Not supported yet.");

		tolerance = 5;
		//System.out.println(center);
		Point2D.Double objectCoordinate = new Point2D.Double();
		Point2D.Double endpoint = new Point2D.Double();
		AffineTransform worldToObject = new AffineTransform();
		worldToObject.rotate(-rotation);
		worldToObject.translate(-center.getX(), -center.getY());
		worldToObject.transform(pt, objectCoordinate);
		worldToObject.transform(getEnd(), endpoint);

		double slope = endpoint.getY() / endpoint.getX();

		double c = endpoint.getY() - (slope * endpoint.getX());

		double distance = Math.abs(((slope * objectCoordinate.getX()) - objectCoordinate.getY() + c) / Math.sqrt(slope * slope + 1));
		if (distance <= tolerance){
			System.out.println("Line is selected");
			return true;
		}
		return false;
	}

	@Override
	public boolean pointInHandle(Point2D.Double pt, double tolerance) {
		return false;
	}

}
