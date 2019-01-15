
package org.usfirst.frc1073.robot18.commands;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;


import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.TurnWithGyro;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LidarWallwObstacles extends Command {


	edu.wpi.first.networktables.NetworkTable Table;
	NetworkTableInstance lidarSendTable;
	double lidarDegrees;
	double ultimateMeasurement;
	double robotSpeed;
	double degrees;
	double left;
	double right;
	double Inches;
	double center;
	double rightSide;
	double leftSide;

	//Variable for button used in isFinished
	boolean isPressed = false;

	public LidarWallwObstacles() {

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
		left = lidarSendTable.getEntry("left").getDouble(99);
		right = lidarSendTable.getEntry("right").getDouble(99);
		degrees = lidarSendTable.getEntry("degrees").getDouble(99);
		Inches = lidarSendTable.getEntry("Inches").getDouble(1073);
		SmartDashboard.putNumber("Ultimate Lidar Measurement", ultimateMeasurement);



		//This code modifies the speed based on how close you are to the peg
		SmartDashboard.putNumber("Lidar Distance" , ultimateMeasurement);
		SmartDashboard.putNumber("Lidar Degrees" , degrees);
		SmartDashboard.putNumber("Lidar To Inches", ultimateMeasurement/mmToIn);
		SmartDashboard.putNumber("Robot Speed", robotSpeed);
		SmartDashboard.putNumber("left", left);
		SmartDashboard.putNumber("right", right);
		if (Inches>=25){
		Robot.drivetrain.difDrive.tankDrive(-1*left, right);
		}
		if (center <=25 && rightSide <=28){
			new TurnWithGyro(1, 90, "counterclockwise");
		}
		else if (center <=25 && leftSide <=28){
			new TurnWithGyro(1, 90, "clockwise");
		}
		else if (center <= 25 && rightSide <= 28 && leftSide <= 28){
			Robot.drivetrain.difDrive.tankDrive(0, 0);
		}
		else{
			Robot.drivetrain.difDrive.tankDrive(-1*left, right);
		}




	}

	protected boolean isFinished() {
		if (Inches<25){
			return false;
		}
		else{
			return false;
		}

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
