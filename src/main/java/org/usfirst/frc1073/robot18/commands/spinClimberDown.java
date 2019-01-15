package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class spinClimberDown extends Command {
	
	private double speed;
	
	public spinClimberDown() {
		//requires(Robot.climber);
	}

	protected void initialize() {
		speed = 0;
	}
	
	protected void execute() {
		
		Robot.climber.climberDrive.tankDrive(-1, -1);
	}

	protected boolean isFinished() {
		return false;
	}

}