
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
public class LidarMoveAway extends Command {

	edu.wpi.first.networktables.NetworkTable Table;
	//edu.wpi.first.networktables.NetworkTable Gyro;
	NetworkTableInstance lidarSendTable;
	//NetworkTableInstance gyroTable;
	double lidarDegrees;
	double ultimateMeasurement;
	double robotSpeed;
	double degrees;
	double left;
	double right;
	double Inches;

	//Variable for button used in isFinished
	boolean isPressed = false;


	public LidarMoveAway() {

		requires(Robot.drivetrain);

		//Sets the correct Network Table to pull from the Pixy
		lidarSendTable = NetworkTableInstance.getDefault();
		
		Table = lidarSendTable.getTable("lidarSendTable");
		//gyroTable = lidarSendTable.getTable("gyro");
	}

	protected void initialize() {
		SmartDashboard.putString("lidar info", "init");
		SmartDashboard.putString("hello_world", "x");
		//gyroTable = NetworkTable.getTable("gyro");


	}

	protected void execute() {

		SmartDashboard.putString("lidar info", "execute");
		//These are the variables that get manipulated in the code

		double mmToIn = 1.0;
		SmartDashboard.putNumber("ultimateMeasurement", ultimateMeasurement);

		//These are the variables for speed - start slow

		//These are what the Pixy send us
		
		//finish these
		//robotSpeed = lidarSendTable.getNumber("robotSpeed", 99);
		//left = lidarSendTable.getNumber("left", 99);
		//right = lidarSendTable.getNumber("right", 99);
		//degrees = lidarSendTable.getNumber("degrees",99);
		SmartDashboard.putNumber("Ultimate Lidar Measurement", ultimateMeasurement);



		//This code modifies the speed based on how close you are to the peg
		SmartDashboard.putNumber("Lidar Distance" , ultimateMeasurement);
		SmartDashboard.putNumber("Lidar Degrees" , degrees);
		// lidarDegrees = degree measurement of closest object
		SmartDashboard.putNumber("Lidar To Inches", ultimateMeasurement/mmToIn);
		//ultimateMeasurement = distance at closest degrees
		SmartDashboard.putNumber("Robot Speed", robotSpeed);
		SmartDashboard.putNumber("left", left);
		SmartDashboard.putNumber("right", right);

		Robot.drivetrain.basicDrive(-1*right, left);

		//gyroTable.putNumber("gyroValue", RobotMap.headingGyro.getAngle());
		//lidarSendTable.putNumber("gyroValue", RobotMap.headingGyro.getAngle());




	}

	protected boolean isFinished() {
		boolean is_finished = false;
		return is_finished;

		//SmartDashboard.putString("lidar info", "isFinished");

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


