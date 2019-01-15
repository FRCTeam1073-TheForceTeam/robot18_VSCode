package org.usfirst.frc1073.robot18.commands.AutonomousTools;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc1073.robot18.Bling;
public class IsCubeIn extends Command {
	
	public double voltage, stopVoltage;
	
	public IsCubeIn() {
		
	}
	
	protected void initialize() {
		Robot.clawBool = false;
		stopVoltage = .50;
		Robot.bling.sendCubein();
	}

	protected void execute() {
		voltage = RobotMap.clawSensor.getVoltage();
	}
	
	protected boolean isFinished() {
		boolean isFinished = false;
		if (stopVoltage <= voltage) {
			Robot.clawBool = true;
			isFinished = true;
			Robot.bling.sendFinished();
		}
		return isFinished;
	}
}
