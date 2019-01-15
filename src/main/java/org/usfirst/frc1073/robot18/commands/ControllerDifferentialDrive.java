
package org.usfirst.frc1073.robot18.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ControllerDifferentialDrive extends Command {



	/* Ramp? */
	private double axis, axisTurn, rampEnd, forward, forwardLast, forwardFinal, turn, lastSpeed, currentSpeed, hold, held; 
	private int rampCurrent;
	private boolean top, bottom, ramp, holdingUp, holdingDown;
	private double[] ramps = {.2, .35, .475, .5, .625, .75, .95, 1};

	public ControllerDifferentialDrive() {
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		/* Ramp? 
		rampCurrent = 0;
		rampEnd = 7;
		hold = 0;
		held = 10;
		top = false; */
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		/**RAWAXIS LIST
		 * 1 - Left X
		 * 2 - Left Y
		 * 3 - Triggers
		 * 4 - Right X
		 * 5 - Right Y
		 * 6 - DPad left/right
		 **/

		axis = Robot.oi.driverControl.getRawAxis(1);
		axisTurn = Robot.oi.driverControl.getRawAxis(4);
		
		/** Deadzone */
		if((axisTurn < .05 && axisTurn > 0) || (axisTurn > (-0.05) && axisTurn < 0)) {
			turn = 0;
		}
		else {
			turn = axisTurn;
		}
		if((axis < .05 && axis > 0) || (axis > (-.05) && axis < 0)) {
			forward = 0;
			hold = 0;
		}
		/* Ramp? */
		else {
			/* State of controller */
			//if (axis > .05) {
				//holdingUp = true;
				//holdingDown = false;
			//}
			//else if (axis < -.05) {
				//holdingUp = false;
				//holdingDown = true;
			//}
			
			/* Is long pressed? */
			//if (axis > .05) {
				//if (hold >= held) {
					//top = true;
				//}
				//else {
					//top = false;
					//hold++;
				//}
			//}
			//else if (axis < -.05) {
				//if (hold >= held) {
					//bottom = true;
				//}
				//else {
					//bottom = false;
					//hold++;
				//}
			//}
			
			/* Checks for ramp necessity 
			if (top == true && axis < -.05) {
				ramp = true;
				
			}
			else if (bottom == true && axis > .05) {
				ramp = true;
			}*/
			
			/* Set speed */
			if (/*ramp == true && */axis > .05) {
				forwardFinal = /*ramps[rampCurrent] * */axis;
				//rampCurrent++;
			}
			else if (/*ramp == true && */axis < -.05) {
				forwardFinal = /*ramps[rampCurrent] * */axis;
				//rampCurrent++;
			}
			else{
				forwardFinal = axis;
			}
			
			/* Reset Ramp?
			if (rampCurrent >= rampEnd) {
				top = false;
				bottom = false;
				hold = 0;
				rampCurrent = 0;
				ramp = false;
			}*/
		}
		
		/** Output */
		//SmartDashboard.putNumber("forward", forwardFinal);
		//SmartDashboard.putNumber("turn", turn);
		//SmartDashboard.putNumber("rampCurrent", rampCurrent);

		Robot.drivetrain.difDrive.arcadeDrive(forwardFinal, turn * -1);
	}

	protected boolean isFinished() {
		return false;
	}
}
