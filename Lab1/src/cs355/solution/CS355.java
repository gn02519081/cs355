package cs355.solution;

import cs355.GUIFunctions;

import java.awt.*;

/**
 * This is the main class. The program starts here.
 * Make you add code below to initialize your model,
 * view, and controller and give them to the app.
 */
public class CS355 {

	/**
	 * This is where it starts.
	 * @param args = the command line arguments
	 */
	public static void main(String[] args) {

		// Fill in the parameters below with your controller and view.
		Controller controller = new Controller();
		Viewer viewer = new Viewer();

		GUIFunctions.createCS355Frame(controller, viewer);
		GUIFunctions.changeSelectedColor(Color.WHITE);

		GUIFunctions.refresh();
	}
}
