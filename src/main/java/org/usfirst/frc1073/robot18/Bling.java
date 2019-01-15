package org.usfirst.frc1073.robot18;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Bling {
	NetworkTable newtable;

	//Instance Variables
	private String color;
	private String speed;
	private int min;
	private int max;
	private String pattern;
	private String segment;
	private String put;
	private String put2;

	//Constructor
	public Bling() {
		newtable = NetworkTable.getTable("Bling");
		//NetworkTable.initialize();
	}

	//Methods
	public String setPattern(String patt, String col, String seg, String spd, int mini, int maxi) {
		pattern = patt;
		color = col;
		segment = seg;
		speed = spd;
		min = mini;
		max = maxi;
		put = "Pattern=" + pattern + "," + "Color=" + color + "," + "Segment=" + segment + "," + "Speed=" + speed + "," + "Min=" + min + "," + "Max=" + max; 
		return put;
	}

	public void disableLeds() {
		put = "Pattern=" + "off" + "," + "Color=" + color + "," + "Segment=" + segment + "," + "Speed=" + speed + "," + "Min=" + min + "," + "Max=" + max; 
	}

	public void send() {
		newtable.putString("command", put);
	}

	/** Set of API functions that can be called from the robot code to send and
        pattern to the bling **/
	public void sendPattern( BlingMode pattern ) {
		Boolean validPattern = true;

		switch( pattern ) {
		case ROBOT_INIT:
			setPattern("RainbowHalves", "rainbow", "all", "medium", 0, 100);
			break;
		case ROBOT_ERROR:
			setPattern("Alternates", "christmas", "all", "medium", 0, 100);
			break;
		case CLIMBING:
			setPattern("ColorFade", "teamcolors", "all", "fast", 0, 100);
			break;

			// put all pattern specifiers above this point
		case OFF:
			disableLeds();
			break;
		default:
			setPattern("Error", "red", "all", "fast", 0, 100);
			break;
		}

		if ( validPattern ) {
			send();
		}

		return;
	}

	public void sendCubein() {
		setPattern("Solid", "Aqua", "all", "fast", 0, 100);
		send();
	}

	public void sendFinished() {
		setPattern("RainbowHalves", "red", "all", "fast", 0, 100);
		send();
	}

	public void sendFinishedClimbing() {
		setPattern("Alternates", "teamcolors", "all", "medium", 0, 100);
		send();
	}

	public void sendLeftTurning() {
		setPattern("ColorWipe", "Purple", "left", "fast", 0, 100);
		send();
	}

	public void sendRightTurning() {
		setPattern("ColorWipe", "Purple", "right", "fast", 0, 100);
		send();
	}

	public void sendDrive() {
		setPattern("solid", "blue", "all", "medium", 0, 100);
		send();
	}

	public void sendBackup() {
		setPattern("blinking", "yellow", "all", "medium", 0, 100);
		send();
	}

	public void sendEnd() {
		setPattern("fireflies", "rainbow", "all", "medium", 0, 100);
		send();
	}

	public void sendOff() {
		disableLeds();
		send();
	}

	public void sendRobotInit() {
		setPattern("RainbowHalves", "rainbow", "all", "medium", 0, 100);
		send();
	}

	public void sendAutoDrive() {
		setPattern("ColorChase", "green", "all", "slow", 0, 100);
		send();
	}

	public void sendDropOff() {
		setPattern("Fireflies", "Aqua", "all", "slow", 0, 100);
		send();
	}

	public void sendAutoTurnRight() {
		setPattern("blinking", "Purple", "right", "medium", 0, 100);
		send();
	}
	public void sendAutoTurnLeft() {
		setPattern("blinking", "Purple", "left", "medium", 0, 100);
		send();
	}

	public void sendAdvancedDrive() {
		setPattern("Solid", "Blue", "all", "medium", 0, 100);
		send();
	}

	public void sendDeliverCube() {
		setPattern("scanner", "Yellow", "all", "medium", 0, 100);
		send();
	}

	public void sendFindingCube() {
		setPattern("blinking", "Green", "all", "medium", 0, 100);
		send();
	}

	public void sendDriveBy() {
		setPattern("RainbowHalves", "rainbow", "all", "medium", 0, 100);
		send();
	}
	public void sendEndgame() {
		setPattern("blinking", "yellow", "all", "fast", 0, 100);
		send();
	}
	public void sendDriveTrainLowGear() {
		setPattern("solid", "blue", "all", "medium", 0, 100);
		send();
	}
	public void sendDriveTrainHighGear() {
		setPattern("solid", "green", "all", "medium", 0, 100);
		send();
	}
	public void sendSuckinCube() {
		setPattern("scanner", "yellow", "all", "fast", 0, 100);
		send();

	}
}
