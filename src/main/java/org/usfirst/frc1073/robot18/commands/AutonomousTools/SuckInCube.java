package org.usfirst.frc1073.robot18.commands.AutonomousTools;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc1073.robot18.RobotMap;
import org.usfirst.frc1073.robot18.Bling;
/**
 *
 */
public class SuckInCube extends Command {

	private double time, timer, timeEnd, delay, delayer, delayEnd;

	private boolean clawBool;

	public SuckInCube() {
		this.time = .5;
		this.delay = 0;
	}

	public SuckInCube(double time) {
		this.time = time;
		this.delay = 0;
	}

	public SuckInCube(double time, double delay) {
		this.time = time;
		this.delay = delay;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		clawBool = false;
		
		delayEnd = delay * 20;
		delayer = 0;
		timeEnd = (time * 20) + delayEnd;
		timer = 0;
		Robot.bling.sendSuckinCube();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.collector.collectDrive.tankDrive(1, 1);
		timer++;
		
		if (delayEnd <= delayer) {
			if (clawBool == false) {
				Robot.pneumatic.openClaw();
				clawBool = true;
			}
		}
		delayer++;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		boolean finished = false;
		if (timer > timeEnd || Robot.oi.driverCancel.get() == true || Robot.oi.operatorCancel.get() == true) {
			finished = true;
			Robot.collector.collectDrive.tankDrive(0, 0);
			Robot.bling.sendFinished();
		}
		return finished;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.bling.sendFinished();
	}
}