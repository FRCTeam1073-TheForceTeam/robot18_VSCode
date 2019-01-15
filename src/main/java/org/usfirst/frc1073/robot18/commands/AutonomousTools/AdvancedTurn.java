package org.usfirst.frc1073.robot18.commands.AutonomousTools;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc1073.robot18.Bling;
/*** Straight drive command 
 * @author Nathaniel 
 */
public class AdvancedTurn extends Command {

	/** Class wide variable declaration */
	private double speed, currentSpeed, finalSpeed, finalSpeedL, finalSpeedR, 
	degrees, toBeTraveled, inch, leftEncDif, rightEncDif, startleftEncDif, percentComplete, 
	avgEncDif, startrightEncDif, originalDegrees, currentDegrees, n;
	private String direction;

	/* Ramp? */
	private double ramp, rampStart, rampEnd;

	/* Timer variables */
	private double timeout, timer, timeEnd;
	private boolean fin;
	
	//New Timer
	
	private double timerStart, timerEnd;

	/** PID, but not because this actually works
	 * @author Nathaniel
	 * @param speed (must be positive)
	 * @param degrees (must be positive)
	 * @param direction ("left" or "right")
	 * @param timeout in milliseconds * 5 (200 in a second) 
	 * Note: 0 = no timeout
	 * Note: Minimum of 5 millisecond run time
	 * @category Drive Command
	 */
	public AdvancedTurn(double speed, double degrees, String direction, double timeout) {
		this.speed = speed;
		this.direction = direction;
		this.degrees = degrees;
		this.timeout = timeout;

		/* Sets up encoders */
		RobotMap.leftMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		RobotMap.rightMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);

	}

	protected void initialize() {
		/* Set Robot.turn to false because not turning */
		Robot.turn = false;
		
		/* Timer setup and check for if used */
		timerEnd = timeout;
		timerStart = System.currentTimeMillis();

		/* Grabs initial robot encoder position */
		startleftEncDif = RobotMap.leftMotor1.getSelectedSensorPosition(0);
		startrightEncDif = RobotMap.rightMotor1.getSelectedSensorPosition(0);

		/* Sets speed to an editable value and zeros out values */
		currentSpeed = speed;
		avgEncDif = 0;

		/* Grabs current heading to use for comparison during drive */
		originalDegrees = RobotMap.headingGyro.getAngle();

		/* Variables for the math of the encoder tick to degreesance */
		double rotation = 1440;
		double circumference = 12.25221134900019363000430919479;
		inch = 117.52980412939963256779108679819;
		toBeTraveled = degrees;

		/* Extra variables */
		n = 0;
		fin = false;

		/* Ramp? vars */
		ramp = currentSpeed / 2;
		rampStart = 0;
		rampEnd = 2;
		
		if (direction == "left") {
			Robot.bling.sendAutoTurnLeft();
		} else if (direction == "right") {
			Robot.bling.sendAutoTurnRight();
		}
	}

	protected void execute() {
		
		double deltaTime = (timerEnd + timerStart) - System.currentTimeMillis();
		
		System.out.println("timerEnd: " + (long)timerEnd + " timerStart: " + (long)timerStart + " CurrentTime: " + System.currentTimeMillis() + " DeltaTime: " + (long)deltaTime);
		
		/** Heading Checks */
		/* Checks how far the robot has gone from the initial position */
		leftEncDif = Math.abs(startleftEncDif - RobotMap.leftMotor1.getSelectedSensorPosition(0));
		rightEncDif = Math.abs(startrightEncDif - RobotMap.rightMotor1.getSelectedSensorPosition(0));

		/* Checks current heading */
		currentDegrees = RobotMap.headingGyro.getAngle();

		/** Code used to adjust the heading for straighter travel */
		/* If going right */
		if (direction == "right") {
			if (currentDegrees - originalDegrees <= degrees / 1.05) {
				if (currentDegrees - originalDegrees < degrees / 1.15) {
					if (currentDegrees - originalDegrees < degrees / 1.35) {
						finalSpeedL = -1;
						finalSpeedR = 1;
					}
					finalSpeedL = -.8;
					finalSpeedR = .8;
				}
				finalSpeedL = -.5;
				finalSpeedR = .5;
			} else if (currentDegrees - originalDegrees >= degrees / .95) {
				finalSpeedL = .5;
				finalSpeedR = -.5;
			}
			percentComplete = (currentDegrees - originalDegrees) / degrees;
		}
		/* If going left */
		if (direction == "left") {
			if (originalDegrees - currentDegrees <= degrees / 1.05) {
				if (originalDegrees - currentDegrees < degrees / 1.15) {
					if (originalDegrees - currentDegrees < degrees / 1.35) {
						finalSpeedL = 1;
						finalSpeedR = -1;
					}
					finalSpeedL = .8;
					finalSpeedR = -.8;
				}
				finalSpeedL = .5;
				finalSpeedR = -.5;
			} else if (originalDegrees - currentDegrees >= degrees / .95) {
				finalSpeedL = -.5;
				finalSpeedR = .5;
			}
			percentComplete = (originalDegrees - currentDegrees) / degrees;
		}

		/** Grabs a degrees traveled based on the average of the two encoders */
		/* Both are working */ if (leftEncDif != 0 && rightEncDif != 0) {
			avgEncDif = (leftEncDif + rightEncDif) / 2;
		} /* Left is working */ else if (leftEncDif != 0 && rightEncDif <= 1) {
			avgEncDif = (leftEncDif);
		} /* Right is working */ else if (leftEncDif == 0 && rightEncDif != 0) {
			avgEncDif = (rightEncDif);
		} /* Neither are working */ else {
			avgEncDif = 0;
		}		

		/** Uses lidar to check if path is clear */
		if (Robot.notClear == false) {
			/* Ramp? */
			/* Sets up a final speed */
			if (rampStart < rampEnd) {
				if (rampStart < rampEnd / 2) {
					finalSpeed = ramp / 2;
				} else {
					finalSpeed = ramp;
				}
				rampStart++;
			} else {
				finalSpeed = currentSpeed;
			}

			/* Sets the motor with their respective offsets based on heading adjustment */ 
			Robot.drivetrain.difDrive.tankDrive(-finalSpeed * finalSpeedL, -finalSpeed * finalSpeedR);

			/* Timer step for if timed */
			timer++;
		} else {
			/* Stops the robot */
			Robot.drivetrain.difDrive.tankDrive(0, 0);

			/* Resets Ramp? */
			rampStart = 0;
		}
	}

	protected boolean isFinished() {
		boolean isFinished = false;

		/** Decides if code should use timeout */
		/* If already set */
		//Encoder not working
		if (Robot.EncoderBoolSet == true && Robot.EncoderBool == false) {
			if (Robot.oi.driverCancel.get() == true || Robot.oi.operatorCancel.get() == true || timer >= timeEnd) {
				Robot.EncoderBool = false;
				isFinished = true;
				Robot.bling.sendFinished();
			}
		}
		/* If not set */
		/* Encoder not working */ if (System.currentTimeMillis() - timerStart > 5 && avgEncDif == 0) {
			if (Robot.oi.driverCancel.get() == true || Robot.oi.operatorCancel.get() == true || timer >= timeEnd) {
				Robot.EncoderBoolSet = true;
				Robot.EncoderBool = false;
				isFinished = true;
				Robot.bling.sendFinished();
			}
		} /* Encoder working else if */ else if (System.currentTimeMillis() - timerStart > 5 && avgEncDif != 0) {
			if (Robot.oi.driverCancel.get() == true || Robot.oi.operatorCancel.get() == true || percentComplete >= .99) {
				Robot.EncoderBoolSet = true;
				Robot.EncoderBool = true;
				isFinished = true;
				Robot.bling.sendFinished();
			}
		}
		if (System.currentTimeMillis() - timerStart >= timerEnd) {
			Robot.EncoderBoolSet = true;
			Robot.EncoderBool = true;
			isFinished = true;
			Robot.bling.sendFinished();
		}
		else if (Robot.oi.driverCancel.get() == true || Robot.oi.operatorCancel.get() == true) {
			isFinished = true;
			Robot.bling.sendFinished();
		}
		return isFinished;
	}
}