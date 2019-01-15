package org.usfirst.frc1073.robot18.commands.AutonomousTools;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc1073.robot18.RobotMap;

/**
 *
 */
public class SpitOutCube extends Command {

	private double speed, time, timer, timeEnd, delay, delayer, delayEnd;

	private boolean clawBool;

	public SpitOutCube() {
		this.time = .5;
		this.delay = 0;
		this.speed = 1;
	}

	public SpitOutCube(double time, double speed) {
		this.time = time;
		this.delay = 0;
		this.speed = speed;
	}

	public SpitOutCube(double time, double delay, double speed) {
		this.time = time;
		this.delay = delay;
		this.speed = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		clawBool = false;

		delayEnd = delay * 50;
		delayer = 0;
		timeEnd = time * 50;
		timer = 0;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.collector.collectDrive.tankDrive(speed, speed);
		Robot.bling.sendDeliverCube();
		timer++;
		
		if (delayEnd <= delayer) {
			if (clawBool == false) {
				//Robot.pneumatic.openClaw();
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
		}
		return finished;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
