package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ElectricalTest extends Command {
	private int timer, time;
	public ElectricalTest() {
	}
	protected void initialize() {
		timer = 100;
		time = 0;
	}
	protected boolean isFinished() {
		if (time < timer / 2) {
			RobotMap.rightMotor1.set(1);
			time++;
		}
		else if (timer/2 <= time && time <= timer) {
			RobotMap.rightMotor1.set(-1);
			time++;
		}
		else {
			time = 0;
		}
		return false;
	}
}
