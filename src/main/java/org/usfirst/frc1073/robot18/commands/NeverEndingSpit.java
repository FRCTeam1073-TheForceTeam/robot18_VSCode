package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1073.robot18.RobotMap;

/**
 *
 */
public class NeverEndingSpit extends Command {
	double timer;

	// Called just before this Command runs the first time
	protected void initialize() {
		timer = 0;
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.collector.collectDrive.tankDrive(-.7, -.7);
		Robot.bling.sendDeliverCube();
		timer = timer + 1;
		SmartDashboard.putNumber("spittimer", timer);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		
		if (Robot.oi.driverCancel.get() == true || Robot.oi.operatorCancel.get() == true || timer >=130) {
			Robot.collector.collectDrive.tankDrive(0, 0);
			return true;
			
		}
		else {
			return false;
					}
		}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
