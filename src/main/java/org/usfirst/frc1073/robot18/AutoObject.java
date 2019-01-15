package org.usfirst.frc1073.robot18;

/*** Used for different autonomousChooser settings
 * @author Nathaniel
 * @category Dashboard Stuff
 * @param Value (int) that will later be used to equate to a string
 */
public class AutoObject {
	private int n;

	/** Takes an integer when setting and that int will correspond to a string */
	public AutoObject(int v) {
		n = v;
	}

	/** Gets the position of the robot from the Chooser */
	public String getString() {
		String send = "";

		if (n == 1) {
			send = "left";
		}
		else if (n == 2) {
			send = "center";
		}
		else if (n == 3) {
			send = "right";
		}
		else if (n == 4) {
			send = "other";
		}
		else if (n == 5) {
			send = "quals";
		}
		else if (n == 6) {
			send = "elims";
		}
		else if (n == 7) {
			send = "experimental";
		}
		else {
			send = "not set";
		}

		return send;
	}

	/** Become your inner spinny boi */
	public void Danktonomous() {
		Robot.FMS = "DANK";
	}

	/** Coopertition?? */
	public void Invert() {
		int setVar = 0;
		if (Robot.FMS == "RRR") setVar = 1;
		if (Robot.FMS == "RLR") setVar = 2;
		if (Robot.FMS == "LLL") setVar = 3;
		if (Robot.FMS == "LRL") setVar = 4;

		if (setVar == 1) Robot.FMS = "LLL";
		if (setVar == 2) Robot.FMS = "LRL";
		if (setVar == 3) Robot.FMS = "RRR";
		if (setVar == 4) Robot.FMS = "RLR";
	}
}