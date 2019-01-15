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
public class LidarTest extends Command {

	edu.wpi.first.networktables.NetworkTable Table;
	NetworkTableInstance lidarSendTable;
	double LidarDegrees;
	double ultimateMeasurement;
	double robotSpeed;

	//Variable for button used in isFinished
	boolean isPressed = false;

	public LidarTest() {


		requires(Robot.drivetrain);

		//Sets the correct Network Table to pull from the Pixy
		lidarSendTable = NetworkTableInstance.getDefault();
		Table = lidarSendTable.getTable("lidarSendTable");
	}

	protected void initialize() {
		SmartDashboard.putString("lidar info", "init");
		SmartDashboard.putString("hello_world", "x");

	}

	protected void execute() {
		SmartDashboard.putString("lidar info", "execute");
		//These are the variables that get manipulated in the code

		double mmToIn = 1.0;
		SmartDashboard.putNumber("ultimateMeasurement", ultimateMeasurement);

		//These are the variables for speed - start slow

		//These are what the Pixy send us
		robotSpeed = lidarSendTable.getEntry("robotSpeed").getDouble(99);
		SmartDashboard.putNumber("Ultimate Lidar Measurement", ultimateMeasurement);


		//This code modifies the speed based on how close you are to the peg
		SmartDashboard.putNumber("Lidar Distance" , ultimateMeasurement);
		SmartDashboard.putNumber("Lidar Degrees" , LidarDegrees);
		SmartDashboard.putNumber("Lidar To Inches", ultimateMeasurement/mmToIn);
		SmartDashboard.putNumber("Robot Speed" , robotSpeed);

		Robot.drivetrain.basicDrive(robotSpeed, robotSpeed);

	}

	protected boolean isFinished() {

		//SmartDashboard.putString("lidar info", "isFinished");

		//Checks the cancel button for its state
		//isPressed = Robot.oi.cancelAny.get();
		return false;
	}

	protected void end() {
		//Stops motors and sets bling
		Robot.drivetrain.basicDrive(0, 0);
		//Robot.bling.sendRemoveGear();
	}

	protected void interrupted() {
		//Stops motors and sets bling
		Robot.drivetrain.basicDrive(0, 0);
		SmartDashboard.putString("lidar info", "Interrupted");
		//Robot.bling.sendRemoveGear();
	}
}
