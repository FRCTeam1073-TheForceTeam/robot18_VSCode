package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class doBasic extends Command {

	private double speed1, speed2, speed3, dist1, dist2, dist3, 
	speedTurn, distTurn, currentSpeed1, currentSpeed2, currentSpeed3, currentSpeedL, currentSpeedR, 
	toBeTraveled1, toBeTraveled2, toBeTraveled3, inch, leftEncDif1, leftEncDif2, leftEncDif3, 
	rightEncDif1, rightEncDif2, rightEncDif3, startleftEncDif1, startleftEncDif2, startleftEncDif3,
	percentComplete1, percentComplete2, percentComplete3, avgEncDif1, avgEncDif2, avgEncDif3,
	startrightEncDif1, startrightEncDif2, startrightEncDif3, originalDegrees, currentDegrees, n;
	private String direction;
	private boolean fin1, fin2, fin3, fin4, oneMove, turn1, turn1s, turn2, turn2s;
	
	private double slowdownDistance = 11;
	private double slowdownValue = .5;
	private double slowdownMin = .3;

	/** Is literally Advanced Drive, but it's untested so like just use that instead... or not
	 * 
	 * @author Nathaniel
	 * 
	 * @param Speed 
	 * @param Distance in inches
	 * @param SetToTrue it's literally the name of the variable so PLEASE
	 * set it to true
	 */
	public doBasic(double speed1, double dist1, boolean setToTrue) {
		this.speed1 = speed1;
		this.dist1 = dist1;
		oneMove = setToTrue;
	}
	/** Runs through a "basic" move auto that goes straight, the turns,
	 *  then goes straight again, and then turns, and then goes straight one last time
	 *  
	 *  Truly a simple system of code. If you can't understand the following, then you aren't worthy.
	 * 
	 * @author Nathaniel
	 * 
	 * @param Speed1 speed for first move
	 * @param Distance1 distance to travel (in inches) for first move
	 * @param SpeedTurn speed of BOTH turns
	 * @param DistanceTurn degrees of BOTH turns (must be positive)
	 * @param Direction direction of the FIRST turn (input must be "counterclockwise" or "clockwise") 
	 * Note: the SECOND turn goes the opposite direction of the first
	 * @param Speed2 speed for second move
	 * @param Distance2 distance to travel (in inches) for second move
	 * @param Speed3 speed for third move
	 * @param Distance3 distance to travel (in inches) for third move
	 */
	public doBasic(double speed1, double dist1, double speedTurn, double distTurn,
			String direction, double speed2, double dist2, double speed3, double dist3) {
		this.speed1 = speed1;
		this.dist1 = dist1;
		this.speedTurn = speedTurn;
		this.distTurn = distTurn;
		this.direction = direction;
		this.speed2 = speed2;
		this.dist2 = dist2;
		this.speed3 = speed3;
		this.dist3 = dist3;
	}

	protected void initialize() {
		startleftEncDif1 = RobotMap.leftMotor1.getSelectedSensorPosition(0);
		startrightEncDif1 = RobotMap.rightMotor1.getSelectedSensorPosition(0);
		
		originalDegrees = RobotMap.headingGyro.getAngle();

		currentSpeed1 = speed1;
		currentSpeed2 = speed2;
		currentSpeed3 = speed3;

		double rotation = 1440;
		double circumference = 12.25221134900019363000430919479;
		inch = 117.52980412939963256779108679819;

		toBeTraveled1 = (dist1 * inch * 1.045);
		toBeTraveled2 = (dist2 * inch * 1.045);
		toBeTraveled3 = (dist3 * inch * 1.045);

		n = 0;
		fin1 = false;
		fin2 = false;
		fin3 = false;
		fin4 = false;
		turn1 = false;
		turn1s = false;
		turn2 = false;
		turn2s = false;
	}

	protected void execute() {
		if (fin1 == false && fin2 == false && fin3 == false && fin4 == false && turn1 == false && turn2 == false) {
			leftEncDif1 = Math.abs(startleftEncDif1 - RobotMap.leftMotor1.getSelectedSensorPosition(0));
			rightEncDif1 = Math.abs(startrightEncDif1 - RobotMap.rightMotor1.getSelectedSensorPosition(0));
			currentDegrees = RobotMap.headingGyro.getAngle();
			SmartDashboard.putNumber("Left Encoder", leftEncDif1);
			SmartDashboard.putNumber("Right Encoder", rightEncDif1);
		}
		if (fin1 == true && fin2 == false && fin3 == false && fin4 == false && turn1 == true && turn2 == false) {
			startleftEncDif2 = RobotMap.leftMotor1.getSelectedSensorPosition(0);
			startrightEncDif2 = RobotMap.rightMotor1.getSelectedSensorPosition(0);
			originalDegrees = RobotMap.headingGyro.getAngle();
			fin2 = true;
		}
		if (fin1 == true && fin2 == true && fin3 == false && fin4 == false && turn1 == true && turn2 == false) {
			leftEncDif2 = Math.abs(startleftEncDif2 - RobotMap.leftMotor1.getSelectedSensorPosition(0));
			rightEncDif2 = Math.abs(startrightEncDif2 - RobotMap.rightMotor1.getSelectedSensorPosition(0));
			currentDegrees = RobotMap.headingGyro.getAngle();
			SmartDashboard.putNumber("Left Encoder", leftEncDif1);
			SmartDashboard.putNumber("Right Encoder", rightEncDif1);
		}
		if (fin1 == true && fin2 == true && fin3 == true && fin4 == false && turn1 == true && turn2 == true) {
			startleftEncDif3 = RobotMap.leftMotor1.getSelectedSensorPosition(0);
			startrightEncDif3 = RobotMap.rightMotor1.getSelectedSensorPosition(0);
			originalDegrees = RobotMap.headingGyro.getAngle();
			fin4 = true;
		}
		if (fin1 == true && fin2 == true && fin3 == true && fin4 == true && turn1 == true && turn2 == true) {
			leftEncDif3 = Math.abs(startleftEncDif3 - RobotMap.leftMotor1.getSelectedSensorPosition(0));
			rightEncDif3 = Math.abs(startrightEncDif3 - RobotMap.rightMotor1.getSelectedSensorPosition(0));
			currentDegrees = RobotMap.headingGyro.getAngle();
			SmartDashboard.putNumber("Left Encoder", leftEncDif1);
			SmartDashboard.putNumber("Right Encoder", rightEncDif1);
		}

		avgEncDif1 = (leftEncDif1 + rightEncDif1) / 2;
		avgEncDif2 = (leftEncDif2 + rightEncDif2) / 2;
		avgEncDif3 = (leftEncDif3 + rightEncDif3) / 2;

		percentComplete1 = avgEncDif1/toBeTraveled1;
		percentComplete2 = avgEncDif2/toBeTraveled2;
		percentComplete3 = avgEncDif3/toBeTraveled3;

		if (percentComplete1 > .99 && fin1 == false && fin2 == false && fin3 == false && fin4 == false) {
			if (turn1s == false) {
				originalDegrees = RobotMap.headingGyro.getAngle();
				turn1s = true;
			}
			else if (turn1s == true && turn1 == false) {
		    	double right = speedTurn, left = speedTurn;
		    	
		    	if(direction.equals("clockwise")) {
		 	   		if(Math.abs(RobotMap.headingGyro.getAngle() - (originalDegrees + distTurn)) > slowdownDistance) {
		 	   			Robot.drivetrain.difDrive.tankDrive(left*-1, right);
		 	   		}
		 	   		else if(Math.abs(RobotMap.headingGyro.getAngle() - (originalDegrees + distTurn)) <= slowdownDistance) {
		 	   			Robot.drivetrain.difDrive.tankDrive(Double.min
		 	   					(left*(slowdownDistance - (originalDegrees + distTurn))*-.01, slowdownMin*-1), Double.max
		 	   					(right*(slowdownDistance - (originalDegrees + distTurn))*.01, slowdownMin));
		 	   		}
		 	   	}
		    	else if(direction.equals("counterclockwise")) {
		 	   		if(Math.abs(RobotMap.headingGyro.getAngle() - (originalDegrees - distTurn)) > slowdownDistance) {
		 	   			Robot.drivetrain.difDrive.tankDrive(left, right*-1);
		 	   		}
		 	   		else if(Math.abs(RobotMap.headingGyro.getAngle() - (originalDegrees - distTurn)) <= slowdownDistance) {
		 	   			Robot.drivetrain.difDrive.tankDrive(Double.max
		 	   					(left*(slowdownDistance - (originalDegrees - distTurn))*.01, slowdownMin), Double.min
		 	   					(right*(slowdownDistance - (originalDegrees - distTurn))*-.01, slowdownMin*-1));
		 	   		}
		 	   	}
		    	if(direction.equals("clockwise")) {
		    		turn1 = (RobotMap.headingGyro.getAngle() >= (originalDegrees + distTurn));
		    	}
		    	else if(direction.equals("counterclockwise")) {
		    		turn1 = (RobotMap.headingGyro.getAngle() <= (originalDegrees - distTurn));
		    	}
		    	else {
		    		turn1 = false;
		    	}
			}
			else if (turn1s == true && turn1 == true) {
				fin1 = true;
			}
			else {
				SmartDashboard.putString("Turn", "Failed");
			}
		}
		if (percentComplete2 > .99 && fin1 == true && fin2 == true && fin3 == false && fin4 == false) {
			if (turn2s == false) {
				originalDegrees = RobotMap.headingGyro.getAngle();
				turn2s = true;
			}
			else if (turn2s == true && turn2 == false) {
		    	double right = speedTurn, left = speedTurn;
		    	
		    	if(direction.equals("counterclockwise")) {
		 	   		if(Math.abs(RobotMap.headingGyro.getAngle() - (originalDegrees + distTurn)) > slowdownDistance) {
		 	   			Robot.drivetrain.difDrive.tankDrive(left*-1, right);
		 	   		}
		 	   		else if(Math.abs(RobotMap.headingGyro.getAngle() - (originalDegrees + distTurn)) <= slowdownDistance) {
		 	   			Robot.drivetrain.difDrive.tankDrive(Double.min
		 	   					(left*(slowdownDistance - (originalDegrees + distTurn))*-.01, slowdownMin*-1), Double.max
		 	   					(right*(slowdownDistance - (originalDegrees + distTurn))*.01, slowdownMin));
		 	   		}
		 	   	}
		    	else if(direction.equals("clockwise")) {
		 	   		if(Math.abs(RobotMap.headingGyro.getAngle() - (originalDegrees - distTurn)) > slowdownDistance) {
		 	   			Robot.drivetrain.difDrive.tankDrive(left, right*-1);
		 	   		}
		 	   		else if(Math.abs(RobotMap.headingGyro.getAngle() - (originalDegrees - distTurn)) <= slowdownDistance) {
		 	   			Robot.drivetrain.difDrive.tankDrive(Double.max
		 	   					(left*(slowdownDistance - (originalDegrees - distTurn))*.01, slowdownMin), Double.min
		 	   					(right*(slowdownDistance - (originalDegrees - distTurn))*-.01, slowdownMin*-1));
		 	   		}
		 	   	}
		    	if(direction.equals("counterclockwise")) {
		    		turn2 = (RobotMap.headingGyro.getAngle() >= (originalDegrees + distTurn));
		    	}
		    	else if(direction.equals("clockwise")) {
		    		turn2 = (RobotMap.headingGyro.getAngle() <= (originalDegrees - distTurn));
		    	}
		    	else {
		    		turn2 = false;
		    	}
			}
			else if (turn2s == true && turn2 == true) {
				fin3 = true;
			}
			else {
				SmartDashboard.putString("Turn", "Failed");
			}
		}
		if (percentComplete1 < .99) {

			if (currentSpeed1 > 0) {
				if (1 < originalDegrees - currentDegrees) {
					currentSpeedL = 1;
					currentSpeedR = .9;
				}
				else if (-1 > originalDegrees - currentDegrees) {
					currentSpeedL = .9;
					currentSpeedR = 1;
				}
				else {
					currentSpeedL = 1;
					currentSpeedR = 1;
				}
			}
			if (currentSpeed1 < 0) {
				if (1 < originalDegrees - currentDegrees) {
					currentSpeedL = .9;
					currentSpeedR = 1;
				}
				else if (-1 > originalDegrees - currentDegrees) {
					currentSpeedL = 1;
					currentSpeedR = .9;
				}
				else {
					currentSpeedL = 1;
					currentSpeedR = 1;
				}
			}

			if (percentComplete1 < 1) {
				if (percentComplete1 > .90) {
					currentSpeed1 = currentSpeed1 / 20;
				}
			}

			Robot.drivetrain.difDrive.tankDrive(-currentSpeed1 * currentSpeedL, -currentSpeed1 * currentSpeedR);
		}
		else if (percentComplete1 >= .99 && percentComplete2 < .99
				&& fin1 == true && fin2 == true && fin3 == false && fin4 == false) {

			if (currentSpeed2 > 0) {
				if (1 < originalDegrees - currentDegrees) {
					currentSpeedL = 1;
					currentSpeedR = .9;
				}
				else if (-1 > originalDegrees - currentDegrees) {
					currentSpeedL = .9;
					currentSpeedR = 1;
				}
				else {
					currentSpeedL = 1;
					currentSpeedR = 1;
				}
			}
			if (currentSpeed2 < 0) {
				if (1 < originalDegrees - currentDegrees) {
					currentSpeedL = .9;
					currentSpeedR = 1;
				}
				else if (-1 > originalDegrees - currentDegrees) {
					currentSpeedL = 1;
					currentSpeedR = .9;
				}
				else {
					currentSpeedL = 1;
					currentSpeedR = 1;
				}
			}

			if (percentComplete2 < 1) {
				if (percentComplete2 > .90) {
					currentSpeed2 = currentSpeed2 / 20;
				}
			}

			Robot.drivetrain.difDrive.tankDrive(-currentSpeed2 * currentSpeedL, -currentSpeed2 * currentSpeedR);
		}
		else if (percentComplete1 >= .99 && percentComplete2 >= .99 && percentComplete3 < .99
				&& fin1 == true && fin2 == true && fin3 == true && fin4 == true) {

			if (currentSpeed3 > 0) {
				if (1 < originalDegrees - currentDegrees) {
					currentSpeedL = 1;
					currentSpeedR = .9;
				}
				else if (-1 > originalDegrees - currentDegrees) {
					currentSpeedL = .9;
					currentSpeedR = 1;
				}
				else {
					currentSpeedL = 1;
					currentSpeedR = 1;
				}
			}
			if (currentSpeed3 < 0) {
				if (1 < originalDegrees - currentDegrees) {
					currentSpeedL = .9;
					currentSpeedR = 1;
				}
				else if (-1 > originalDegrees - currentDegrees) {
					currentSpeedL = 1;
					currentSpeedR = .9;
				}
				else {
					currentSpeedL = 1;
					currentSpeedR = 1;
				}
			}

			if (percentComplete3 < 1) {
				if (percentComplete3 > .90) {
					currentSpeed2 = currentSpeed3 / 20;
				}
			}

			Robot.drivetrain.difDrive.tankDrive(-currentSpeed3 * currentSpeedL, -currentSpeed3 * currentSpeedR);
		}
	}

	protected boolean isFinished() {
		boolean isFinished = false;
		if (oneMove == true) {
			if (percentComplete1 >= .99) {
				isFinished = true;
			}
		}
		else if (percentComplete1 >= .99 && percentComplete2 >= .99 && percentComplete3 >= .99
				&& fin1 == true && fin2 == true && fin3 == true && fin4 == true || Robot.oi.driverCancel.get() == true || Robot.oi.operatorCancel.get() == true) {
			isFinished = true;
		}
		return isFinished;
	}
}
