package org.usfirst.frc1073.robot18.commands.AutonomousTools;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc1073.robot18.Bling;
/** PID, but not because this actually works
 * @param speed
 * @param distance in inches (must be positive)
 * @param heading in degrees
 */
public class AdvancedDriveFR extends Command {

	/** Class wide variable declaration */
	private double speed, currentSpeed, finalSpeed, finalSpeedL, finalSpeedR, 
	dist, toBeTraveled, inch, leftEncDif, rightEncDif, startleftEncDif, percentComplete, 
	avgEncDif, startrightEncDif, originalDegrees, currentDegrees, heading;

	/* Ramp? */
	private double ramp, rampStart, rampEnd;

	public AdvancedDriveFR(double speed, double dist, double heading) {
		this.speed = speed;
		this.dist = dist;
		this.heading = heading;
		if(this.heading >= 180 && this.heading <= 360){
			this.heading = this.heading - 360;
		}
		

		/* Sets up encoders */
		RobotMap.leftMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		RobotMap.rightMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);

	}

	protected void initialize() {
		/* Set Robot.turn to false because not turning */
		Robot.turn = false;
		

		/* Grabs initial robot encoder position */
		startleftEncDif = RobotMap.leftMotor1.getSelectedSensorPosition(0);
		startrightEncDif = RobotMap.rightMotor1.getSelectedSensorPosition(0);

		/* Sets speed to an editable value and zeros out values */
		currentSpeed = speed;
		avgEncDif = 0;

		/* Grabs current heading to use for comparison during drive */
		originalDegrees = RobotMap.headingGyro.getAngle();
		originalDegrees = originalDegrees%360;

		/* Variables for the math of the encoder tick to distance */
		double rotation = 1440;
		double circumference = 12.25221134900019363000430919479;
		inch = 117.52980412939963256779108679819;
		toBeTraveled = (dist * inch * 1.045); // Distance to be traveled as used in the code

		/* Ramp? vars */
		ramp = currentSpeed / 2;
		rampStart = 0;
		rampEnd = 2;
		Robot.bling.sendAdvancedDrive();
	}

	protected void execute() {
		/** Heading Checks */
		/* Checks how far the robot has gone from the initial position */
		leftEncDif = Math.abs(startleftEncDif - RobotMap.leftMotor1.getSelectedSensorPosition(0));
		rightEncDif = Math.abs(startrightEncDif - RobotMap.rightMotor1.getSelectedSensorPosition(0));

		/* Checks current heading */
		currentDegrees = RobotMap.headingGyro.getAngle();
		currentDegrees = currentDegrees%360;

		/** Code used to adjust the heading for straighter travel */
		/* If going straight */
		if (currentSpeed > 0) {
			if (1 < heading - currentDegrees) {
				finalSpeedL = .8;
				finalSpeedR = .6;
			}
			else if (-1 > heading - currentDegrees) {
				finalSpeedL = .6;
				finalSpeedR = .8;
			}
			else {
				finalSpeedL = .8;
				finalSpeedR = .8;
			}
		}
//		/* If going backwards */
//		if (currentSpeed < 0) {
//			if (1 < originalDegrees - currentDegrees) {
//				finalSpeedL = .8;
//				finalSpeedR = 1;
//			}
//			else if (-1 > originalDegrees - currentDegrees) {
//				finalSpeedL = 1;
//				finalSpeedR = .8;
//			}
//			else {
//				finalSpeedL = 1;
//				finalSpeedR = 1;
//			}
//		}

		/** Grabs a distance traveled based on the average of the two encoders */
		/* Both are working */
		if (leftEncDif != 0 && rightEncDif != 0) {
			avgEncDif = (leftEncDif + rightEncDif) / 2;
		}
		/* Left is working */
		else if (leftEncDif != 0 && rightEncDif == 0) {
			avgEncDif = (leftEncDif);
		}
		/* Right is working */
		else if (leftEncDif == 0 && rightEncDif != 0) {
			avgEncDif = (rightEncDif);
		}
		/* Neither are working */
		else {
			avgEncDif = 0;
		}

		/** Variable update code */
		/* Uses that average and the original distance to be traveled to make a percentage total completed */
		percentComplete = avgEncDif/toBeTraveled;

		/** Uses lidar to check if path is clear */
		if (Robot.notClear == false) {
			/* Ramp? */
			/* Sets up a final speed */
			if (rampStart < rampEnd) {
				if (rampStart < rampEnd / 2) {
					finalSpeed = ramp / 2;
				}
				else {
					finalSpeed = ramp;
				}
				rampStart++;
			}
			else {
				finalSpeed = currentSpeed;
			}

			/* Sets the motor with their respective offsets based on heading adjustment */ 
			Robot.drivetrain.difDrive.tankDrive(-finalSpeed * finalSpeedL, -finalSpeed * finalSpeedR);
		}
		else {
			/* Stops the robot */
			Robot.drivetrain.difDrive.tankDrive(0, 0);

			/* Resets Ramp? */
			rampStart = 0;
		}
	}

	protected boolean isFinished() {
		boolean isFinished = false;

		//Encoder working
		if (avgEncDif != 0) {
			if (Robot.oi.driverCancel.get() == true || Robot.oi.operatorCancel.get() == true || percentComplete >= .99) {
				Robot.EncoderBoolSet = true;
				Robot.EncoderBool = true;
				isFinished = true;
				Robot.bling.sendFinished();
			}
		}
		//Cancel button
		else if (Robot.oi.driverCancel.get() == true || Robot.oi.operatorCancel.get() == true) {
			isFinished = true;
			Robot.bling.sendFinished();
		}
		
		return isFinished;
	}
}