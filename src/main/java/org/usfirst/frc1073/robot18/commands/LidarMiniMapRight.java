package org.usfirst.frc1073.robot18.commands;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;


import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LidarMiniMapRight extends Command {


	edu.wpi.first.networktables.NetworkTable Table;
	NetworkTableInstance lidarSendTable;
	double lidarDegrees;
	double ultimateMeasurement;
	double robotSpeed;
	double degrees;
	double left;
	double right;
	double Inches;
	double x;
	double y;
	String rightLeft = new String (SmartDashboard.getString("rightLeft", "right"));

	//Variable for button used in isFinished
	boolean isPressed = false;

	public LidarMiniMapRight() {

		requires(Robot.drivetrain);

		//Sets the correct Network Table to pull from the Pixy
		lidarSendTable = NetworkTableInstance.getDefault();
		Table = lidarSendTable.getTable("lidarSendTable");
		rightLeft = "right";
		lidarSendTable.getEntry("rightLeft").setString(rightLeft);
		rightLeft = "right";
		lidarSendTable.getEntry("rightLeft").setString(rightLeft);
		x = lidarSendTable.getEntry("x").getDouble(0);
		y = lidarSendTable.getEntry("y").getDouble(0);
		SmartDashboard.putNumber("X Coordinate", x);
		SmartDashboard.putNumber("Y Coordinate", y);
	}

	protected void initialize() {
		rightLeft = "right";
		lidarSendTable.getEntry("rightLeft").setString(rightLeft);
			
		
		
	}

	protected void execute() {
		rightLeft = "right";
		lidarSendTable.getEntry("rightLeft").setString(rightLeft);
		x = lidarSendTable.getEntry("x").getDouble(0);
		y = lidarSendTable.getEntry("y").getDouble(0);
		SmartDashboard.putNumber("X Coordinate", x);
		SmartDashboard.putNumber("Y Coordinate", y);
	}
	protected boolean isFinished() {
		boolean is_finished = false;
		return is_finished;

		//Checks the cancel button for its state
		//isPressed = Robot.oi.cancelAny.get();
		//if (true){
		//SmartDashboard.putString("hello_world", "isFinished");
		//return false;
	}	
	//else 
	//	return true;
	// }

	protected void end() {
		
	}

	protected void interrupted() {
		SmartDashboard.putString("lidar info", "Interrupted");
		//Robot.bling.sendRemoveGear();
	}
}