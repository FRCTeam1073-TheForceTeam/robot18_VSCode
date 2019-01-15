package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class doBoth extends Command {

	public doBoth() {
	}
	protected void initialize() {
	}
	protected void execute() {
		SmartDashboard.putNumber("right", RobotMap.rightMotor1.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("left", RobotMap.leftMotor1.getSelectedSensorPosition(0));
	}
	protected boolean isFinished() {
		boolean isFinished = false;
		return isFinished;
	}
}