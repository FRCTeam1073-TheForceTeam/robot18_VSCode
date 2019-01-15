package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SpinCollectorTele extends Command {
	
	private double speed;
	
	/**
	 * Controller command for tele collector spinning
	 * @author Nathaniel
	 */
	public SpinCollectorTele() {
		requires(Robot.collector);
	}
	
	protected void initialize() {
		speed = 0;
	}
	
	protected void execute() {
        SmartDashboard.putNumber("Claw Prox", RobotMap.clawSensor.getAverageVoltage());
		if (Robot.oi.operatorControl.getRightTrigger() > 0) {
			speed = Robot.oi.operatorControl.getRightTrigger();
		}
		else if (Robot.oi.operatorControl.getLeftTrigger() > 0) {
			speed = -Robot.oi.operatorControl.getLeftTrigger()*0.75;
		//	if (RobotMap.clawSensor.getVoltage() <=2);
		}
//		else if (RobotMap.clawSensor.getAverageVoltage() > 1 && Robot.oi.operatorControl.leftBumper.get() == true) {
//			speed = -0.5;
//		}
		else {
			speed = 0;
		}
		Robot.collector.collectDrive.tankDrive(speed, speed);
	}

	protected boolean isFinished() {
		return false;
	}

}